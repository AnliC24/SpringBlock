package tern.block.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

/**
 * 将要被验证的交易信息
 * */
@Alias("VaildOrder")
public class VaildOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    //节点邮箱  发送邮箱
	/*@NotNull(message = "接收邮箱不能为空")*/
	private String receiveEmail;
	//交易内容
	//@NotNull(message = "交易信息不能为空")
	private String info;
	//发起时间
	//@NotNull(message = "发起时间不能为空")
	private String time;
	//发起节点邮箱
	//@NotNull(message = "发送邮箱不能为空")
	private String sendEmail;
	
	public VaildOrder() {
		super();
	}
	
	
	

	public VaildOrder(String receiveEmail, String info, String time, String sendEmail) {
		super();
		this.receiveEmail = receiveEmail;
		this.info = info;
		this.time = time;
		this.sendEmail = sendEmail;
	}




	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	
	

	
}
