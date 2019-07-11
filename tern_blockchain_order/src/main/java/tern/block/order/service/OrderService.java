package tern.block.order.service;

import tern.block.core.orderDTO.Order;

public interface OrderService {
   
	/**
	 * 根据id 获取订单信息
	 * */
	public Order getOrderById();
}
