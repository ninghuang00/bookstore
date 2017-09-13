package cn.hn.web.formbean;

import cn.hn.domain.Customer;
import cn.hn.golobals.Gender;
import cn.hn.golobals.Preference;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by huangning on 2017/9/12.
 */
public class CustomerFormbean extends ActionForm {
    private String id;
    private String username;
    private String password;
    private String password2;
    private String gender;
    private String birthday;
    private String income;
    private String city;
    private String[] preference;
    private String email;


    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getPreference() {
        return preference;
    }

    public void setPreference(String[] preference) {
        this.preference = preference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Customer cus = new Customer();

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    /*
        表单校验
        在WebInitListener中注册类型转换器，
         */
    //private ActionErrors errors = new ActionErrors();
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        /*
        校验用户名
         */
        if(isEmpty(this.username)){
            addMessage(errors,"username","用户名不能为空");
        }else{
            if(!this.username.matches("(\\w*|\\W*|[\\u4e00-\\u9fa5]){3,9}")){
                addMessage(errors,"username","用户名只能是3~9位");
            }
            cus.setUsername(this.username);
        }
        /*
        校验密码
         */
        if(isEmpty(this.password)){
            addMessage(errors,"password","密码不能为空");
        }else{
            if(!this.password.matches("[0-9a-zA-Z]{3,9}")){
                addMessage(errors,"password","密码只能是3~9位的字母或数字");
            }

        }

        if(isEmpty(this.password2)){
            addMessage(errors,"password2","确认密码不能为空");
        }else{
            if(!this.password.equals(this.password2)){
                addMessage(errors,"password2","两次输入密码不一致");
            }
            cus.setPassword(this.password);
        }
        /*
        校验性别，性别不能为空，且需要一个枚举的实例的值
         */
        if(isEmpty(this.gender)){
            addMessage(errors,"gender","性别不能为空");
        }else {
            try{
                Gender g = Gender.valueOf(this.gender.toUpperCase());
                cus.setGender(g);

            }catch(Exception e){
                addMessage(errors,"gender","性别非法");
            }
        }
        /*
        校验生日
         */
        if(isEmpty(this.birthday)){
            addMessage(errors,"birthday","出生日期不能为空");
        }else{
            if(!this.birthday.matches("[0-9]{4}(-[0-9]{2}){2}")){
                addMessage(errors,"birthday","参考格式1995-02-22");
            }
            try{
                DateLocaleConverter converter = new DateLocaleConverter();
                Date date = (Date) converter.convert(this.birthday);
                cus.setBirthday(date);
            }catch (Exception e){
                addMessage(errors,"birthday","非法日期");
            }
        }
        /*
        收入校验，得是一个数字
         */
        if(!isEmpty(this.income)){
            try{
                double in = Double.parseDouble(this.income);
                cus.setIncome(in);
            }catch (Exception e){
                addMessage(errors,"income","收入必须是一个数字");
            }
        }
        /*
        校验爱好，不为空时要是合法的枚举值
         */
        if(this.preference != null && this.preference.length>0){
            Preference[] pres = new Preference[this.preference.length];
            int pos = 0;
            for(String pre:this.preference){
                try{
                    Preference result = Preference.valueOf(pre.toUpperCase());
                    pres[pos++] = result;
                }catch (Exception e){
                    addMessage(errors,"preference","爱好输入有误");
                }
            }
            cus.setPreferences(pres);
        }
        /*
        校验邮箱
         */
        if(isEmpty(this.email)){
            addMessage(errors,"email","邮箱地址不能为空");
        }else{
            if(!this.email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
                addMessage(errors,"email","非法邮箱地址");
            }
            cus.setEmail(this.email);
        }



        return errors;
    }

    private void addMessage(ActionErrors errors, String key, String message) {
        errors.add(key,new ActionMessage(message,false));
    }

    public  boolean isEmpty(String value){
        return (value == null || value.trim().equals(""));
    }
}
