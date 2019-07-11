package tern.block.demo.service;

import java.util.List;
import java.util.Map;

import tern.block.core.dto.Node;


/**
 * @author WindC~
 * @Time 2019/4/16 
 * @title 节点信息业务操作-- 区块链应用层
 * @Version 1.0
 * */

public interface NodeLoadService {
   
	//节点信息获取
	public Node loginNode(Map<String,Object> loginInfo);
	

	//验证节点邮箱是否存在
	public List<Map<String, Object>> vaildNodeEmail();
	
	
	
	
	/**
	 * 以下都为验证节点的信息
	 * */
	
	//验证节点状态
	public int nodeState(String nodeEmail);
	
	//验证节点密码输入次数
	public int nodeBlockNum(String nodeEmail);
	
	//更新节点验证次数
	public boolean updateBlockNum(Map<String,Object> updateInfo);
	
	//更新节点状态
	public boolean updateNodeState(Map<String,Object> updateInfo);
	
	
	
	//查询所有节点信息
	public List<Node> getAllNode();
	
	
	
	
}
