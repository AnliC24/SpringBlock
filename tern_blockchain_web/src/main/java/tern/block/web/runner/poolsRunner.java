package tern.block.web.runner;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import tern.block.core.dto.Account;
import tern.block.web.pool.NodeAccountPools;
import tern.block.web.pool.NodePubkeyPools;
import tern.block.web.pool.P2pWebSocketPools;
import tern.block.web.util.FileUtil;

@Component
public class poolsRunner implements CommandLineRunner{
    
   /**
    * web服务启动后,账本连接池启动,公钥连接池启动,p2p网络连接池启动
    * 这里的连接池需要测试是否取得的对象一致
    * */
	
	@Autowired
	private FileUtil fileutil;
	
	String ROOT_PUBKEY_TARGET = "rootPubKey.txt";
	String ROOT_ACCOUNT_TARGET = "920465415@qq.com_AccountBook.txt";
	
	private  Logger Log = LogManager.getLogger(this.getClass());
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 账本连接池初始化
		 * 1.读入本地系统账本文件
		 * */
		NodeAccountPools accountPools = NodeAccountPools.getIntance();
		Log.info("账本连接池初始化成功...");
		Log.info("账本连接池对象: "+accountPools);
		Map<String, Object> superAccount = new HashMap<>();
		superAccount.put("rootAccount", this.readOriginAccount());
		accountPools.getAllNodeAccountsPools().add(superAccount);
		Log.info("系统账本已接入账本连接池");
		/**
		 * 公钥连接池初始化
		 * 1.读入系统公钥文件
		 * */
		NodePubkeyPools pubKeyPool = NodePubkeyPools.getInstance();
		Log.info("公钥连接池初始化成功...");
		Log.info("公钥连接池对象: "+pubKeyPool);
		Map<String, Object> superPub = new HashMap<>();
		superPub.put("rootPubkey",this.readOriginPubkey());
		pubKeyPool.getPubKeyPools().add(superPub);
		Log.info("系统公钥已接入公钥连接池");
		/**
		 * p2p网络连接池初始化
		 * 1.超级节点的接入等到root用户登录后接入
		 * */
		P2pWebSocketPools p2pWebSocketPool = P2pWebSocketPools.getInstance();
		Log.info("p2p网络连接池初始化成功...");
		Log.info("p2p网络连接池对象: "+p2pWebSocketPool);
	}
	
	
	/**
	 * 系统公钥读取
	 * */
	public String readOriginPubkey()
	{   	
	  Log.info("公钥开始读取解析...");
	  return fileutil.readText(ROOT_PUBKEY_TARGET);
	}
	
	/**
	 * 系统账本读取
	 * */
	public Account readOriginAccount()
	{   
		Log.info("账本开始读取解析...");
		String rootAccount = fileutil.readText(ROOT_ACCOUNT_TARGET);
		Account account = JSON.parseObject(rootAccount, Account.class);
		/**
		 * 这里做一个账本对象类型
		 * 用来解析账本内容
		 * */
		return account;
	}
	
}
