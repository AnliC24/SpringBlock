package tern.block.mqReceive;

import javax.mail.MessagingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import tern.block.serviceImpl.SendEmailServiceImpl;


@Component
@EnableBinding(RegistryStreamClient.class)
public class RegistryStreamReceive {
         
	    //注册日志log
	 	protected Logger LOG = LogManager.getLogger(this.getClass());
	 	
	 	/**
	 	 * 接入短信服务
	 	 * */
	 	private SendEmailServiceImpl sendEmailServiceImpl;
		
		@StreamListener(value = RegistryStreamClient.Input)
		public void process(Object message) throws MessagingException
		{
			
			LOG.info("StreamReceiver  "+message);
			if("注册成功".equals(message))
			{
				sendEmailServiceImpl.sendTemplateMail("773768893@qq.com", "节点注册成功");
			}
		}
	
}
