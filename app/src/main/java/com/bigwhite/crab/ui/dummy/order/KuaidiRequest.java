package com.bigwhite.crab.ui.dummy.order;

import com.bigwhite.crab.base.BaseRequest;

/**
 * Created by heqiang on 4/19/18.
 */

public class KuaidiRequest implements BaseRequest {
    private long id;
    private String kuaidiNum;
    private String token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKuaidiNum() {
        return kuaidiNum;
    }

    public void setKuaidiNum(String kuaidiNum) {
        this.kuaidiNum = kuaidiNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}