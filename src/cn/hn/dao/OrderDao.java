package cn.hn.dao;

import java.util.List;

import cn.hn.domain.Order;

public interface OrderDao {

	void add(Order order);

	Order find(String id);

	List<Order> getAll(boolean state);

	void update(Order order);

}