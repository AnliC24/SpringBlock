package tern.block.web;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author WindC.~
 * @title 发送mq消息测试
 * @time 2019/04/22
  * */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TernBlockchainWebApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MySenderTest extends TernBlockchainWebApplicationTests{
    
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Test
	public void send()
	{
				System.out.print("123");
		//amqpTemplate.convertAndSend("myQueue","now "+new Date());
	}
}
