package com.bigwhite.crab.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/21 0021.
 */
public class UserInfo implements Serializable {
    private int id;
    private String name;
    private String iconURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{ id:" + id + ", name:" + name + " }";
    }
}
