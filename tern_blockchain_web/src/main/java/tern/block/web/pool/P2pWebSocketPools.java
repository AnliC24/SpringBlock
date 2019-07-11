package tern.block.web.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.esotericsoftware.minlog.Log;

/**
 * @author WindC.~
 * @time 2019/04/24 1:02
 * @title: 区块链底层---P2p网络连接池
 * @version 1.0  --  单例模式   整个服务只有一个账本连接池
 * 
 * 这个池子暂时暂缓 , 还未设计好
 * */
public class P2pWebSocketPools extends BaseConnectionPool{
       
	private static List<Map<String, Object>> p2pWebSocketPools;
	
	private static P2pWebSocketPools Instance;
	
	private P2pWebSocketPools()
	{   
		Log.info("P2p网络连接池初始化...");
		p2pWebSocketPools = new ArrayList<>();
	}
	
	public static P2pWebSocketPools getInstance()
	{
		if(Instance == null)
		{
			Log.info("P2p网络连接池启动...");
			Instance = new P2pWebSocketPools();
		}
		return Instance;
	}
	
}
