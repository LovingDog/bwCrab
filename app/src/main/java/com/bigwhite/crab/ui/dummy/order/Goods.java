package com.bigwhite.crab.ui.dummy.order;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class Goods {
    private int id;
    private String info;
    private String price;
    private int integral;
    private String exchangeCode;
    private String pics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "Goods:{id = " + id + ", info = " + info + ", price = " + price + ", integral = " + integral + ", " +
                "exchangeCode = " + exchangeCode + ",pics = " + pics + "}";
    }
}
