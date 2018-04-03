package com.bigwhite.crab.base;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public interface HttpCallBack<T> {
    void onCompleted(int type, T t);

    void onError(int type, String fail);
}
