package tern.block.demo.service;

import java.util.List;
import java.util.Map;

import tern.block.core.orderDTO.Order;
import tern.block.demo.dto.OrderDTO;

public interface OrderService {
    
	/**
	 * 订单信息存储
	 * */
	public boolean orderSave(OrderDTO order);
	
	/**
	 * 根据id获取订单信息
	 * */
	public Order getOrderById(int id);
	
	
	/**
	 * 获取所有未验证的交易信息
	 * */
	public List<Order> getOrderAll();
	
	/**
	 * 更改订单状态
	 * */
	public boolean updateOrderState(Map<String, Object> orderInfo);
	
	
	/**
	 * 获取节点邮箱信息，通过id
	 * */
	public String getEmailByNodeId(String sendId);
}
