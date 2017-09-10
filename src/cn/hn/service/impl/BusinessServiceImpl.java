package cn.hn.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hn.dao.BookDao;
import cn.hn.dao.CategoryDao;
import cn.hn.dao.OrderDao;
import cn.hn.dao.UserDao;
import cn.hn.domain.Book;
import cn.hn.domain.Cart;
import cn.hn.domain.CartItem;
import cn.hn.domain.Category;
import cn.hn.domain.Order;
import cn.hn.domain.OrderItem;
import cn.hn.domain.Page;
import cn.hn.domain.Privilege;
import cn.hn.domain.User;
import cn.hn.service.BusinessService;
import cn.hn.utils.DaoFactory;
import cn.hn.utils.Permission;
import cn.hn.utils.WebUtils;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao dao = (CategoryDao) DaoFactory.getInstance().createDao("cn.hn.dao.impl.CategoryDaoImpl",
			Category.class);
	private BookDao bdao = (BookDao) DaoFactory.getInstance().createDao("cn.hn.dao.impl.BookDaoImpl", Book.class);
	private UserDao udao = (UserDao) DaoFactory.getInstance().createDao("cn.hn.dao.impl.UserDaoImpl", User.class);
	private OrderDao odao = (OrderDao) DaoFactory.getInstance().createDao("cn.hn.dao.impl.OrderDoaImpl", Order.class);

	/*
	 * 添加图书分类
	 * 
	 * @see cn.hn.service.BusinessService#addCategory(cn.hn.domain.Category)
	 */
	@Override
	@Permission("addCategory")
	public void addCategory(Category cate) {
		dao.add(cate);

	}

	/*
	 * 查找图书分类
	 * 
	 * @see cn.hn.service.impl.BusinessService#findCategory(java.lang.String)
	 */
	@Override
	@Permission("findCategory")
	public Category findCategory(String id) {
		return dao.find(id);
	}

	/*
	 * 查找所有图书分类
	 * 
	 * @see cn.hn.service.impl.BusinessService#getAllCategory()
	 */
	@Override
	public List<Category> getAllCategory() {
		return dao.getAll();
	}

	/*
	 * 删除图书分类
	 * 
	 * @see cn.hn.service.BusinessService#deleteCategory(java.lang.String)
	 */
	@Override
	public void deleteCategory(String id) {
		dao.delete(id);

	}

	@Override
	public void addBook(Book book) {
		bdao.add(book);

	}

	/*
	 * 查找图书
	 * 
	 * @see cn.hn.service.BusinessService#findBook(java.lang.String)
	 */
	@Override
	public Book findBook(String id) {
		return bdao.find(id);
	}

	/*
	 * 得到所有图书，好像没什么用处
	 * 
	 * @see cn.hn.service.BusinessService#getAllBook()
	 */
	@Override
	public List<Book> getAllBook() {
		return bdao.getAll();
	}

	/*
	 * 删除图书
	 * 
	 * @see cn.hn.service.BusinessService#deleteBook(java.lang.String)
	 */
	@Override
	public void deleteBook(String id) {
		bdao.delete(id);

	}

	/*
	 * 得到要显示的页面的所有相关信息
	 * 
	 * @see cn.hn.service.BusinessService#getPageData(java.lang.String)
	 */
	@Override
	public Page getPageData(String pagenum, String category_id) {
		int totalrecord = bdao.getTotalRecord(category_id);
		Page page = null;
		if (pagenum == null) {
			page = new Page(1, totalrecord);
		} else {
			page = new Page(Integer.parseInt(pagenum), totalrecord);
		}

		List<Book> books = bdao.getPageData(page.getStartindex(), page.getPagesize(), category_id);
		page.setList(books);
		return page;

	}

	/*
	 * 将要购买的书籍及加入购物车
	 */
	@Override
	public void buybook(Cart cart, Book book) {
		cart.add(book);
	}

	/*
	 * 将书籍从购物车中删除
	 */
	@Override
	public void removebook(Cart cart, Book book) {
		cart.remove(book);
	}

	/*
	 * 用户注册，将信息存入数据库
	 */
	@Override
	public void registerUser(User user) {
		udao.add(user);
	}

	/*
	 * 查找用户
	 */
	public User findUser(String username, String password) {
		return udao.find(username, password);
	}

	/*
	 * 添加订单，并将数据保存到数据库
	 */
	@Override
	public void addOrder(Cart cart, User user) {
		if (cart == null) {
			throw new RuntimeException("you have not bought anything");
		}
		Order order = new Order();
		order.setId(WebUtils.makeUUID());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setUser(user);
		order.setState(false);

		for (Map.Entry<String, CartItem> me : cart.getMap().entrySet()) {
			CartItem citem = me.getValue();
			OrderItem oitem = new OrderItem();
			oitem.setBook(citem.getBook());
			oitem.setId(WebUtils.makeUUID());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			order.getOrderitems().add(oitem);

		}
		odao.add(order);
	}

	/*
	 * 从数据率中查找订单
	 */
	@Override
	public Order getAllOrders() {
		return null;
	}

	/*
	 * 根据订单状态获取订单
	 */
	@Override
	public List<Order> listOrder(String state) {
		return odao.getAll(Boolean.parseBoolean(state));
	}

	/*
	 * 根据订单id查找订单
	 */
	@Override
	public Order findOrder(String orderid) {
		return odao.find(orderid);
	}

	/*
	 * 确认发货状态，更新数据库
	 */
	@Override
	public void confirmOrder(String orderid) {
		Order order = odao.find(orderid);
		order.setState(true);
		odao.update(order);
	}
	@Override
	public List<Privilege> getPrivileges(String id) {
		return udao.getPrivileges(id);
	}

}
