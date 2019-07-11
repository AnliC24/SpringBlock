package tern.block.demo.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import tern.block.core.dto.Node;
import tern.block.core.dto.mqMessage;
import tern.block.demo.client.SysKeysClient;
import tern.block.demo.dto.NodeDTO;
import tern.block.demo.mqClient.RegistryStreamClient;
import tern.block.demo.service.RegistryNodeService;
import tern.block.demo.serviceImpl.NodeLoadServiceImpl;
import tern.block.demo.util.DateUtil;
import tern.block.demo.util.FileUtil;

/**
 *  WindC.~
 *  普通节点注册
 * */

@Controller
@RequestMapping("/node")
public class RegistryNodeController extends PublicController{
	 
	@Autowired
	private RegistryNodeService registryNodeService;
	
	@Autowired
	private SysKeysClient sysKeysClient;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private RegistryStreamClient registryStreamClient;
	
    @Autowired
	private AmqpTemplate amqpTemplate;
    
    @Autowired
    private NodeLoadServiceImpl nodeLoadServiceImpl;
	
	/**
	 * 注册
	 * */
	@PostMapping(value="/registry")
	@Transactional
	public ModelAndView registrySystem(@RequestBody @Validated  @ModelAttribute NodeDTO nodedto, BindingResult bindingResult)
	{   
		ModelAndView model=new ModelAndView();
		if(nodedto.getNodeName()==null)
		{
			model.setViewName("register");
			Log.info("注册节点解析为空,无法验证");
			model.addObject("nodeEmail","注册节点解析为空,无法验证");
			return model;
		}
		//Node node =new Node(nodedto.getNodeName(), nodedto.getNodeSex() ,nodedto.getNodePassword(), nodedto.getNodeEmail(), nodeTelphone, nodeRegistryTime, nodePubKey, nodePriKey, nodeCompetence, nodeState, nodeBlockNum)
		if(bindingResult.hasErrors())
        {
            model.setViewName("register");
            //model.addObject("error", true);
            model.addObject("errorMsg",bindingResult.getAllErrors());
            return model;
        }else{
            //判断一下注册邮箱是否已存在 checkMail
            if(!checkMail(nodedto.getNodeEmail()))
            {
               //已存在
                ObjectError error=new ObjectError("nodeEmail","邮箱已存在,请重新选择注册");
                bindingResult.addError(error);
                model.setViewName("register");
                model.addObject("errorEmail", true);
                model.addObject("nodeEmail","邮箱已存在,请重新填写");
                if(!checkTelphone(nodedto.getNodeTelphone()))
                {   
                	//已存在
                    bindingResult.addError(error);
                    model.setViewName("register");
                    model.addObject("errorTel", true);
                    model.addObject("nodeTelphone","电话号码已被注册,请重新填写");
                }
                //判断一下密码是否相同
                boolean falg =checkVaildPassword(nodedto.getNodePassword(),nodedto.getNodePasswordVaild());
              //相同
                if(falg)
                {    
                	 model.setViewName("register");
                	 model.addObject("errorPassword", true);
                     model.addObject("nodePassword","两次输入的密码不正确,请重新输入");
                }
                
              //首先判断手机号码是否格式错误
                if(nodedto.getNodeTelphone().length()<11||nodedto.getNodeTelphone().length()>11)
                {
                	 model.setViewName("register");
                     model.addObject("errorTel", true);
                     model.addObject("nodeTelphone","电话号码格式不正确,因为11位数,请重新填写");
                }
                
                return model;
            }
            
            //判断一下密码是否相同
            boolean falg =checkVaildPassword(nodedto.getNodePassword(),nodedto.getNodePasswordVaild());
            
            //相同
            if(falg)
            {    
            	 model.setViewName("register");
            	 model.addObject("errorPassword", true);
                 model.addObject("nodePassword","两次输入的密码不正确,请重新输入");
                 return model;
            }
            
            
            //首先判断手机号码是否格式错误
            if(nodedto.getNodeTelphone().length()<11||nodedto.getNodeTelphone().length()>11)
            {
            	 model.setViewName("register");
                 model.addObject("errorTel", true);
                 model.addObject("nodeTelphone","电话号码格式不正确,因为11位数,请重新填写");
                 return model;
            }
            
            //判断一下手机号码是否已经被注册
            if(!checkTelphone(nodedto.getNodeTelphone()))
            {
            	ObjectError error=new ObjectError("nodeEmail","邮箱已存在,请重新选择注册");
                bindingResult.addError(error);
                model.setViewName("register");
                model.addObject("errorTel", true);
                model.addObject("nodeTelphone","电话号码已被注册,请重新填写");
                return model;
            }
            
            
            
        }
		/**
    	 * 生成公私钥对
    	 * */
		boolean flag = sysKeysClient.sysKeysCreate(nodedto.getNodeName());
		if(flag)
		{
			//注册节点信息入库
	        if(!addNode(nodedto))
	        {
	            model.addObject("registry","0");
	            model.setViewName("register");
	            model.addObject("errorMessage","节点创建失败");
	            return model;
	        }else{
	        	/**
	        	 * 节点入库成功,读取本地公钥文件,存入数据库
	        	 * */
	        	String pubKey= this.getPubKeys(nodedto.getNodeName());
	        	if(pubKey!=null)
	        	{
	        		Map<String, Object> pubkeyNode = new HashMap<String, Object>();
	        		pubkeyNode.put("pubKeyNode", pubKey);
	        		pubkeyNode.put("node_email", nodedto.getNodeEmail());
	        		boolean result = registryNodeService.nodePubKey(pubkeyNode);
	        		if(result)
	        		{
	        			/**
	        			 * 公钥读取成功
	        			 * */
	        			Log.info(nodedto.getNodeName()+"公钥读取成功");
	        			Log.info(nodedto.getNodeEmail()+"Account.txt----账本文件开始创建");
	        			/**
	        			 * 创建账本
	        			 * */
	        			String message = sysKeysClient.sysAccountCreate(nodedto.getNodeEmail());
	        			model.addObject("message",message);
	        			model.addObject("registry","1");
	        	        model.setViewName("login");
	        	        /**
	        	         * 节点创建成功后,需要发送邮件提示用户
	        	         * 创建mq消息队列,
	        	         * 把node和注册成功封装成map
	        	         * */
	        	        mqMessage info = new mqMessage();
	        	        info.setMessage("注册成功");
	        	        /**
	        	         * NodeDTO -> node
	        	         * 在搜索一次Node信息
	        	         * */
	        	        Map<String,Object> loginInfo = new HashMap<>();
	        	        loginInfo.put("loginEmail", nodedto.getNodeEmail());
	        	        Node node = nodeLoadServiceImpl.loginNode(loginInfo);
	        	        info.setNode(node);
	        	        amqpTemplate.convertAndSend("nodeRegistry",JSON.toJSONString(info));
	        	        //提示用户注册成功
	        	        model.setViewName("register");
	                    model.addObject("registryMessage", true);
	                    model.addObject("registryMessage","注册成功");
	        			return model;
	        			
	        		}else{
	        			 model.addObject("registry","0");
	     	             model.setViewName("register");
	     	             model.addObject("errorMessage","公钥插入失败...节点创建失败");
	     	             /**
	     	              * 节点创建失败,删除已存节点信息
	     	              * */
	     	             boolean  deleteResult = registryNodeService.deleteNodeInfo(nodedto.getNodeEmail());
	     	             return model;
	        		}
	        	}
	        }		
		}
		model.addObject("registry","0");
		model.addObject("errorMessage","公私钥生成异常,节点创建失败");
	    model.setViewName("register");
	    return model;
		
	}
	
	/**
     * 检查智能节点邮箱是否已经被注册
     * */
    public boolean checkMail(String email){
        String checkResult = registryNodeService.checkMail(email);
        if(email == null || email == "")
        {
        	return false;
        }
        if(checkResult != null)
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * 注册节点信息 存入数据库
     * */
    public boolean addNode(NodeDTO registryNode){
        //DateUtil.dateToString(new Date(),"yyyy-MM-dd");
    	/**
    	 * 拼装节点信息,并添加入数据库
    	 * */
    	registryNode.setNodeBlockNum(0);//默认为0
    	/**
    	 * 1.信息验证成功后,本地生成公私钥对
    	 * 2.生成完成后，读取本地公钥,然后写入数据库
    	 * 3.私钥用户自己保存
    	 * 4.保存成功后,发送消息队列给sys服务,sys服务调用其短信接口,通知用户邮箱 节点注册成功
    	 * */
    	/**
    	 * 本地读取节点公钥
    	 * */

    	registryNode.setNodeCompetence(1);//默认为普通节点
    	registryNode.setNodeState(0); //默认为正常状态
    	registryNode.setNodeRegistryTime(DateUtil.stringToDate(DateUtil.dateToString(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd"));
        return registryNodeService.registryNode(registryNode);
    }
    /**
     * 读取本地公钥文件
     * */
    public String getPubKeys(String nodeName)
    {
    	String target = nodeName+"PubKey.txt";
    	if(fileUtil.readText(target) != null)
    	{
    		return fileUtil.readText(target);
    	}
    		Log.info("公钥读取失败..,创建节点失败");
    		return null;
    }
    
    /**
     * 发送注册成功信息
     * */
     public void sendSuccessInfo()
     {
    	 registryStreamClient.output().send(MessageBuilder.withPayload("注册成功").build());
     }
     
     
     /**
      * 判断手机号码是否已被注册
      * */
     public boolean checkTelphone(String tel){
    	 List<String> checkResult = registryNodeService.checkTel(tel);
         if(tel == null || tel == "")
         {
         	return false;
         }
         if(checkResult != null)
         {
             return false;
         }
         return true;
     }
     
     /**
      * 验证两个值是否相同
      * */
     public boolean checkVaildPassword(String password,String passwordVaild)
     {
    	 if(password==null || passwordVaild ==null)
    	 {
    		 return false;
    	 }
    	 if("".equals(passwordVaild)   || "".equals(passwordVaild))
    	 {
    		 return false;
    	 }
    	 if(password.toUpperCase().equals(passwordVaild.toUpperCase()))
    	 {
    		 return true;
    	 }
    	 return false;
     }
}
