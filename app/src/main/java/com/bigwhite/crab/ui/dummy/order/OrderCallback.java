package com.bigwhite.crab.ui.dummy.order;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public interface OrderCallback {
    void onCompleted(int status, OrderList list);
}
