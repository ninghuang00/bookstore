package cn.hn.web.action;

import cn.hn.domain.Category;
import cn.hn.service.impl.BusinessServiceImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huangning on 2017/9/23.
 */
public class BookUIAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.saveToken(request); //生成随机数,防止表单重复提交
        BusinessServiceImpl service = new BusinessServiceImpl();
        List<Category> categories = service.getAllCategory();
        request.setAttribute("categories", categories);
        return mapping.findForward("addbook");
    }
}
