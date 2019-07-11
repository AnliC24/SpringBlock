package tern.block.service;

import javax.mail.MessagingException;

/**
 * 邮件服务测试
 * */
public interface SendEmailService {
    
	/**
	 * 文本邮件发送
	 * */
	public void sendSimpleMail(String target,String subject,String content);
	
	
	/**
	 * HTML邮件发送
	 * @throws MessagingException 
	 * */
	public void sendHtmlMail(String target,String subject,String content) throws MessagingException;
	
	/**
	 * 邮件附件发送
	 * @throws MessagingException 
	 * */
	public void sendAttachmentsMail(String target,String subject,String content,String filePath) throws MessagingException;
	
	/**
	 * 图片邮件发送
	 * @throws MessagingException 
	 * */
	public void sendInlineReSourceMail(String target,String subject,String content,String rscPath,String rscId) throws MessagingException;
	
	/**
	 * 默认模板邮件发送
	 * @throws MessagingException 
	 * */
	public void  sendTemplateMail(String target,String subject) throws MessagingException;
	
	
	/**
	 * 可选模板发送
	 */
	public void  sendTemplateMail(String target,String subject,String targetTemplate) throws MessagingException;

}
