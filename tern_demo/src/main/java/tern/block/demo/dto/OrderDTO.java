package tern.block.demo.dto;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 订单信息加密处理
 * */
@Alias("OrderDTO")
public class OrderDTO implements Serializable{
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orderId;          //订单流水号
	private Integer sendId;           //寄件人Id
	private String orderInfoSend;     //寄件人信息
	private String orderInfoRecevie;  //收件人信息
	private String orderInfoTime;     //下单时间
	private Integer orderIdState;     //订单状态
	private Integer orderIdVaild;     //订单校验
	
	
	public OrderDTO(Integer orderId, Integer sendId, String orderInfoSend, String orderInfoRecevie, String orderInfoTime,
			Integer orderIdState, Integer orderIdVaild) {
		super();
		this.orderId = orderId;
		this.sendId = sendId;
		this.orderInfoSend = orderInfoSend;
		this.orderInfoRecevie = orderInfoRecevie;
		this.orderInfoTime = orderInfoTime;
		this.orderIdState = orderIdState;
		this.orderIdVaild = orderIdVaild;
	}
	
	
	public OrderDTO(Integer sendId, String orderInfoSend, String orderInfoRecevie, String orderInfoTime,
			Integer orderIdState, Integer orderIdVaild) {
		super();
		this.sendId = sendId;
		this.orderInfoSend = orderInfoSend;
		this.orderInfoRecevie = orderInfoRecevie;
		this.orderInfoTime = orderInfoTime;
		this.orderIdState = orderIdState;
		this.orderIdVaild = orderIdVaild;
	}


    


	public OrderDTO() {
		super();
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Integer getSendId() {
		return sendId;
	}


	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}


	public String getOrderInfoSend() {
		return orderInfoSend;
	}


	public void setOrderInfoSend(String orderInfoSend) {
		this.orderInfoSend = orderInfoSend;
	}


	public String getOrderInfoRecevie() {
		return orderInfoRecevie;
	}


	public void setOrderInfoRecevie(String orderInfoRecevie) {
		this.orderInfoRecevie = orderInfoRecevie;
	}


	public String getOrderInfoTime() {
		return orderInfoTime;
	}


	public void setOrderInfoTime(String orderInfoTime) {
		this.orderInfoTime = orderInfoTime;
	}


	public Integer getOrderIdState() {
		return orderIdState;
	}


	public void setOrderIdState(Integer orderIdState) {
		this.orderIdState = orderIdState;
	}


	public Integer getOrderIdVaild() {
		return orderIdVaild;
	}


	public void setOrderIdVaild(Integer orderIdVaild) {
		this.orderIdVaild = orderIdVaild;
	}
	
	
	
}
