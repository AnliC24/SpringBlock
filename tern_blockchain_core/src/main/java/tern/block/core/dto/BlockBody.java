package tern.block.core.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;



/**
 * 交易区块体
 * */
@Alias("BlockBody")
public class BlockBody implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<OrderInfo> orderInfos;
	public List<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(List<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}
	    
}
