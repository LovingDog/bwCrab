package com.bigwhite.crab.bean;

import java.util.List;

/**
 * Created by HDL on 2016/7/30.
 */
public class UserHttpResultCode<Object> {
    private int code;
    private String desc;
    private List<CodeBean> object;

    public List<CodeBean> getObject() {
        return object;
    }

    public void setObject(List<CodeBean> object) {
        this.object = object;
    }

    public UserHttpResultCode() {
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "UserHttpResult{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", object=" + object +
                '}';
    }
}
