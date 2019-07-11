package tern.block.web.websocket;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;


@Component
public class P2pWebSocketClient {
    
	//用来记录客户端节点连接的个数
	private static int CLIENTCount=0;
	
	//日志记录基类
	protected Logger Log = LogManager.getLogger(this.getClass());
	
	//p2p 网络 客户端连接到服务端的Server
    private String wsUrl = "ws://localhost:8889/";
    
    //所有客户端WebSocket的连接池缓存
    private List<WebSocket> localSockets = new ArrayList<WebSocket>();

    
    
    
    public static int getCLIENTCount() {
		return CLIENTCount;
	}

	public static void setCLIENTCount(int cLIENTCount) {
		CLIENTCount = cLIENTCount;
	}

	public List<WebSocket> getLocalSockets() {
		return localSockets;
	}

	public void setLocalSockets(List<WebSocket> localSockets) {
		this.localSockets = localSockets;
	}

	/**
     * 连接到服务端
     * */
    @PostConstruct
    @Order(2)
    public void connectPeer()
    {
    	//创建WebSocket客户端
    	try {
			final WebSocketClient socketClient = new WebSocketClient(new URI(wsUrl))
			{

				@Override
				public void onClose(int i, String msg, boolean b) {
					// TODO Auto-generated method stub
					Log.info("普通节点关闭");
					localSockets.remove(this);
					CLIENTCount--;
				}

				@Override
				public void onError(Exception arg0) {
					// TODO Auto-generated method stub
					Log.info("普通节点发生异常");
					localSockets.remove(this);
					CLIENTCount--;
				}

				@Override
				public void onMessage(String msg) {
					// TODO Auto-generated method stub
					Log.info("节点向服务端发送消息"+msg);
				}

				@Override
				public void onOpen(ServerHandshake serverHandshake) {
					// TODO Auto-generated method stub
					//Log.info(this,"普通节点连接成功");
					sendMessage(this, "普通节点创建成功");
					localSockets.add(this);
					CLIENTCount++;
				}
				
			};
			//客户端开始连接服务端
			socketClient.connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("连接错误:"+e.getMessage());
		}
    }
    
    /**
     * 向服务端发送消息,当前WebSocket的远程Socket地址就是服务端
     * */
    public void sendMessage(WebSocket ws,String message)
    {
    	Log.info("发送给:"+ws.getRemoteSocketAddress().getPort()+"的P2p消息:"+message);
    	ws.send(message);
    }
    
    /**
     * 向所有连接过的服务端广播消息
     * */
    public void broatcast(String message)
    {
    	if(localSockets.size() == 0 || Strings.isNullOrEmpty(message))
    	{
    		return;
    	}
    	Log.info("开始向所有服务端广播消息");
    	for(WebSocket socket : localSockets)
    	{
    		this.sendMessage(socket, message);
    	}
    	Log.info("消息广播结束");
    }
}
