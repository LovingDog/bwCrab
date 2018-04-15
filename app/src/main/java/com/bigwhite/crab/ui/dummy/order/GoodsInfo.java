package com.bigwhite.crab.ui.dummy.order;

import java.util.List;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class GoodsInfo {
    private long id;
    private String createtime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUptatime() {
        return updatetime;
    }

    public void setUptatime(String uptatime) {
        this.updatetime = uptatime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKuaidiNum() {
        return kuaidiNum;
    }

    public void setKuaidiNum(String kuaidiNum) {
        this.kuaidiNum = kuaidiNum;
    }

    private String updatetime;
    private String username;
    private String phone;
    private String address;
    private Goods goods;

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    private int status;
    private String kuaidiNum;

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + id +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", goods=" + goods +
                ", status=" + status +
                ", kuaidiNum='" + kuaidiNum + '\'' +
                '}';
    }
}
