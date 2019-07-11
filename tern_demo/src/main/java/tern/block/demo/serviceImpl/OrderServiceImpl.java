package tern.block.demo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tern.block.core.orderDTO.Order;
import tern.block.demo.dao.OrderDAO;
import tern.block.demo.dto.OrderDTO;
import tern.block.demo.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{
   
	@Autowired
	private OrderDAO orderDAO;
	
	@Transactional
	@Override
	public boolean orderSave(OrderDTO order) {
		// TODO Auto-generated method stub
		return orderDAO.orderSave(order);
	}

	@Override
	public Order getOrderById(int id) {
		// TODO Auto-generated method stub
		return orderDAO.getOrderById(id);
	}

	@Override
	public List<Order> getOrderAll() {	
		// TODO Auto-generated method stub
		return orderDAO.getOrderAll();
	}

	@Override
	public boolean updateOrderState(Map<String, Object> orderInfo) {
		// TODO Auto-generated method stub
		return orderDAO.updateOrder(orderInfo);
	}

	@Override
	public String getEmailByNodeId(String sendId) {
		// TODO Auto-generated method stub
		return orderDAO.getEmailBySendId(sendId);
	}

}
