package cn.hn.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hn.domain.Category;
import cn.hn.domain.User;
import cn.hn.service.BusinessService;
import cn.hn.service.impl.BusinessServiceImpl;
import cn.hn.utils.PrivilegeException;
import cn.hn.utils.ServiceFactory;
import cn.hn.utils.WebUtils;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/manager/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("listall")) {
			listall(request, response);
		} else if (method.equals("find")) {
			find(request, response);
		} else if (method.equals("update")) {
			update(request, response);
		} else if (method.equals("delete")) {
			delete(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} else {
			request.setAttribute("message", "do not support this method");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	/*
	 * 删除分类服务
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			//BusinessService service = new BusinessServiceImpl();
			BusinessService service = ServiceFactory.getInstance().createService("cn.hn.service.impl.BusinessServiceImpl", BusinessService.class, (User)request.getSession().getAttribute("user"));
			service.deleteCategory(id);
			request.setAttribute("message", "delete category successfully");
		} catch (Exception e) {
			if(e.getCause() instanceof PrivilegeException){
				request.setAttribute("message", e.getCause().getMessage());
				
			}
			request.setAttribute("message", "delete category failed");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/*
	 * 添加分类服务
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BusinessService service = ServiceFactory.getInstance().createService("cn.hn.service.impl.BusinessServiceImpl", BusinessService.class, (User)request.getSession().getAttribute("user"));
			Category cate = new Category();
			String description = request.getParameter("description");
			String name = request.getParameter("name");
			cate.setDescription(description);
			cate.setName(name);
			cate.setId(WebUtils.makeUUID());
			service.addCategory(cate);
			request.setAttribute("message", "add category successfully");
		} catch (Exception e) {
			if(e.getCause() instanceof PrivilegeException){
				request.setAttribute("message", e.getCause().getMessage());
				
			}else{
				request.setAttribute("message", "add category failed");
				e.printStackTrace();
			}
			
		}

		request.getRequestDispatcher("/message.jsp").forward(request, response);

	}

	/*
	 * 更新分类服务
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/*
	 * 查找分类服务
	 */
	private void find(HttpServletRequest request, HttpServletResponse response) {
		

	}

	/*
	 * 查看所有分类服务
	 */
	private void listall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BusinessService service = ServiceFactory.getInstance().createService("cn.hn.service.impl.BusinessServiceImpl", BusinessService.class, (User)request.getSession().getAttribute("user"));
			List<Category> categories = service.getAllCategory();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			if(e.getCause() instanceof PrivilegeException){
				request.setAttribute("message", e.getCause().getMessage());
				
			}else{
				e.printStackTrace();
				request.setAttribute("message", "list all categories failed");
			}
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
