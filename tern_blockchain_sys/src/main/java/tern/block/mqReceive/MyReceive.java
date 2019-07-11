package tern.block.mqReceive;

import java.util.Date;

import javax.mail.MessagingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import tern.block.core.dto.Node;
import tern.block.core.dto.mqMessage;
import tern.block.serviceImpl.SendEmailServiceImpl;



/**
 * @author WindC.~
 * @version 1.0
 * @title 接收mq消息
 * @time 2019/04/22
 * */
@Component
public class MyReceive {
    
	    //注册日志log
	 	protected Logger LOG = LogManager.getLogger(this.getClass());
	
	 	/**
	 	 * 接入短信服务
	 	 * */
	 	@Autowired
	 	private SendEmailServiceImpl sendEmailServiceImpl;
	 	
	 	
	 	//@RabbitListener(queues="myQueue")
	 	//自动创建队列
	 	//@RabbitListener(queuesToDeclare = @Queue("myQueue"))
	 	//3.自动创建,Exchange和Queue绑定
	 	@RabbitListener(bindings = @QueueBinding(
	 			value = @Queue("nodeRegistry"),
	 			exchange = @Exchange("myExchange")
	 			))
		public void process(String message) throws MessagingException
		{
	 		try {
	 			LOG.info("MqReceiver time: "+new Date());
				
				mqMessage info = JSON.parseObject(message, mqMessage.class);
				Node node = info.getNode();
				if("注册成功".equals(info.getMessage().toString()))
				{
					sendEmailServiceImpl.sendTemplateMail(node.getNodeEmail(),node.getNodeEmail() +"节点注册成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				return;
			}
			
		}
}
