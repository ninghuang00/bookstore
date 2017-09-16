package cn.hn.web.action;

import cn.hn.utils.Log;
import cn.hn.web.formbean.UpFileFormBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by huangning on 2017/9/14.
 */
public class UpFileAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UpFileFormBean formbean = (UpFileFormBean) form;
        String username = formbean.getUsername();
        FormFile file = formbean.getUpfile();

        String filename = file.getFileName();
        InputStream is = file.getInputStream();
        String savePath =request.getServletContext().getRealPath("/WEB-INF/upload") + File.separator + filename;//要得到真实路径才行
        //String savePath = "/Users/huangning/Desktop/" + filename;
        FileOutputStream out = new FileOutputStream(savePath);
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = is.read(buffer))>0){
            out.write(buffer,0,len);
        }
        out.close();
        is.close();
        Log.loggerInfo("getContextPath is " + savePath);
        /*
        List<FormFile> list = formbean.getAll();
        for (FormFile file : list) {
            String filename = file.getFileName();
            InputStream is = file.getInputStream();
            String savePath =request.getServletContext().getContextPath() + "/WEB-INF/download/" + filename;

            Log.loggerInfo("getContextPath is " + savePath);
            FileOutputStream out = new FileOutputStream(savePath);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            is.close();
        }*/

        return super.execute(mapping, form, request, response);
    }
}
