package tern.block.demo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * WindC.~
 * fegin 调用node服务下 node登录成功或失败的
 * */
@FeignClient(name = "tern-blockchain-sys")
public interface NodeLoadClient {
     
	/**
	 * 登录成功后,发送通知
	 * */
	@GetMapping("/sendNodeLoadSuccessMail")
	boolean sendSuccessMail(@RequestParam("nodeEmail") String nodeEmail);
	
	/**
	 * 节点操作通知
	 * */
	@GetMapping("/sendNodeOptionMail")
	boolean sendNodeOptionMail(@RequestParam("nodeEmail") String nodeEmail,@RequestParam("nodeMessage") String nodeMessage);
}
