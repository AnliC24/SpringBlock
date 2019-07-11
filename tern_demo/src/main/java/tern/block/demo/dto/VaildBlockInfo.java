package tern.block.demo.dto;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("VaildBlockInfo")
public class VaildBlockInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * JSON 化的寄件人信息
	 * */
	private String sendInfo;
	
	/**
	 * JSON 化的收件人信息
	 * */
	private String receiveInfo;

	public String getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(String sendInfo) {
		this.sendInfo = sendInfo;
	}

	public String getReceiveInfo() {
		return receiveInfo;
	}

	public void setReceiveInfo(String receiveInfo) {
		this.receiveInfo = receiveInfo;
	}

    
	
	
	
  
}
