package cn.hn.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hn.dao.OrderDao;
import cn.hn.domain.Book;
import cn.hn.domain.Order;
import cn.hn.domain.OrderItem;
import cn.hn.domain.User;
import cn.hn.utils.JdbcUtils;

public class OrderDoaImpl implements OrderDao {
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.OrderDao#add(cn.hn.domain.Order)
	 */
	@Override
	public void add(Order order){
		try {
			//先将Order表的基本信息插入order表中
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into orders(id,ordertime,price,state,user_id) values(?,?,?,?,?)";
			Object[] params = {order.getId(),order.getOrdertime(),order.getPrice(),order.isState(),order.getUser().getId()};
			runner.update(sql, params);
			//然后将Order中的订单项保存到orderitem表
			Set<OrderItem> set = order.getOrderitems();
			for(OrderItem item:set){
				sql = "insert into orderitem(id,quantity,price,book_id,order_id) values(?,?,?,?,?)";
				Object[] params2  = {item.getId(),item.getQuantity(),item.getPrice(),item.getBook().getId(),order.getId()};
				runner.update(sql, params2);
			}
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.OrderDao#find(java.lang.String)
	 */
	@Override
	public Order find(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			//1 找出订单的基本信息
			String sql = "select * from orders where id=?";
			Order order = (Order) runner.query(sql, id, new BeanHandler<>(Order.class));
			//2 找出订单中所有的订单项的信息
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = (List<OrderItem>) runner.query(sql, id, new BeanListHandler<>(OrderItem.class));
			//查询订单向中book的信息
			for(OrderItem item:list){
				sql = "select b.* from orderitem o,book b where o.id=? and o.book_id=b.id";
				Book book = runner.query(sql, item.getId(), new BeanHandler<>(Book.class));
				item.setBook(book);
			}
			order.getOrderitems().addAll(list);
			//3 订单所属的用户信息
			sql = "select u.* from user u,orders o where o.id=? and o.user_id=u.id";
			User user = runner.query(sql, id, new BeanHandler<>(User.class));
			order.setUser(user);
			return order;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.OrderDao#getAll(boolean)
	 */
	@Override
	public List<Order> getAll(boolean state){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=?";
			List<Order> list =  (List<Order>)runner.query(sql,state,  new BeanListHandler(Order.class));
			for(Order order:list){
				sql = "select * from user u,orders o where o.id=? and o.user_id=u.id";
				User user = runner.query(sql, order.getId(), new BeanHandler<>(User.class));
				order.setUser(user);
			}
			
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.OrderDao#update(cn.hn.domain.Order)
	 */
	@Override
	public void update(Order order){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update orders set state=? where id=?";
			Object[] params = {order.isState(),order.getId()};
			runner.update(sql,params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
