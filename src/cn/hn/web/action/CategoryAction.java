package cn.hn.web.action;

import cn.hn.domain.Category;
import cn.hn.domain.User;
import cn.hn.service.BusinessService;
import cn.hn.utils.PrivilegeException;
import cn.hn.utils.ServiceFactory;
import cn.hn.utils.WebUtils;
import cn.hn.web.formbean.CategoryFormbean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangning on 2017/9/25.
 */
public class CategoryAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(this.isTokenValid(request)){
            this.resetToken(request);
            try {
                BusinessService service = ServiceFactory.getInstance().createService("cn.hn.service.impl.BusinessServiceImpl",
                        BusinessService.class, (User)request.getSession().getAttribute("user"));
                CategoryFormbean formbean = (CategoryFormbean) form;
                Category cate = formbean.getCategory();

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

        }else {
            request.setAttribute("message","please do not resubmit the form");
        }

        return mapping.findForward("message");
    }
}
