package cn.hn.web.action;

import cn.hn.domain.Order;
import cn.hn.service.impl.BusinessServiceImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huangning on 2017/9/25.
 */
public class OrderAction extends DispatchAction {
    public ActionForward listOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String state = request.getParameter("state");
        BusinessServiceImpl service = new BusinessServiceImpl();
        List<Order> orders = service.listOrder(state);
        request.setAttribute("orders", orders);
        //request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);

        return mapping.findForward("listorder");
    }

    public ActionForward detailOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BusinessServiceImpl service = new BusinessServiceImpl();
        String orderid = request.getParameter("orderid");
        Order order = service.findOrder(orderid);
        request.setAttribute("order", order);
        //request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);

        return mapping.findForward("detailorder");
    }


}
