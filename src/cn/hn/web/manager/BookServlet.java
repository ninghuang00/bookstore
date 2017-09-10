package cn.hn.web.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.hn.domain.Book;
import cn.hn.domain.Category;
import cn.hn.domain.Page;
import cn.hn.service.BusinessService;
import cn.hn.service.impl.BusinessServiceImpl;
import cn.hn.utils.WebUtils;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/manager/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equalsIgnoreCase("addUI")){
			addUI(request,response);
		}else if(method.equalsIgnoreCase("add")){
			add(request,response);
		}else if(method.equalsIgnoreCase("list")){
			list(request,response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceImpl service = new BusinessServiceImpl(); 
		String pagenum = request.getParameter("pagenum");
		Page page = service.getPageData(pagenum,null);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Book book = doupload(request);
			book.setId(WebUtils.makeUUID());
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.addBook(book);
			request.setAttribute("message", "add book successfully");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message","add book failed");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/*
	 * 判断表单提交的数据类型之后在进行处理
	 */
	public Book doupload(HttpServletRequest request){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		Book book = new Book();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){	//判断字段类型是否是普通类型
					String name = item.getFieldName();
					String value = item.getString("UTF-8");	//得到字段的值
					BeanUtils.setProperty(book, name, value);	//将字段封装到bean中
				}else{
					String filename = item.getName();	//如果不是普通字段则得到文件名
					String savefilename = makeFilename(filename);//filename saved in disk
					String savepath = this.getServletContext().getRealPath("/images");	//得到文件将要保存的真是路径
					InputStream in = item.getInputStream();	//得到上传的文件的输入流
					FileOutputStream out = new FileOutputStream(savepath + File.separator + savefilename);	//通过拼接爆粗路径和文件名，得到保存的真是路径
					int len = 0;
					byte[] buffer = new byte[1024];
					while((len = in.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
					
					in.close();
					out.close();
					item.delete();
					book.setImageName(savefilename);
				}
			}
			return book;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public String makeFilename(String filename){
		String ext = filename.substring(filename.lastIndexOf("."));
		return UUID.randomUUID().toString() + ext;
	
	}
	
	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
