package tern.block.demo.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import tern.block.core.dto.Node;
import tern.block.demo.serviceImpl.LoginRedisServiceImpl;
import tern.block.demo.serviceImpl.NodeLoadServiceImpl;



/**
 * 登出成功后调用
 * */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    
    private Logger 	Log = LogManager.getLogger(this.getClass());
	
	@Autowired
    private LoginRedisServiceImpl loginRedisServiceImpl;
	
	@Autowired
    private NodeLoadServiceImpl nodeLoadService;
	
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//把redis内的对象删除
		Log.info("退出操作");
		/**
		 *1. 获取当前登录节点Email
		 *2. 获取此节点的Id
		 *3. 删除此节点的redis信息
		 *4. 删除此节点的token信息
		 * */
		String nodeEmail = (String)authentication.getPrincipal();
		Map<String, Object> loginNode = new HashMap<>();
		loginNode.put("loginEmail", nodeEmail);
		Node logoutNode = nodeLoadService.loginNode(loginNode);
		
		loginRedisServiceImpl.deleteLoginNodeRedis(logoutNode.getNodeId().toString());
		
		Cookie authCookie = new Cookie("token", null);
		authCookie.setPath("/");
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(0);
		response.addCookie(authCookie);
		
		
		request.getRequestDispatcher("/login?logout=true").forward(request, response);
		
	}
   
}
