package com.bigwhite.crab.ui.dummy.login;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/21 0021.
 */
public class UserInfo implements Serializable {
    private long id;
    private String name;
    private String password;
    private String info;
    private String phone;
    private String token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{ id:" + id + ", name:" + name + " }";
    }
}
