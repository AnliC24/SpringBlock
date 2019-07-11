package tern.block.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import tern.block.core.dto.Node;
import tern.block.demo.serviceImpl.GateUserDetailService;
import tern.block.demo.serviceImpl.LoginRedisServiceImpl;
import tern.block.demo.serviceImpl.NodeLoadServiceImpl;



/**
 * @author WindC~
 * @Time 2019/4/16 
 * @title 节点信息验证-- 区块链底层
 * @Version 1.0
 * */
@Component
public class NodeAuthenticationProvider implements AuthenticationProvider{
  
    private Logger 	Log = LogManager.getLogger(this.getClass());
    
    @Autowired
    private NodeLoadServiceImpl nodeLoadService;
    
    @Autowired
    private GateUserDetailService gateUserDetailService;
    
    @Autowired
    private LoginRedisServiceImpl loginRedisServiceImpl;
    
  
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		/**
		 * 该内容用来做数据库节点验证
		 * 1.判断节点信息
		 * 2.验证输入次数
		 * 3.生成令牌
		 * */
		// 获取待认证的用户名 & 密码
		String nodename = authentication.getName();
		String password = authentication.getCredentials().toString();
		Log.info("待验证的节点信息:"+"节点名称:"+nodename+"节点密码:"+password);
		/**
		 * 判断节点信息
		 * 1.是否为空
		 * 2.密码是否错误
		 * 3.节点状态是否正常 0为正常状态 1为冻结状态 2为异常状态 3为活跃状态
		 * */
		if(nodename != null)
		{
			 //节点登录邮箱不为空的时候,验证密码是否为空
			if(password != null)
			{
				/**
				 * 1.先验证是否有此节点
				 * */
				List<Map<String, Object>> allNodeEmail = nodeLoadService.vaildNodeEmail();
				Map<String, Object> loginNode = new HashMap<>();
				loginNode.put("loginEmail", nodename);
				loginNode.put("loginPassword", password);
				if(checkEmailExists(allNodeEmail,nodename,"node_email"))
				{   
					//若该节点存在,则判断是否已经存在redis内
					Node node = nodeLoadService.loginNode(loginNode);
					if(node == null)
					{
						 throw new UsernameNotFoundException("登录节点不存在");
					}
					//若不存在reids内,则判断其密码是否正确
					if(!node.getNodePassword().equals(password))
					{
						//密码输入不正确,超过3次将会被冻结状态
						/**
						 * 1.获取密码已输入次数
						 * 2.超过3次,状态更改为1
						 * 3.小于等于3次,num次数加1
						 * */
						 int unBlock = nodeLoadService.nodeBlockNum(nodename);
						 if(unBlock>=3)
						 {   
							 //冻结账号信息
							 Map<String, Object> map = new HashMap<>();
							 map.put("state", 1);
							 map.put("nodeEmail", nodename);
							 nodeLoadService.updateNodeState(map);
							 throw new BadCredentialsException("3次连续输入错误的密码，节点已被锁定，请联系管理员");
					     }
					     else
					     {
							 unBlock++;
							 Map<String, Object> map = new HashMap<>();
							 map.put("num", unBlock);
							 map.put("nodeEmail", nodename);
						    if(nodeLoadService.updateBlockNum(map))
						    {   
							   throw new BadCredentialsException("密码不正确,还剩"+(3-unBlock)+"次输入机会");
						    }
						     else
						     {
							   throw new LockedException("数据更新异常,请联系管理员");
						     }
					      }
				     }
					 else{
						/**
						 * 1.判断节点状态,0,2,3可以正常登陆,2必须要有提示信息,账号存在危险,1无法登录
						 * 2.登录成功,则把num次数改为0
						 * */
						StringBuilder key = new StringBuilder("node");
						key.append(node.getNodeId());
						if(loginRedisServiceImpl.checkLoginNodeRedis(key.toString()))
					    {
							//已经存在登录用户,不能重复登录
							throw new LockedException("节点已登录,无法重复登录");
					    }
						 UserDetails userDetails = null;
						 if(node.getNodeState()==1)
						 {   
							 userDetails=  gateUserDetailService.loadUserByUsername(node);
							 throw new LockedException("节点已被锁定，请联系管理员");
						 }else{
							    userDetails=  gateUserDetailService.loadUserByUsername(node);
							    Collection<? extends GrantedAuthority> authors = userDetails.getAuthorities();
								// 生成令牌
								Authentication auth = new UsernamePasswordAuthenticationToken(nodename, password, authors);
								
								/**
								 * 存入reids 内,在返回令牌
								 * */
								loginRedisServiceImpl.loginNodeRedis(node);
								 /**
								 * 更新节点锁定次数
								 * */
								 Map<String, Object> updateInfo = new HashMap<>();
								 updateInfo.put("num", 0);
								 updateInfo.put("nodeEmail", nodename);
								 nodeLoadService.updateBlockNum(updateInfo);
								 
								 return auth;
						}
					}
			   }
			    else
			    {
				  throw new UsernameNotFoundException("登录节点不存在");
		        }
	        }
			else
			{
				throw new UsernameNotFoundException("登录节点密码不能为空");
			}
	     }
	      else
	      {
			  //节点登录邮箱为空的时候,抛出自定义异常
			  throw new UsernameNotFoundException("登录节点名称不能为空");
		  }
  }

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	/**
	 * 验证登录节点邮箱是否存在
	 * */
	public boolean checkEmailExists(List<Map<String, Object>> map,String vaildEmail,String vaildType)
	{   
		for(Map<String, Object> nodeInfo : map)
		{    
			String node = nodeInfo.get("node_email").toString().trim();
			if(node.equals(vaildEmail.toString().trim()))
			{
				//如果存在,返回true
				return true;
			}
		}
		return false;
	}

}
