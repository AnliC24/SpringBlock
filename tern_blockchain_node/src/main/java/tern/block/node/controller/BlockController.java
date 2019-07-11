package tern.block.node.controller;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import tern.block.core.dto.Block;
import tern.block.core.dto.BlockBody;
import tern.block.core.dto.BlockHeader;
import tern.block.core.dto.OrderInfo;
import tern.block.node.utils.BlockUtil;
import tern.block.node.utils.DateUtil;
import tern.block.node.utils.Encrypt;
import tern.block.node.client.NodeLoadClient;
import tern.block.node.runner.VaildBlockRunner;

@Controller
public class BlockController {
	    //日志记录基类
		protected Logger Log = LogManager.getLogger(this.getClass());
		
		@Autowired
		private BlockUtil blockUtil;
		
		@Autowired
		private Encrypt encrypt;
		
		@Autowired
		private NodeLoadClient nodeLoadClient;
		
		@Autowired
	    private AmqpTemplate rabbitTemplate;
		
		@Autowired
		private VaildBlockRunner vaildBlockRunner;
		/**
		 * 打包成块
		 * */
		@GetMapping("/orderChangeBlock")
		@ResponseBody
		public boolean orderChangeBlock(@RequestParam("vaildOrderReceive") String vaildReceiveOrder,@RequestParam("vaildOrderSend") String vaildOrderSend)
		{    
			 /**
			  * 1.解析成OrderInfo对象
			  * 2.创建区块block
			  * 3.交易加入区块
			  * 4.形成完整区块
			  * 5.丢入rabbitmq内
			  * 6.通知交易节点,区块已发送
			  * */
			  OrderInfo orderInfo = new OrderInfo();
			  
			  orderInfo.setReceiveTime(DateUtil.dateToString(new Date()));
			  orderInfo.setReceiveTranscation(vaildReceiveOrder);
			  orderInfo.setSendTranscation(vaildOrderSend);
			  
			  
			 /**
			  * 1.生成区块
			  * 2.序列化为JSON
			  * 3.写入账本内
			  * */
			  Block orderBlock = new Block();
			 
			  
			 /**
			  * 1.生成区块头
			  * 2.生成区块体
			  * 3.生成hash 创世交易hash为null
			  * */
			  BlockHeader blockHeader = new BlockHeader();
			  blockUtil.createBlockHeader(blockHeader);
			  BlockBody blockBody = new BlockBody();
			  blockUtil.createBlockBody(blockBody,orderInfo);
			  /**
			   * 创建hash
			   * */
			  String blockHash = encrypt.getSHA256(JSON.toJSONString(orderInfo)+DateUtil.dateToString(new Date()));
			  blockUtil.createBlock(orderBlock, blockHeader, blockBody,blockHash);
			  /**
			   * */
			  Log.info("交易已形成区块...即将发送至链上...");
			  /**
			   * 1.邮件通知节点,交易正在验证
			   * 2.发送到rabbitmq上
			   * 3.在getway网关上写一个mqClient用来监听此队列
			   * */
			  rabbitTemplate.convertAndSend("vailOrder",JSON.toJSONString(orderBlock));
			  nodeLoadClient.sendNodeOptionMail("920465415@qq.com", "交易已形成区块,已发送到链上验证...");
			  
			return true;
		}
		
		
		/**
		 * 提供一个统计结果的结果
		 * */
		public int getVaildBlockResult()
		{
			return vaildBlockRunner.vaildBlockResult;
		}
}
