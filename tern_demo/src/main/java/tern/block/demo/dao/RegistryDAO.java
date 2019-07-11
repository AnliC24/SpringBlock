package tern.block.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import tern.block.core.dto.Node;
import tern.block.demo.dto.NodeDTO;


@Mapper
public interface RegistryDAO {
   
	/**
	 * 邮箱校验
	 * */
	public String checkMailInfo(String email);
	
	/**
	 * 节点注册
	 * */
	public boolean  registryNode(NodeDTO node);
	
	/**
	 * 节点注册成功后,插入公钥信息
	 * */
	public boolean updatePubNode(Map<String, Object> nodePubKeyInfo);
	
	/**
	 * 公钥读取失败后,删除已存入节点信息
	 * */
	public boolean deleteExistsNode(String nodeEmail);
	
	/**
	 * 手机号码校验
	 * */
	public List<String> checkTelphone(String tel);
}
