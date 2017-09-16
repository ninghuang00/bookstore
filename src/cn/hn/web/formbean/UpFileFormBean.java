package cn.hn.web.formbean;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangning on 2017/9/14.
 */
public class UpFileFormBean extends ActionForm {
    private String username;
    private FormFile upfile;
    List<FormFile> list = new ArrayList<>();

    public FormFile getUpfile() {
        return upfile;
    }

    public void setUpfile(FormFile upfile) {
        this.upfile = upfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FormFile getList(int index) {
        return list.get(index);
    }

    public void setList(int index,FormFile formFile) {
        this.list.add(formFile);
    }

    public List<FormFile> getAll(){
        return this.list;
    }
}
