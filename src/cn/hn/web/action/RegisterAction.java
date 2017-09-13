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
        if (this.isTokenValid(request)) {   //首先判断表单带过来的随机码是不是和域中存的随机码是不是一样
            //request.getSession().removeAttribute("org.apache.struts.action.TOKEN"); //从session域中移除随机码
            this.resetToken(request);   //同上
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
        } else {
            request.setAttribute("message", "请勿重复提交表单");
        }

        return mapping.findForward("message");
    }
}
