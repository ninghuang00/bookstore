package cn.hn.domain;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;

import javax.servlet.http.HttpServletRequest;

public class User extends ActionForm {
    private String id;
    private String username;
    private String password;
    private String phonenumber;
    private String email;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*
    注册表单的表单校验
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        MessageResources mr = MessageResources.getMessageResources("cn.hn.resource.MessageResource");

        /*
        用户名不能为空，且是3到9位的字母
         */
        if (isEmpty(this.username)) {
            //errors.add("username",new ActionMessage("username can not be null",false));
            //errors.add("username",new ActionMessage("errors.username.required"));
            //errors.add("username",new ActionMessage("errors.required","用户名"));
            addMessage(errors, "username", "username can not be null");
        } else {
            if (!this.username.matches("(\\w*|\\W*|[\\u4e00-\\u9fa5]){3,9}")) {
                addMessage(errors, "username", "username need to be characters which length is 3-9");
            }
        }
        /*
        密码不能为空且长度为3-9
         */
        if (isEmpty(this.password)) {
            addMessage(errors, "password", "password can not be null");
        } else {
            if (!this.password.matches("\\w{3,9}")) {
                addMessage(errors, "password", "password need to be a string which length is 3-9");
            }
        }
        /*
        电话号码不能为空且长度为11位
         */
        if (isEmpty(this.phonenumber)) {
            addMessage(errors, "phonenumber", "phonenumber can not be null");
        } else {
            if (!this.phonenumber.matches("[0-9]{11}")) {
                addMessage(errors, "phonenumber", "phonenumber need to be a string which length is 11");
            }
        }


        return errors;

    }

    /*
    判断参数是否为空
     */

    public boolean isEmpty(String value) {
        if (value == null || value.trim().equals("")) {
            return true;
        }
        return false;
    }

    /*
    添加成功或者失败的消息,是否使用资源文件
     */
    public void addMessage(ActionErrors errors, String prop, String key, String value) {
        errors.add(prop, new ActionMessage(key, value));
    }

    public void addMessage(ActionErrors errors, String key, String message) {
        errors.add(key, new ActionMessage(message, false));
    }

}
