package cn.hn.web.action;

import cn.hn.domain.User;
import cn.hn.service.impl.BusinessServiceImpl;
import cn.hn.utils.WebUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangning on 2017/9/10.
 */
public class RegisterAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) form;
        user.setId(WebUtils.makeUUID());
        try {
            BusinessServiceImpl service = new BusinessServiceImpl();
            service.registerUser(user);
            request.setAttribute("message", "successfully");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "failed");
        }

        return mapping.findForward("message");
    }
}
