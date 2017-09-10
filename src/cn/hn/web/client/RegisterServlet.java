package cn.hn.web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hn.domain.User;
import cn.hn.service.impl.BusinessServiceImpl;
import cn.hn.utils.WebUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/client/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phonenumber = request.getParameter("phonenumber");
			String address = request.getParameter("address");
			
			User user = new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setId(WebUtils.makeUUID());
			user.setPassword(password);
			user.setPhonenumber(phonenumber);
			user.setUsername(username);
			
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.registerUser(user);
			request.setAttribute("message", "register successfully");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "register failed");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
