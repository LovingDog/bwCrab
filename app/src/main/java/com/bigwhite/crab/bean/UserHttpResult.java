package com.bigwhite.crab.bean;

/**
 * Created by HDL on 2016/7/30.
 */
public class UserHttpResult<T> {
    private int code;
    private String desc;
    private Object object;

    public UserHttpResult() {
    }

    public UserHttpResult(int result, String result_info, Object data) {
        this.code = result;
        this.desc = result_info;
        this.object = data;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
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
