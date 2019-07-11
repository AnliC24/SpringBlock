package tern.block.web.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.esotericsoftware.minlog.Log;

/**
 * @author WindC.~
 * @time 2019/04/24 0:57
 * @title: 区块链底层---节点账本连接池
 * @version 1.0  --  单例模式   整个服务只有一个账本连接池
 * */
public class NodeAccountPools extends BaseConnectionPool{
    
	/**
	 * 账本连接池
	 * */
	private static List<Map<String, Object>> allNodeAccountsPools; 
	
	private static NodeAccountPools Instance;
	
	
	private NodeAccountPools()
	{
		Log.info("账本连接池启动...");
		allNodeAccountsPools = new ArrayList<>();
	}

	public static NodeAccountPools getIntance() {
		
		if(Instance == null)
		{   
			Log.info("账本连接池初始化...");
			Instance = new NodeAccountPools();
		}
		return Instance;
	}

	public  List<Map<String, Object>> getAllNodeAccountsPools() {
		return allNodeAccountsPools;
	}

}
