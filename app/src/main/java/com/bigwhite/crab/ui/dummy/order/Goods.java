package com.bigwhite.crab.ui.dummy.order;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class Goods {
    private int id;
    private String createtime;
    private String updatetime;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    private String info;
    private String price;
    private int integral;
    private String pics;

    public String getPics() {
        return pics;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", info='" + info + '\'' +
                ", price='" + price + '\'' +
                ", integral=" + integral +
                ", pics='" + pics + '\'' +
                ", count=" + count +
                ", remainNum=" + remainNum +
                '}';
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    private int count;
    private int remainNum;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

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



}
