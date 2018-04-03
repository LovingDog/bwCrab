package com.bigwhite.crab.bean;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class JsonResultBean<T> {
    private T object;
    private int code;
    private String desc;
    private String result;

    public T getObject() {
        return this.object;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getResult() {
        return this.result;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
