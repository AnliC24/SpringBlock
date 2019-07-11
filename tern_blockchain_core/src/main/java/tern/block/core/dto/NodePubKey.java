package tern.block.core.dto;

import java.io.Serializable;

/**
 * 公钥池  ---  登录节点的公钥对象
 * */

public class NodePubKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private String nodeEmail;
	
	private String nodePubKey;

	public String getNodeEmail() {
		return nodeEmail;
	}

	public void setNodeEmail(String nodeEmail) {
		this.nodeEmail = nodeEmail;
	}

	public String getNodePubKey() {
		return nodePubKey;
	}

	public void setNodePubKey(String nodePubKey) {
		this.nodePubKey = nodePubKey;
	}

	public NodePubKey(String nodeEmail, String nodePubKey) {
		super();
		this.nodeEmail = nodeEmail;
		this.nodePubKey = nodePubKey;
	}

	public NodePubKey() {
		super();
	}
	
	
	
	
}
