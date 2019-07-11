package tern.block.core.dto;

import java.io.Serializable;

public class mqMessage implements Serializable{

	private String message;
	
	private Node node;
	
	
	

	public mqMessage() {
		super();
	}

	public mqMessage(String message, Node node) {
		super();
		this.message = message;
		this.node = node;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	
	
	
}
