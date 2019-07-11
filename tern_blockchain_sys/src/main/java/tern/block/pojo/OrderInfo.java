package tern.block.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 交易信息
 * */

@Alias("OrderInfo")
public class OrderInfo implements Serializable{


	private static final long serialVersionUID = 1L;
	
	/**
	 * 交易信息JSON化数据
	 * 寄件人信息
	 * */
    private String SendTranscation;
    
    /**
     * 交易信息JSON化数据
     * 收件人信息
     * */
    private String ReceiveTranscation;
    
    /**
     * 交易信息JSON化数据
     * 收件时间
     * */
    private String ReceiveTime;
    /**
     * 时间戳
     * */
    private Long timeStamp;
    
    /**
     * 公钥
     * */
    private String publicKey;
    
    /**
     * 签名
     * */
    private String sign;
    
    /**
     * 该操作的哈希
     * */
    private String hash;

	

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSendTranscation() {
		return SendTranscation;
	}

	public void setSendTranscation(String sendTranscation) {
		SendTranscation = sendTranscation;
	}

	public String getReceiveTranscation() {
		return ReceiveTranscation;
	}

	public void setReceiveTranscation(String receiveTranscation) {
		ReceiveTranscation = receiveTranscation;
	}

	public String getReceiveTime() {
		return ReceiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		ReceiveTime = receiveTime;
	}
    
    
}
