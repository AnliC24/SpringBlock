package tern.block.core.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;


/**
 * 普通节点
 * */
@Alias("Node")
public class Node implements Serializable{
	
	
     /**
	 * Spring MVC valid 校验
	 */
	 private static final long serialVersionUID = 1L;
	 private  Integer  nodeId;

	 @NotNull(message = "节点名称不能为空")
	 private  String  nodeName;
	 @NotNull(message = "性别不能为空")
	 private  String  nodeSex;
	 @NotNull(message = "注册密码不能为空")
	 private  String  nodePassword;
	 
	 @Email(message = "注册邮箱格式不正确")
	 @NotNull(message = "注册邮箱不能为空")
	 private  String  nodeEmail;
	 
	 @Size(min = 1,max = 11,message = "联系电话格式不标准")
	 @NotNull(message = "联系电话不能为空")
	 private  String  nodeTelphone;
	 private  Date  nodeRegistryTime;
	 private  String  nodePubKey;
	 private  String  nodePriKey;
	 private  Integer  nodeCompetence;
	 private  Integer  nodeState;
	 private  Integer  nodeBlockNum;
	 
	 
	 
	

	public Node(String nodeName, String nodeSex, String nodePassword, String nodeEmail, String nodeTelphone) {
		super();
		this.nodeName = nodeName;
		this.nodeSex = nodeSex;
		this.nodePassword = nodePassword;
		this.nodeEmail = nodeEmail;
		this.nodeTelphone = nodeTelphone;
	}

	public Node(Integer nodeId, String nodeName, String nodeSex, String nodePassword, String nodeEmail,
			String nodeTelphone, Date nodeRegistryTime, String nodePubKey, String nodePriKey, Integer nodeCompetence,
			Integer nodeState, Integer nodeBlockNum) {
		super();
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeSex = nodeSex;
		this.nodePassword = nodePassword;
		this.nodeEmail = nodeEmail;
		this.nodeTelphone = nodeTelphone;
		this.nodeRegistryTime = nodeRegistryTime;
		this.nodePubKey = nodePubKey;
		this.nodePriKey = nodePriKey;
		this.nodeCompetence = nodeCompetence;
		this.nodeState = nodeState;
		this.nodeBlockNum = nodeBlockNum;
	}

	public Node(String nodeName, String nodeSex, String nodePassword, String nodeEmail, String nodeTelphone,
			Date nodeRegistryTime, String nodePubKey, String nodePriKey, Integer nodeCompetence, Integer nodeState,
			Integer nodeBlockNum) {
		super();
		this.nodeName = nodeName;
		this.nodeSex = nodeSex;
		this.nodePassword = nodePassword;
		this.nodeEmail = nodeEmail;
		this.nodeTelphone = nodeTelphone;
		this.nodeRegistryTime = nodeRegistryTime;
		this.nodePubKey = nodePubKey;
		this.nodePriKey = nodePriKey;
		this.nodeCompetence = nodeCompetence;
		this.nodeState = nodeState;
		this.nodeBlockNum = nodeBlockNum;
	}
	
   
    
  

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeSex() {
		return nodeSex;
	}

	public void setNodeSex(String nodeSex) {
		this.nodeSex = nodeSex;
	}

	public String getNodePassword() {
		return nodePassword;
	}

	public void setNodePassword(String nodePassword) {
		this.nodePassword = nodePassword;
	}

	public String getNodeEmail() {
		return nodeEmail;
	}

	public void setNodeEmail(String nodeEmail) {
		this.nodeEmail = nodeEmail;
	}

	public String getNodeTelphone() {
		return nodeTelphone;
	}

	public void setNodeTelphone(String nodeTelphone) {
		this.nodeTelphone = nodeTelphone;
	}

	public Date getNodeRegistryTime() {
		return nodeRegistryTime;
	}

	public void setNodeRegistryTime(Date nodeRegistryTime) {
		this.nodeRegistryTime = nodeRegistryTime;
	}

	public String getNodePubKey() {
		return nodePubKey;
	}

	public void setNodePubKey(String nodePubKey) {
		this.nodePubKey = nodePubKey;
	}

	public String getNodePriKey() {
		return nodePriKey;
	}

	public void setNodePriKey(String nodePriKey) {
		this.nodePriKey = nodePriKey;
	}

	public Integer getNodeCompetence() {
		return nodeCompetence;
	}

	public void setNodeCompetence(Integer nodeCompetence) {
		this.nodeCompetence = nodeCompetence;
	}

	public Integer getNodeState() {
		return nodeState;
	}

	public void setNodeState(Integer nodeState) {
		this.nodeState = nodeState;
	}

	public Integer getNodeBlockNum() {
		return nodeBlockNum;
	}

	public void setNodeBlockNum(Integer nodeBlockNum) {
		this.nodeBlockNum = nodeBlockNum;
	}
	 
	 
	 
	 
	 
}
