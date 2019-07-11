package tern.block.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tern.block.serviceImpl.SendEmailServiceImpl;

/**
 * 短信接口
 * */
@Controller
public class MailController extends PublicBaseController{
    
	@Autowired
	private SendEmailServiceImpl sendEmailServiceImpl;
	
	/**
	 * 登录成功后,短信通知
	 */
	@GetMapping("/sendNodeLoadSuccessMail")
	@ResponseBody
	public boolean sendNodeLoadSuccessMail(@RequestParam("nodeEmail") String nodeEmail) throws MessagingException
	{   
		sendEmailServiceImpl.sendTemplateMail(nodeEmail, "节点登录成功","loadsuccess");
		return true;
	}
	
	/**
	 * 操作通知
	 * */
	@GetMapping("/sendNodeOptionMail")
	@ResponseBody
	public boolean sendNodeOptionMail(@RequestParam("nodeEmail") String nodeEmail,@RequestParam("nodeMessage") String nodeMessage) throws MessagingException
	{
		sendEmailServiceImpl.sendTemplateMail(nodeEmail, nodeMessage,"nodeOption");
		return true;
	}
	 
	
}
