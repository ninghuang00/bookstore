package cn.hn.web.formbean;

import cn.hn.domain.Category;
import cn.hn.utils.WebUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangning on 2017/9/25.
 */
public class CategoryFormbean extends ActionForm{
    private String name;
    private String description;
    private String id;
    private Category category = new Category();

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(WebUtils.isEmpty(name)){
            WebUtils.addMessage(errors,"name","图书类型不能为空");
        }
        category.setName(name);
        category.setDescription(description);
        category.setId(WebUtils.makeUUID());

        return errors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
