package com.bigwhite.crab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class OrdersList implements Serializable {
    private List<OrderInfo> release = new ArrayList<>();
    private List<OrderInfo> order = new ArrayList<>();
    private List<OrderInfo> done = new ArrayList<>();

    public List<OrderInfo> getRelease() {
        return release;
    }

    public void addRelease(OrderInfo info) {
        release.add(info);
    }

    public List<OrderInfo> getOrder() {
        return order;
    }

    public void addOrder(OrderInfo info) {
        order.add(info);
    }

    public List<OrderInfo> getDone() {
        return done;
    }

    public void addDone(OrderInfo info) {
        done.add(info);
    }
}
