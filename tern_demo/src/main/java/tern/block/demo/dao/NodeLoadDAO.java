package tern.block.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import tern.block.core.dto.Node;



@Mapper
public interface NodeLoadDAO {
    
	//节点信息获取
	public  Node loginNode(Map<String,Object> loginInfo);
	
	
	//节点邮箱信息获取
	public List<Map<String, Object>> vaildNodeEmail();
	
	/**
	 * 以下为节点的信息验证
	 * */
	
	
	//节点状态获取
	public int getNodeState(String nodeEmail);
	
	//密码输入次数获取
	public int getNodeBlockNum(String nodeEmail);
	
	//更新节点密码输入次数
	public boolean updateBlockNum(Map<String,Object> updateInfo);
	
	//更新节点状态
	public boolean updateNodeState(Map<String,Object> updateInfo);
	
//	/**
//	 * 以下为超级节点的信息验证
//	 * */
//	
//	//节点状态获取
//	public int getSuperNodeState(String nodeEmail);
//	
//	//密码输入次数获取
//	public int getNodeSuperBlockNum(String nodeEmail);
//	
//	//更新节点密码输入次数
//	public boolean updateSuperBlockNum(Map<String,Object> updateInfo);
//	
//	//更新节点状态
//	public boolean updateSuperNodeState(Map<String,Object> updateInfo);
	
	
	/**
	 * 获取节点信息
	 * */
	public List<Node> getAllNodeInfo();
}
