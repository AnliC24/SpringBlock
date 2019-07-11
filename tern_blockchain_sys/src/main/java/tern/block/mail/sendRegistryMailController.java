package tern.block.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tern.block.serviceImpl.SendEmailServiceImpl;

/**
 * 邮件发送接口
 * */
@Component
public class sendRegistryMailController extends MailBaseController{
   
	@Autowired
	private SendEmailServiceImpl sendEmailServiceImpl;
	
	/**
	 * 节点注册成功后,发送邮件服务 -- 模板邮件
	 * */
	public  void sendTemplateMail()
	{
		
	}
}
