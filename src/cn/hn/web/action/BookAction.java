package cn.hn.web.action;

import cn.hn.domain.Book;
import cn.hn.domain.Page;
import cn.hn.service.BusinessService;
import cn.hn.service.impl.BusinessServiceImpl;
import cn.hn.utils.Log;
import cn.hn.utils.ServiceFactory;
import cn.hn.web.formbean.BookFormbean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by huangning on 2017/9/16.
 */
public class BookAction extends DispatchAction {

    /*public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Log.loggerInfo("list books in bookaction");
        BusinessServiceImpl service = new BusinessServiceImpl();
        String pagenum = request.getParameter("pagenum");
        Page page = service.getPageData(pagenum,null);
        request.setAttribute("page", page);
        //request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
        return mapping.findForward("listbook");
    }*/

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Log.loggerInfo("add book");
        if(this.isTokenValid(request)){
            this.resetToken(request);
            try{
                BookFormbean formbean = (BookFormbean) form;
                Book book = formbean.getBook();
                String imageName = doupload(request);
                book.setImageName(imageName);
                BusinessService service = new BusinessServiceImpl();
                service.addBook(book);
                request.setAttribute("message","添加书籍成功");


            }catch (Exception e){
                request.setAttribute("message","添加书籍失败");
                e.printStackTrace();

            }
        }else{
            request.setAttribute("message","请勿重复提交表单");
        }


        return mapping.findForward("message");
    }

    //处理表单上传的图片文件,返回图片的名字
    public String doupload(HttpServletRequest request){

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //Book book = new Book();
        try {
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(!item.isFormField()){	//判断字段类型是否是普通类型

                    String filename = item.getName();	//如果不是普通字段则得到文件名
                    String savefilename = makeFilename(filename);//filename saved in disk
                    //String savepath = this.getServletContext().getRealPath("/images");	//得到文件将要保存的真是路径
                    String savepath = request.getServletContext().getRealPath("/images");
                    InputStream in = item.getInputStream();	//得到上传的文件的输入流
                    FileOutputStream out = new FileOutputStream(savepath + File.separator + savefilename);	//通过拼接爆粗路径和文件名，得到保存的真是路径
                    int len ;
                    byte[] buffer = new byte[1024];
                    while((len = in.read(buffer)) > 0){
                        out.write(buffer, 0, len);
                    }

                    in.close();
                    out.close();
                    item.delete();
                    return savefilename;
                    //book.setImageName(savefilename);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return null;

    }

    public String makeFilename(String filename){
        String ext = filename.substring(filename.lastIndexOf("."));
        return UUID.randomUUID().toString() + ext;

    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Log.loggerInfo("delete book");
        return null;
    }
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Log.loggerInfo("update book");
        return null;
    }

}
