package cn.hn.web.formbean;

import cn.hn.domain.Book;
import cn.hn.utils.WebUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangning on 2017/9/23.
 */
public class BookFormbean extends ActionForm {
    private String id;
    private String name;
    private String author;
    private FormFile imageName;
    private String price;
    private String description;
    private String category_id;
    //维护一个book实体
    private Book book = new Book();



    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public FormFile getImageName() {
        return imageName;
    }

    public void setImageName(FormFile imageName) {
        this.imageName = imageName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if(isEmpty(this.name)){
            addMessage(errors,"name","the name of book cannot be null");
        }else{
            book.setName(this.name);
        }
        if(isEmpty(price)){
            addMessage(errors,"price","the price of a book cannot be null");
        }else{
            try {
                double pri = Double.parseDouble(price);
                book.setPrice(pri);
            }catch (Exception e){
                addMessage(errors,"price","price should be a number");
            }
        }
        book.setAuthor(author);
        book.setCategory_id(this.category_id);
        book.setId(WebUtils.makeUUID());
        //book.setImageName(this.imageName);    //在BookAction中根据上传文件的名字以及存储位置进行设置
        book.setDescription(description);


        return errors;
    }

    private void addMessage(ActionErrors errors, String key, String message) {
        errors.add(key,new ActionMessage(message,false));
    }

    public  boolean isEmpty(String value){
        return (value == null || value.trim().equals(""));
    }
}
