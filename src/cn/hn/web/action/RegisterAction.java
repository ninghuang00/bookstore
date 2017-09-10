package cn.hn.web.action;

import cn.hn.domain.User;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangning on 2017/9/10.
 */
public class RegisterAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("haah");
        User user = (User)form;
        System.out.println(user.getUsername());
        System.out.println(user.getAddress());
        System.out.println(user.getEmail());
        try{
            System.out.println("chenggong");
            request.setAttribute("message","successfully");
        }catch(Exception e){
            request.setAttribute("message","failed");
        }

        return mapping.findForward("message");
    }
}
