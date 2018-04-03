package com.bigwhite.crab.ui.dummy.login;

import com.bigwhite.crab.base.BaseRequest;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class LoginRequest implements BaseRequest {
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
