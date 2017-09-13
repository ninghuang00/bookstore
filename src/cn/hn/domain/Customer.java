package cn.hn.domain;

import cn.hn.golobals.Gender;
import cn.hn.golobals.Preference;

import java.util.Date;

/**
 * Created by huangning on 2017/9/12.
 */
public class Customer {
    private String id;
    private String username;
    private String password;
    private Gender gender;
    private Date birthday;
    private double income;
    private String city;
    private Preference[] preferences;
    private String email;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Preference[] getPreferences() {
        return preferences;
    }

    public void setPreferences(Preference[] preferences) {
        this.preferences = preferences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
