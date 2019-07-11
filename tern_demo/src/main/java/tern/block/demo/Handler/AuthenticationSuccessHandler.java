package tern.block.demo.Handler;





import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tern.block.core.dto.Node;
import tern.block.core.dto.NodeLoadStates;
import tern.block.demo.helper.TokenHelper;
import tern.block.demo.serviceImpl.NodeLoadServiceImpl;
import tern.block.demo.util.ClientUtil;
import tern.block.demo.client.NodeLoadClient;


/**
 * 登录成功后的处理
 * */

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
	
	
	
	@Autowired
    private NodeLoadServiceImpl nodeLoadService;
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private NodeLoadClient NodeLoadClient;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		    //首先获取登录节点信息
		    String nodeEmail = (String) authentication.getPrincipal();
		    JSONObject tokenObject = new JSONObject();
		
			tokenObject.put("ipAddress", ClientUtil.getClientIp(request));
			tokenObject.put("nodeName", nodeEmail);
			
			//生成token 
			String jwt = tokenHelper.createJwtToken(tokenObject.toJSONString());//生成token
			
			/**
			 * 1.创建存放token的cookie
			 * */
			String uuid = UUID.randomUUID().toString();
			
			Cookie authCookie = new Cookie("token", uuid);
			authCookie.setPath("/");
			authCookie.setHttpOnly(true);
			authCookie.setMaxAge(0);
			response.addCookie(authCookie);
			
			
			
			
			
			
			/**
			 * 获取节点信息
			 * */
			Map<String, Object> loginNode = new HashMap<>();
			loginNode.put("loginEmail", nodeEmail);
			Node node = nodeLoadService.loginNode(loginNode);
			
			/**
			 * 将节点信息存入session内
			 * */
			HttpSession session = request.getSession();
			session.setAttribute("node",node);
			/**
			 * 根据节点内容的不同
			 * 进行跳转
			 * 0 普通节点跳转至首页
			 * 1 超级节点跳转至后台
			 * 节点登录后,发送mq消息,节点登录成功
			 * */
			NodeLoadStates nodeStates = new NodeLoadStates();
			nodeStates.setNodeLoadStatus("登录成功");
			nodeStates.setNode(node);
			amqpTemplate.convertAndSend("nodeLoad",JSON.toJSONString(nodeStates));
			
			/**
			 * 登录成功后,向节点发送登录成功的提示信息
			 * */
			NodeLoadClient.sendSuccessMail(node.getNodeEmail().toString());
			
			if(node.getNodeCompetence().toString().equals("0"))
			{
				request.getRequestDispatcher("/superIndex").forward(request, response);
			}
			else if(node.getNodeCompetence().toString().equals("1"))
			{   
				
				request.getRequestDispatcher("/index").forward(request, response);
			}
			else{
				request.getRequestDispatcher("/superIndex").forward(request, response);
			}
			
		//response.sendRedirect("/hello");
	}
     
	
}
