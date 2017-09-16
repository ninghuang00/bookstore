package cn.hn.web.action;

import cn.hn.domain.Customer;
import cn.hn.service.BusinessService;
import cn.hn.service.impl.BusinessServiceImpl;
import cn.hn.web.formbean.CustomerFormbean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangning on 2017/9/12.
 */
public class CustomerRegisterAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (this.isTokenValid(request)) {
            this.resetToken(request);
            /*//创建一个实体类
            Customer customer = new Customer();
            //将封装了表单提交的数据的formbean转换
            CustomerFormbean bean = (CustomerFormbean) form;
            //条用beanutils将formbean转换到实体类中
            BeanUtils.copyProperties(customer,bean);*/
            try {
                CustomerFormbean bean = (CustomerFormbean) form;
                Customer customer = bean.getCus();  //直接在表单校验的时候就将bean封装到实体类中
                BusinessService service = new BusinessServiceImpl();
                //首先查找数据库，看用户名是否已经存在
                if(service.isCustomerExist(customer)){
                    ActionErrors errors = new ActionErrors();   //struts中保存错误信息
                    errors.add("username",new ActionMessage("用户名已经存在",false));
                    this.saveErrors(request,errors);    //将信息保存在request域中，jsp中取出显示
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message","something wrong");
            }
        }else{
            request.setAttribute("message","请勿重复提交表单");
        }

        return mapping.findForward("message");
    }
}
