package tern.block.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 收件人信息
 * */
public class RecevieOrder implements Serializable{
   
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * */
	@NotNull(message = "收件节点不能为空")
	private String receiveNodeName;
	@NotNull(message = "联系电话不能为空")
	private String receiveNodeTelphone;
	@NotNull(message = "节点邮箱不能为空")
	private String receiveNodeEmail;
	@NotNull(message = "收件联系地址不能为空")
	private String recevieNodeAddress;
	@NotNull(message = "收件时间不能为空")
	private String recevieNodepickTime;
	
	
	public RecevieOrder(String receiveNodeName, String receiveNodeTelphone, String receiveNodeEmail,
			String recevieNodeAddress, String recevieNodepickTime) {
		super();
		this.receiveNodeName = receiveNodeName;
		this.receiveNodeTelphone = receiveNodeTelphone;
		this.receiveNodeEmail = receiveNodeEmail;
		this.recevieNodeAddress = recevieNodeAddress;
		this.recevieNodepickTime = recevieNodepickTime;
	}


	public String getReceiveNodeName() {
		return receiveNodeName;
	}


	public void setReceiveNodeName(String receiveNodeName) {
		this.receiveNodeName = receiveNodeName;
	}


	public String getReceiveNodeTelphone() {
		return receiveNodeTelphone;
	}


	public void setReceiveNodeTelphone(String receiveNodeTelphone) {
		this.receiveNodeTelphone = receiveNodeTelphone;
	}


	public String getReceiveNodeEmail() {
		return receiveNodeEmail;
	}


	public void setReceiveNodeEmail(String receiveNodeEmail) {
		this.receiveNodeEmail = receiveNodeEmail;
	}


	public String getRecevieNodeAddress() {
		return recevieNodeAddress;
	}


	public void setRecevieNodeAddress(String recevieNodeAddress) {
		this.recevieNodeAddress = recevieNodeAddress;
	}


	public String getRecevieNodepickTime() {
		return recevieNodepickTime;
	}


	public void setRecevieNodepickTime(String recevieNodepickTime) {
		this.recevieNodepickTime = recevieNodepickTime;
	}
	
	
	
}
