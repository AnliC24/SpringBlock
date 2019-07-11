package tern.block.web.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esotericsoftware.minlog.Log;

/**
 * @author WindC.~
 * @time 2019/04/24 1:00
 * @title 区块链底层 -- 公钥连接池
 * @version 1.0   单例模式  整个服务只有一个连接池
 * */
public class NodePubkeyPools extends BaseConnectionPool{
       
	private static List<Map<String,Object>> pubKeyPools;
	
	private static NodePubkeyPools Instance;
	
	
	
	private NodePubkeyPools()
	{   
		Log.info("公钥连接池初始化..");
		pubKeyPools = new ArrayList<>();
	}
	
	public static NodePubkeyPools  getInstance()
	{
		if(Instance == null)
		{
			Log.info("公钥连接池启动...");
			Instance = new NodePubkeyPools();
		}
		return Instance;
	}

	public  List<Map<String, Object>> getPubKeyPools() {
		return pubKeyPools;
	}
	
	
}
