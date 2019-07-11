package tern.block.node.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tern.block.core.dto.Account;
import tern.block.node.nodeServer.loadSuccessServer;


@Controller
public class NodeLoadController {
    
	//日志记录基类
	protected Logger Log = LogManager.getLogger(this.getClass());
	
	@Autowired
	private loadSuccessServer loadSuccessServer;
	

	
	/**
	 * 登录节点账本加入连接池
	 * */
	@GetMapping("/joinNodeAccount")
	@ResponseBody
	public Account joinNodeAccount(@RequestParam("nodeEmail") String nodeEmail)
	{   
		/**
		 * 读取节点账本,然后设计成Account类型对象,返回
		 * */
		Log.info("节点账本开始解析...");
		Account nodeAccount = loadSuccessServer.nodeAccountJoinAccountPools(nodeEmail);
		if(nodeAccount == null)
		{
			Log.info("节点账本解析异常...无法正常获取此节点账本信息");
		}
        return nodeAccount;
	}
}
