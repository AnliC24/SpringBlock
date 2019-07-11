package tern.block.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class TestController extends PublicController{
    
	@RequestMapping(value = "/login")
	public String login()
	{   
		Log.info("默认登录页面启动....");
		return "login";
	}
	
	@RequestMapping(value = "/")
	public String toHome()
	{   
		Log.info("首页加载....");
		return "index";
	}
	
	@RequestMapping("/hello")
	public String index()
	{   
		Log.info("资源启动....");
		return "hello";
	}
	
	@RequestMapping(value="/index")
	public String  home()
	{   
		Log.info("首页启动....");
		return "index";
	}
     
	@RequestMapping("/register")
	public String  register()
	{   
		Log.info("注册启动....");
		return "register";
	}
	
	@RequestMapping("/logout")
	public String  logout()
	{   
		Log.info("退出发生....");
		return "login";
	}
	
	
	@RequestMapping("/superIndex")
	public String  superIndex()
	{   
		Log.info("超级管理员登录....");
		return "superIndex";
	}
	
	@RequestMapping("/order")
	public String order()
	{
		Log.info("交易页面启动....");
		return "order";
	}
	
	@RequestMapping("/product")
	public String product()
	{
		Log.info("订单验证模块");
		return "product";
	}
	
}
