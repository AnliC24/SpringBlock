package tern.block.demo.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;




import tern.block.core.dto.Node;
import tern.block.demo.dto.SuperNode;
import tern.block.demo.service.LoginRedisService;


@Service("LoginRedisServiceImpl")
public class LoginRedisServiceImpl implements LoginRedisService{

	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	
	@Override
	public boolean loginNodeRedis(Node loginNode) {
		// TODO Auto-generated method stub
		 //获取登录用户的id,然后key设置成role+id
        String key="node"+loginNode.getNodeId();
        redisTemplate.opsForValue().set(key,loginNode);
        return true;
	}

	@Override
	public boolean checkLoginNodeRedis(String key) {
		// TODO Auto-generated method stub
		Node loginNode = (Node)redisTemplate.opsForValue().get(key);
        if(loginNode!=null)
        {
            return true;
        }
        return false;
	}

	@Override
	public boolean deleteLoginNodeRedis(String key) {
		// TODO Auto-generated method stub
		String role="node"+key;
        redisTemplate.opsForValue().getOperations().delete(role);
        return true;
	}

	@Override
	public boolean loginSuperNodeRedis(SuperNode loginSuperNode) {
		// TODO Auto-generated method stub
		String key="System"+loginSuperNode.getSysId();
        redisTemplate.opsForValue().set(key,loginSuperNode);
        return true;
	}

	@Override
	public boolean deleteLoginSuperNodeRedis(String key) {
		// TODO Auto-generated method stub
		String role="System"+key;
        redisTemplate.opsForValue().getOperations().delete(role);
        return true;
	}

	@Override
	public boolean checkLoginSuperNodeRedis(String key) {
		// TODO Auto-generated method stub
		SuperNode loginNode=(SuperNode)redisTemplate.opsForValue().get(key);
        if(loginNode!=null)
        {
            return true;
        }
        return false;
	}

	@Override
	public boolean loginSystemWebSocketRedis(Session session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteLoginNodeWebSocketRedis(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkLoginNodeWebSocketRedis(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node getNode(String key) {
		// TODO Auto-generated method stub
		Node node = (Node)redisTemplate.opsForValue().get(key);
		return node;
	}

	@Override
	public Map<String, Object> getNodeOnline(String key) {
		// TODO Auto-generated method stub
		Set<String> set = stringRedisTemplate.keys(key+"*");

		// 将set转成ArrayList
		List<String> list=new ArrayList<>(set);
		
		Map<String, Object> map = new HashMap<>();
		if(list.size()!=0){
		    for (String str:list){
		        //通过查到的key值获取value，并放入result
		    	Node loginNode = (Node)redisTemplate.opsForValue().get(str);
		    	map.put(str, loginNode);
		    }
		}
		return map;
	}

}
