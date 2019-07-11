package tern.block.node.server;



import tern.block.core.dto.Account;
import tern.block.core.dto.NodePubKey;

public interface NodeServer {
    
	   /**
	    *  1.账本加入连接池 
	    * */
	  public Account  nodeAccountJoinAccountPools(String nodeEmail);
		
	  /**
	   * 2.公钥加入连接池
	   * */
	  public boolean nodePubkeyJoinPubkeyPools(NodePubKey nodePubKey);
	    
	  /**
	   * 3.产生webSocket加入WebSocket
	   * */
	  public boolean nodeWebSocketJoinWebSocketPools();
}
