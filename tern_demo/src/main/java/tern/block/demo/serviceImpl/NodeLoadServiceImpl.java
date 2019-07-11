package tern.block.demo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tern.block.core.dto.Node;
import tern.block.demo.service.NodeLoadService;
import tern.block.demo.dao.NodeLoadDAO;


/**
 * @author WindC~
 * @Time 2019/4/16 
 * @title 节点登录信息验证 -- 区块链业务
 * @Version 1.0
 * */


@Service("NodeLoadServiceImpl")
public class NodeLoadServiceImpl implements NodeLoadService{
 
	@Autowired
	private NodeLoadDAO NodeLoadDAO;

	@Override
	public Node loginNode(Map<String, Object> loginInfo) {
		// TODO Auto-generated method stub
		return NodeLoadDAO.loginNode(loginInfo);
	}

	@Override
	public List<Map<String, Object>> vaildNodeEmail() {
		// TODO Auto-generated method stub
		return NodeLoadDAO.vaildNodeEmail();
	}

	@Override
	public int nodeState(String nodeEmail) {
		// TODO Auto-generated method stub
		return NodeLoadDAO.getNodeState(nodeEmail);
	}

	@Override
	public int nodeBlockNum(String nodeEmail) {
		// TODO Auto-generated method stub
		return NodeLoadDAO.getNodeBlockNum(nodeEmail);
	}

	@Override
	public boolean updateBlockNum(Map<String, Object> updateInfo) {
		// TODO Auto-generated method stub
		return NodeLoadDAO.updateBlockNum(updateInfo);
	}

	@Override
	public boolean updateNodeState(Map<String, Object> updateInfo) {
		// TODO Auto-generated method stub
		return NodeLoadDAO.updateNodeState(updateInfo);
	}

	@Override
	public List<Node> getAllNode() {
		// TODO Auto-generated method stub
		return NodeLoadDAO.getAllNodeInfo();
	}
	
}
