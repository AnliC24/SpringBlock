package tern.block.demo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * WindC.~
 * fegin 调用node服务下验证区块
 * */
@FeignClient(name = "tern-blockchain-node")
public interface BlockVaildClient {
	/**
	 * 进行区块验证
	 * */
	@GetMapping("/orderChangeBlock")
	boolean vaildBlockOrder(@RequestParam("vaildOrderReceive") String vaildReceiveOrder,@RequestParam("vaildOrderSend") String vaildOrderSend);
}
