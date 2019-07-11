package tern.block.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 寄件人信息
 * */
public class SendOrder implements Serializable{
  

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * */
	@NotNull(message = "寄件节点不能为空")
	private String sendNodeName;
	@NotNull(message = "联系电话不能为空")
	private String sendNodeTelphone;
	@NotNull(message = "注册邮箱不能为空")
	private String sendNodeEmail;
	@NotNull(message = "寄件物品不能为空")
	private String sendNodeProduct;
	@NotNull(message = "寄件地址不能为空")
	private String sendNodeAddress;
	
	
	public SendOrder(String sendNodeName, String sendNodeTelphone, String sendNodeEmail, String sendNodeProduct,
			String sendNodeAddress) {
		super();
		this.sendNodeName = sendNodeName;
		this.sendNodeTelphone = sendNodeTelphone;
		this.sendNodeEmail = sendNodeEmail;
		this.sendNodeProduct = sendNodeProduct;
		this.sendNodeAddress = sendNodeAddress;
	}


	public String getSendNodeName() {
		return sendNodeName;
	}


	public void setSendNodeName(String sendNodeName) {
		this.sendNodeName = sendNodeName;
	}


	public String getSendNodeTelphone() {
		return sendNodeTelphone;
	}


	public void setSendNodeTelphone(String sendNodeTelphone) {
		this.sendNodeTelphone = sendNodeTelphone;
	}


	public String getSendNodeEmail() {
		return sendNodeEmail;
	}


	public void setSendNodeEmail(String sendNodeEmail) {
		this.sendNodeEmail = sendNodeEmail;
	}


	public String getSendNodeProduct() {
		return sendNodeProduct;
	}


	public void setSendNodeProduct(String sendNodeProduct) {
		this.sendNodeProduct = sendNodeProduct;
	}


	public String getSendNodeAddress() {
		return sendNodeAddress;
	}


	public void setSendNodeAddress(String sendNodeAddress) {
		this.sendNodeAddress = sendNodeAddress;
	}
	
	
	
}
