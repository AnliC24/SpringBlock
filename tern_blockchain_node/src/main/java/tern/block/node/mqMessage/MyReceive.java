package tern.block.node.mqMessage;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import tern.block.core.dto.Block;
import tern.block.node.runner.VaildBlockRunner;


/**
 * @author WindC.~
 * @version 1.0
 * @title 接收mq消息   对区块进行监听,验证
 * @time 2019/04/22
 * */

@Component
public class MyReceive {
    
	//注册日志log
 	protected Logger LOG = LogManager.getLogger(this.getClass());
 	
 	@Autowired
    private AmqpTemplate rabbitTemplate;
 	/**
 	 * 监听区块
 	 * */
 	@RabbitListener(bindings = @QueueBinding(
 			value = @Queue("vailOrder"),
 			exchange = @Exchange("blockExchange")
 			))
	public void process(String message)
	{
 		LOG.info("MqReceiver time: "+new Date());
 		LOG.info("开始验证区块...");
 		Block vaildblock = JSON.parseObject(message, Block.class);
 		if (vaildblock!=null) {
 			rabbitTemplate.convertAndSend("vailBlockResult","验证成功");
		}else{
			//信息接收错误
			 rabbitTemplate.convertAndSend("vailBlockResult","验证失败");
		}
	}
 	
 	/**
 	 * 监听验证结果
 	 * */
 	@RabbitListener(bindings = @QueueBinding(
 			value = @Queue("vailBlockResult"),
 			exchange = @Exchange("vaildResult")
 			))
	public void vaildResult(String message)
	{
 		LOG.info("MqReceiver time: "+new Date());
 		LOG.info("解析验证结果");
 		if ("验证成功".equals(message)) {
 			VaildBlockRunner.vaildBlockResult++;
		}else if("验证失败".equals(message)){
		    LOG.info("此节点解析异常,无法验证");	
		}
	}
 	
}
