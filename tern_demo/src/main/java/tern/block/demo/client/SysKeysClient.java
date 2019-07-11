package tern.block.demo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * sys服务 调用
 * */
@FeignClient(name = "tern-blockchain-sys")
public interface SysKeysClient {
   
	/**
	 * 生成本地公私钥对
	 * */
	@GetMapping("/createKeys")
	boolean sysKeysCreate(@RequestParam("nodeName") String nodeName);
	
	/**
	 * 本地生成账本文件  
	 * createAccountBook
	 * */
	@GetMapping("/createAccountBook")
	String sysAccountCreate(@RequestParam("nodeEmail") String nodeEmail);
	
	
}
