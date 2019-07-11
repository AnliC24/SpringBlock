package tern.block.web.mqMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import tern.block.core.dto.Account;
import tern.block.core.dto.Node;
import tern.block.core.dto.NodeLoadStates;
import tern.block.web.client.NodeLoadClient;
import tern.block.web.client.NodeMailClient;
import tern.block.web.pool.NodeAccountPools;
import tern.block.web.pool.NodePubkeyPools;



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
	 	
	 	@Autowired
	 	private NodeLoadClient NodeLoadClient;
	 	
	 	@Autowired
	 	private NodeMailClient NodeMailClient;
	
	 	//@RabbitListener(queues="myQueue")
	 	//自动创建队列
	 	//@RabbitListener(queuesToDeclare = @Queue("myQueue"))
	 	//3.自动创建,Exchange和Queue绑定
	 	@RabbitListener(bindings = @QueueBinding(
	 			value = @Queue("nodeLoad"),
	 			exchange = @Exchange("nodeExchange")
	 			))
		public void process(String message)
		{
	 		LOG.info("MqReceiver time: "+new Date());
	 		
	 		NodeLoadStates nodeLoadStates = JSON.parseObject(message, NodeLoadStates.class);
	 		Node  node = nodeLoadStates.getNode();
	 		try {
				
	 			if("登录成功".equals(nodeLoadStates.getNodeLoadStatus()))
		 		{
		 			/**
		 			 * 调用node fegin 服务,获取到对象,并加入连接池内
		 			 * */
		 			Account account = NodeLoadClient.getNodeAccount(node.getNodeEmail());
		 			if(account == null)
		 			{
		 				LOG.info(node.getNodeEmail()+"节点账本--解析失败");
		 				return;
		 			}
		 			Map<String, Object> nodeAccount = new HashMap<>();
		 			nodeAccount.put(node.getNodeEmail(), account);
		 			NodeAccountPools.getIntance().getAllNodeAccountsPools().add(nodeAccount);
		 			LOG.info(node.getNodeEmail()+"节点账本--成功接入账本连接池");
		 			Map<String, Object> nodePubKey = new HashMap<>();
		 			if(node.getNodePubKey() == null)
		 			{
		 				LOG.info(node.getNodeEmail()+"节点公钥开始解析...");
		 				nodePubKey.put(node.getNodeEmail(), node.getNodePubKey());
		 				LOG.info("节点公钥未填充,标识为空...请登录节点补充节点信息...");
		 			}else{
		 				LOG.info(node.getNodeEmail()+"节点公钥开始解析...");
		 				nodePubKey.put(node.getNodeEmail(), node.getNodePubKey());
		 				LOG.info(node.getNodeEmail()+"节点公钥--成功接入公钥连接池");
		 			}
		 			NodePubkeyPools.getInstance().getPubKeyPools().add(nodePubKey);
		 		}
	 			
	 			
			} catch (Exception e) {
				// TODO: handle exception
				LOG.info(e.getMessage());
				return;
			}
		}
}
