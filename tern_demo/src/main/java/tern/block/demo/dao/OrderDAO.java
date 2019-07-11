package tern.block.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import tern.block.core.orderDTO.Order;
import tern.block.demo.dto.OrderDTO;

@Mapper
public interface OrderDAO {
      
	/**
	 * 订单保存
	 * */
	public boolean orderSave(OrderDTO order);
	
	/**
	 * 获取订单信息，根据id
	 * */
	public Order getOrderById(int id);
	
	/**
	 * 获取所有订单信息
	 * */
	public List<Order> getOrderAll();
	
	/**
	 * 确认订单信息
	 * */
	public boolean updateOrder(Map<String, Object> orderInfo);
	
	
	/**
	 * 获取交易邮箱
	 * */
	public String getEmailBySendId(String sendID);
}
