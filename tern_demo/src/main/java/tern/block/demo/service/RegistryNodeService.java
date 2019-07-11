package tern.block.demo.service;



import java.util.List;
import java.util.Map;

import tern.block.core.dto.Node;
import tern.block.demo.dto.NodeDTO;



/**
 * 节点注册安全校验器
 * */
public interface RegistryNodeService {
	
	/**
	 * 邮箱校验
	 * */
	public String checkMail(String email);
	
	/**
	 * 节点注册
	 * */
	public boolean  registryNode(NodeDTO node);
	
	/**
	 * 节点公钥入库
	 * */
	public boolean nodePubKey(Map<String, Object> nodeInfo);
	
	/**
	 * 删除节点信息
	 * */
	public boolean deleteNodeInfo(String nodeEmail);
	
	/**
	 * 手机号码注册验证
	 * */
	public List<String> checkTel(String tel);
  
}
