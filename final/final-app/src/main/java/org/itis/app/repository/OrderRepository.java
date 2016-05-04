package org.itis.app.repository;

import java.util.List;
import org.itis.app.entity.Order;

public interface OrderRepository {
	public List<Order> getAllOrders();
	public void saveOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(int orderId);
	public Order getOrderById(int orderId);
}
