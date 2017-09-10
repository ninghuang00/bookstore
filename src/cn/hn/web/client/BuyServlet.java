package cn.hn.web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hn.domain.Book;
import cn.hn.domain.Cart;
import cn.hn.service.impl.BusinessServiceImpl;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/client/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//根据bookid查找图书
			String bookid = request.getParameter("bookid");
			BusinessServiceImpl service = new BusinessServiceImpl();
			Book book = service.findBook(bookid);
			
			//从session中取到cart，没有就创建一个,并存入session
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(cart == null){
				cart = new Cart();
				request.getSession().setAttribute("cart", cart);
				
			}
			//用service将book，存入cart
			service.buybook(cart,book);
			
			String method = request.getParameter("method");
			if(method != null && method.equalsIgnoreCase("remove")){
				service.removebook(cart, book);
			}
			
			//跳转购物车
			request.getRequestDispatcher("/client/buycart.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "add to buy cart failed");
			e.printStackTrace();
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
