package tern.block.web.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tern.block.core.dto.Account;

/**
 * WindC.~
 * fegin 调用node服务下 node登录成功或失败的
 * */
@FeignClient(name = "tern-blockchain-node")
public interface NodeLoadClient {
     
	@GetMapping("/joinNodeAccount")
	Account getNodeAccount(@RequestParam("nodeEmail") String nodeEmail);
}
