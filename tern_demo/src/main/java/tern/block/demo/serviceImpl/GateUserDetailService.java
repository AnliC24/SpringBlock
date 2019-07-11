package tern.block.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tern.block.core.dto.Node;
import tern.block.demo.dto.SuperNode;

import java.util.HashSet;
import java.util.Set;


/**
 * 
 * */
@Service("GateUserDetailService")
public class GateUserDetailService implements UserDetailsService{
    
	
	public static int flag;
	
	@Autowired
    private NodeLoadServiceImpl nodeLoadService;
	
	@Override
	public UserDetails loadUserByUsername(String nodeEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	
	/**
	 * 返回一个Node
	 * */
	public UserDetails loadUserByUsername(Node node) throws UsernameNotFoundException {
		
		if(node == null)
		{
			return null;
		}
		boolean unlockState = true;
		if(node.getNodeState() ==1)
		{
			unlockState = false;
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("USER_ADD"));
		return new User(
				node.getNodeName(), //用户名
				node.getNodePassword(),//密码
				true, // 是否可用
				true, // 是否过期
				true, // 证书不过期为true
				unlockState, // 账户未锁定为true
				authorities);
	}
	
	/**
	 * 返回一个SuperNode
	 * */
	public UserDetails loadUserByUsername(SuperNode node) throws UsernameNotFoundException{
		
		if(node == null)
		{
			return null;
		}
		boolean unlockState = true;
		if(node.getSysState() ==1)
		{
			unlockState = false;
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("USER_ADD"));
		return new User(
				node.getSysName(), //用户名
				node.getSysPassword(),//密码
				true, // 是否可用
				true, // 是否过期
				true, // 证书不过期为true
				unlockState, // 账户未锁定为true
				authorities);
		
	}

}
