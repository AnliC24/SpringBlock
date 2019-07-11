package tern.block.web.controller;


import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tern.block.web.mqMessage.StreamClient;

@RestController
@RequestMapping("/send")
public class sendRabbitsController extends PublicController {
	
	@Autowired
    private AmqpTemplate amqpTemplate;
	
	@Autowired
	private StreamClient streamClient;
	
	@GetMapping("/sendMq")
	public void send()
	{
		amqpTemplate.convertAndSend("myQueue", "now :" + new Date());
		Log.info("消息已发送");
	}
	
	@GetMapping("/sendStreamMq")
	public void process()
	{
		streamClient.output().send(MessageBuilder.withPayload("now  "+new Date()).build());
	}
	
	
	
}
