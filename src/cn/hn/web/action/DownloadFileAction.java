package cn.hn.web.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by huangning on 2017/9/16.
 */
public class DownloadFileAction extends DownloadAction{
    @Override
    protected StreamInfo getStreamInfo(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        try {
            httpServletResponse.setHeader("content-disposition","attachment;filename=web.xml");
            String path = httpServletRequest.getServletContext().getRealPath("/WEB-INF/download/web.xml");
            return new FileStreamInfo("xml",new File(path));
        } catch (Exception e) {
            httpServletRequest.setAttribute("message","something wrong with download");
            e.printStackTrace();

        }
        return null;
    }
}
