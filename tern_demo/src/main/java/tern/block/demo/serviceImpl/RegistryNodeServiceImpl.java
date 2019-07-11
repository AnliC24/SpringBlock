package tern.block.demo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tern.block.demo.dao.RegistryDAO;
import tern.block.demo.dto.NodeDTO;
import tern.block.demo.service.RegistryNodeService;



@Service
public class RegistryNodeServiceImpl implements RegistryNodeService{
     
	
	@Autowired
	private RegistryDAO registryDAO;
	
	/**
	 * 这里可能有格式转换异常 list <-> string 
	 * */
	@Override
	public String checkMail(String email) {
		// TODO Auto-generated method stub
		return registryDAO.checkMailInfo(email);
	}

	@Override
	public boolean registryNode(NodeDTO node) {
		// TODO Auto-generated method stub
		return registryDAO.registryNode(node);
	}

	@Override
	public boolean nodePubKey(Map<String, Object> nodeInfo) {
		// TODO Auto-generated method stub
		return registryDAO.updatePubNode(nodeInfo);
	}

	@Override
	public boolean deleteNodeInfo(String nodeEmail) {
		// TODO Auto-generated method stub
		return registryDAO.deleteExistsNode(nodeEmail);
	}

	@Override
	public List<String> checkTel(String tel) {
		// TODO Auto-generated method stub
		return registryDAO.checkTelphone(tel);
	}

}
