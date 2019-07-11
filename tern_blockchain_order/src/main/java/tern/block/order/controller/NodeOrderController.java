package tern.block.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;


/**
 * 节点订单解密信息
 * */

@Controller
public class NodeOrderController extends PublicController{
    
	
	/**
	 * 获取订单信息,解密
	 * */
	public ModelAndView getOrderEncrypt()
	{
		ModelAndView modelAndView = new ModelAndView("");
		/**
		 * 
		 * */
		return modelAndView;
	}
	
	/**
	 * 订单信息打包成块
	 * */
	public String orderChangeBlock()
	{
		
		/**
		 * 1.获取订单信息
		 * 2.填充成块
		 * */
		/**
		 * 测试模块
		 * 
		 * */
		return "打包成功";
	}
}
