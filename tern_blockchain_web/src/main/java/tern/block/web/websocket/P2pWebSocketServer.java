package tern.block.web.websocket;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * @author WindC.~
 * @time 2019/04/20
 * @version 1.0
 * @title 区块链底层--p2p网络构建
 * */
@ServerEndpoint("/websocket")
@Component
public class P2pWebSocketServer {
       
	    //本机socket测试端口
	    private int port = 8889;
	    //日志记录基类
		protected Logger Log = LogManager.getLogger(this.getClass());
		
		/**
		 * 所有连接到服务端的WebSocket缓存器
		 * */
		private List<WebSocket> localSockets = new ArrayList<WebSocket>();

		public List<WebSocket> getLocalSockets() {
			return localSockets;
		}

		public void setLocalSockets(List<WebSocket> localSockets) {
			this.localSockets = localSockets;
		}
		
		/**
		 * 初始化P2P WebSocket 端口
		 * */
		@PostConstruct
		@Order(1)
		public void initServer()
		{
			/**
			 * 
			 * */
			final WebSocketServer socketServer = new WebSocketServer(new InetSocketAddress(port))
			{
				/**
				 * 重写5个事件方法,事件发生时触发对应方法
				 * */
				
				//创建连接关闭时触发
				@Override
				public void onClose(WebSocket webSocket, int i, String s, boolean b) {
					// TODO Auto-generated method stub
					Log.info(webSocket.getRemoteSocketAddress()+"客户端与服务器断开连接");
					//当客户端断开连接时,WebSocket连接池删除该连接
					localSockets.remove(webSocket);
				}
                //发生错误时触发
				@Override
				public void onError(WebSocket webSocket, Exception e) {
					// TODO Auto-generated method stub
					Log.info(webSocket.getRemoteSocketAddress()+"客户端连接错误");
					localSockets.remove(webSocket);
					
				}
                //收到客户端发来的信息时触发
				@Override
				public void onMessage(WebSocket webSocket, String msg) {
					// TODO Auto-generated method stub
					Log.info("超级节点接收到客户端消息"+msg);
					sendMessage(webSocket, "收到消息");
				}
				//创建连接打开时触发
				@Override
				public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
					// TODO Auto-generated method stub
				 sendMessage(webSocket, "超级节点服务端创建成功");
				 //当成功创建一个webSocket连接时,将该连接加入连接池
				 localSockets.add(webSocket);
				}
				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					Log.info("超级节点Server启动....");
				}
			};//内部类要加分号
			socketServer.start();
			Log.info("超级节点监听socketServer端口:"+port);
		}
		/**
		 * 向连接到本机的某客户端发生消息
		 * */
		public void sendMessage(WebSocket ws, String message)
		{
			Log.info("发送给"+ws.getRemoteSocketAddress().getPort()+"的p2p消息是:"+message);
			ws.send(message);
		}
		
		/**
		 * 向所有连接到本机的客户端广播消息
		 * */
		public void broatcast(String message)
		{
			if(localSockets.size() == 0 || Strings.isNullOrEmpty(message))
			{
				return;
			}
			Log.info("开始广播待验证区块信息");
			for(WebSocket socket : localSockets)
			{
				this.sendMessage(socket, message);
			}
			Log.info("消息已广播全网");	
		}
		
		
		
}
