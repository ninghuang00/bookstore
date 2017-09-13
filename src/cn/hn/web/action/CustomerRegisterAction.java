package cn.hn.web.action;

import cn.hn.domain.Customer;
import cn.hn.web.formbean.CustomerFormbean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangning on 2017/9/12.
 */
public class CustomerRegisterAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        /*//创建一个实体类
        Customer customer = new Customer();
        //将封装了表单提交的数据的formbean转换
        CustomerFormbean bean = (CustomerFormbean) form;
        //条用beanutils将formbean转换到实体类中
        BeanUtils.copyProperties(customer,bean);*/
        CustomerFormbean bean = (CustomerFormbean) form;
        Customer customer = bean.getCus();

        System.out.println(customer);

        return null;
    }
}
