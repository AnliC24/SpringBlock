package tern.block.serviceImpl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import tern.block.service.SendEmailService;



/**
 * @author WindC.~
 * @time 2019/04/24 0:52
 * @title 短信接口开发
 * @version 1.0
 * @problem 异常未处理,未抽成邮箱总线
 * */

@Service
public class SendEmailServiceImpl extends BaseService implements SendEmailService{
   
	@Value("${spring.mail.username}")
	private String FROMUSER;
	//private String FROMUSER = "13808547353@163.com";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Override
	public void sendSimpleMail(String target, String subject, String content) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(target);
		message.setSubject(subject);
		message.setText(content);
		message.setFrom(FROMUSER);
		
		mailSender.send(message);
	}

	@Override
	public void sendHtmlMail(String target, String subject, String content) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setTo(target);
			
			helper.setSubject(subject);
			
			helper.setText(content,true);
			
			helper.setFrom(FROMUSER);
			
			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void sendAttachmentsMail(String target, String subject, String content, String filePath) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setFrom(FROMUSER);
			
			helper.setTo(target);
			
			helper.setText(content,true);
			
			helper.setSubject(subject);
			
			FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
			
			String fileName = fileSystemResource.getFilename();
			
			helper.addAttachment(fileName, fileSystemResource);
			
			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void sendInlineReSourceMail(String target, String subject, String content, String rscPath, String rscId)  {
		// TODO Auto-generated method stub
         MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setFrom(FROMUSER);
			
			helper.setTo(target);
			
			helper.setText(content,true);
			
			helper.setSubject(subject);
			
			FileSystemResource res = new FileSystemResource(new File(rscPath));
			
			helper.addInline(rscId, res);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		mailSender.send(message);
	
	}

	@Override
	public void sendTemplateMail(String target,String subject) {
		// TODO Auto-generated method stub
		Context context = new Context();
		context.setVariable("id","006");
		
		String emailContent = templateEngine.process("notice", context);
		this.sendHtmlMail(target,subject,emailContent);
	}

	@Override
	public void sendTemplateMail(String target, String subject, String targetTemplate) {
		// TODO Auto-generated method stub
		Context context = new Context();
		
		String emailContent = templateEngine.process(targetTemplate, context);
		this.sendHtmlMail(target,subject,emailContent);
	}
 
}
