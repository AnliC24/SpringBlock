package tern.block.web.mqMessage;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author WindC.~
 * @title 发送mq消息测试
 * @time 2019/04/22
  * */

public class MySender {
	
	@Autowired
    private AmqpTemplate rabbitTemplate;
    

	/*@Autowired
    private AmqpTemplate amqpTemplate;*/

    public void send() {
    	this.rabbitTemplate.convertAndSend("myQueue", "now " + new Date());
    }

    /*
    public void sendOrder() {
        amqpTemplate.convertAndSend("myOrder", "computer", "now " + new Date());
    }*/
	
}
