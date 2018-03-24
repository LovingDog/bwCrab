package com.bigwhite.crab.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class OrderInfo implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String point;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
