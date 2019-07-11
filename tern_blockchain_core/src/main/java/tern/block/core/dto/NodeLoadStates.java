package tern.block.core.dto;

import java.io.Serializable;

/**
 * 登录节点对象状态
 * */

public class NodeLoadStates implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  nodeLoadStatus;
	private Node node;
	public String getNodeLoadStatus() {
		return nodeLoadStatus;
	}
	public void setNodeLoadStatus(String nodeLoadStatus) {
		this.nodeLoadStatus = nodeLoadStatus;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public NodeLoadStates() {
		super();
	}
	
	

}
