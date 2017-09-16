package cn.hn.service;

import java.util.List;

import cn.hn.domain.*;

public interface BusinessService {

	void addCategory(Category cate);

	Category findCategory(String id);

	List<Category> getAllCategory();
	
	void deleteCategory(String id);
	
	void addBook(Book book);
	
	Book findBook(String id);
	
	List<Book> getAllBook();
	
	void deleteBook(String id);

	Page getPageData(String pagenum, String category_id);

	void buybook(Cart cart, Book book);

	void confirmOrder(String orderid);

	Order findOrder(String orderid);

	List<Order> listOrder(String state);

	Order getAllOrders();

	void addOrder(Cart cart, User user);

	void registerUser(User user);

	void removebook(Cart cart, Book book);

	List<Privilege> getPrivileges(String id);


	boolean isCustomerExist(Customer customer);
}