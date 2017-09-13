package cn.hn.web.listener; /**
 * Created by huangning on 2017/9/12.
 */

import cn.hn.golobals.Gender;
import cn.hn.golobals.Preference;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener()
public class WebInitListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public WebInitListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        //注册转换日期的转换器
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> type, Object value) {
                if(value == null){
                    return null;
                }

                if(value instanceof  String){
                    String d = (String)value;
                    if(d.trim().equals("")){
                        return null;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        return (T) sdf.parse(d);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
                throw new RuntimeException("传入的类型不是String");
            }
        }, Date.class);

        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> type, Object value) {
                if(value == null){
                    return null;
                }
                //因为是枚举类型，传进来的要么是空，要么就是枚举类型中的一个
                return (T) Gender.valueOf(((String)value).toUpperCase());

            }
        }, Gender.class);
        //转换枚举数组
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> type, Object value) {
                if(value == null){
                    return null;
                }

                String pres[] = (String[])value;
                Preference[] ps = new Preference[pres.length];
                for(int i = 0; i < pres.length; i ++){
                    ps[i] = Preference.valueOf(pres[i].toUpperCase());

                }

                return (T) ps;
            }
        }, Preference[].class);

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
