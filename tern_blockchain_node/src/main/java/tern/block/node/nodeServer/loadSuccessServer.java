package tern.block.node.nodeServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import tern.block.core.dto.Account;
import tern.block.core.dto.NodePubKey;
import tern.block.node.server.NodeServer;
import tern.block.node.utils.FileUtil;

/**
 * 节点加载成功 服务 
 * */
@Service
public class loadSuccessServer implements NodeServer{
   
	@Autowired
	private FileUtil fileUtil;
	
	
	@Override
	public Account nodeAccountJoinAccountPools(String nodeEmail) {
		// TODO Auto-generated method stub
		/**
		 * 读取node账本信息
		 * */
		String rootAccount = fileUtil.readText(nodeEmail+"_AccountBook.txt");
		Account account = JSON.parseObject(rootAccount, Account.class);
		/**
		 * 这里做一个账本对象类型
		 * 用来解析账本内容
		 * */
		return account;
	}

	@Override
	public boolean nodePubkeyJoinPubkeyPools(NodePubKey nodePubKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean nodeWebSocketJoinWebSocketPools() {
		// TODO Auto-generated method stub
		return false;
	}
   
}
