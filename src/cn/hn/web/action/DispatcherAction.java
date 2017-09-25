package cn.hn.web.action;

import cn.hn.domain.Category;
import cn.hn.domain.Page;
import cn.hn.domain.User;
import cn.hn.service.BusinessService;
import cn.hn.service.impl.BusinessServiceImpl;
import cn.hn.utils.Log;
import cn.hn.utils.PrivilegeException;
import cn.hn.utils.ServiceFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huangning on 2017/9/24.
 */
/*
    分发WEB-INF目录下的jsp资源
 */
public class DispatcherAction extends DispatchAction {
    public ActionForward goManagerHead(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("managerHead");
    }

    public ActionForward goManagerLeft(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("managerLeft");
    }

    public ActionForward goManagerBody(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("managerBody");
    }

    public ActionForward goListBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Log.loggerInfo("list books in bookaction");
        BusinessServiceImpl service = new BusinessServiceImpl();
        String pagenum = request.getParameter("pagenum");
        Page page = service.getPageData(pagenum, null);
        request.setAttribute("page", page);
        //request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
        return mapping.findForward("listbook");
    }

    public ActionForward goListCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            BusinessService service = ServiceFactory.getInstance().createService("cn.hn.service.impl.BusinessServiceImpl", BusinessService.class, (User) request.getSession().getAttribute("user"));
            List<Category> categories = service.getAllCategory();
            request.setAttribute("categories", categories);
            //request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
            return mapping.findForward("listcategory");
        } catch (Exception e) {
            if (e.getCause() instanceof PrivilegeException) {
                request.setAttribute("message", e.getCause().getMessage());

            } else {
                e.printStackTrace();
                request.setAttribute("message", "list all categories failed");
            }
        }
        return mapping.findForward("message");
    }
}
