package tern.block.demo.service;

import tern.block.core.dto.Node;
import tern.block.demo.dto.SuperNode;

import java.util.Map;

import javax.websocket.Session;


public interface LoginRedisService {
   
	//登录用户存进redis内
    public boolean loginNodeRedis(Node loginNode);

    //扫描redis缓存,检查当前登录的用户
    public boolean checkLoginNodeRedis(String key);

    //删除退出登录的用户信息
    public boolean deleteLoginNodeRedis(String key);

    //登录管理员存进redis内
    public boolean loginSuperNodeRedis(SuperNode loginSuperNode);

    //删除退出登录的管理员信息
    public boolean deleteLoginSuperNodeRedis(String key);

    //扫描redis缓存，检查当前登录的节点
    public boolean checkLoginSuperNodeRedis(String key);

    //登录管理员websocket存进redis内
    public boolean loginSystemWebSocketRedis(Session session);

    //删除退出登录管理员的websocket
    public boolean deleteLoginNodeWebSocketRedis(String key);

    //扫描redis缓存,检查当前的websocket是否已经存在
    public boolean checkLoginNodeWebSocketRedis(String key);
    
    //取出对应key的节点信息
    public  Node getNode(String key);
    
    //根据node + * 模糊查询出所有在线节点
    public Map<String, Object> getNodeOnline(String key);
    
	
}
