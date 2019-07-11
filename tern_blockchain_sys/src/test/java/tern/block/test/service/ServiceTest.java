package tern.block.test.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tern.block.serviceImpl.SendEmailServiceImpl;


/**
 * @author WindC.~
 * @time 2019/04/24 0:52
 * @title 短信接口开发测试
 * @version 1.0
 * */


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    
	
	@Resource
	SendEmailServiceImpl sendEmailServiceImpl;
	
	private String FROMUSER = "13808547353@163.com";
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sendSimpleEmail()
	{
		sendEmailServiceImpl.sendSimpleMail(FROMUSER, "短信接口服务", "交易联盟短信服务接口");
	}
    
	@Test
	public void sendHTMLEmail() throws MessagingException
	{   
		StringBuilder html = new StringBuilder("<html><\br>");
		html.append("<body><\br><h3>区块链交易联盟短信接口测试---HTML接口</h3><\br></body></html>");
		sendEmailServiceImpl.sendHtmlMail(FROMUSER, "短信接口服务", html.toString());
	}
	
	@Test
	public void sendAttachmentsMail() throws MessagingException
	{   
		String filePath = "D:/rootPriKey.txt";
		sendEmailServiceImpl.sendAttachmentsMail(FROMUSER, "短信接口服务--附件接口测试", "请打开附件--附件内容为系统公钥", filePath);
	}
	
	@Test
	public void sendInlineResourceTest() throws MessagingException
	{
		String imgPath = "D:/me.jpg";
		String rcsId = "cfw";
		String content = "<html><body>这是有图片的邮件:<img src=\'cid:"+rcsId+"\'></img></body></html>";
		sendEmailServiceImpl.sendInlineReSourceMail(FROMUSER, "短信接口服务--图片接口测试", content, imgPath, rcsId);
	}
	
	
	@Test
	public void sendTemplateMailTest() throws MessagingException
	{
		sendEmailServiceImpl.sendTemplateMail(FROMUSER, "这是一封模板邮件");
	}
}
