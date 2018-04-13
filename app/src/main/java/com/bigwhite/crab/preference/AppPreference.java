package com.bigwhite.crab.preference;

import android.content.Context;

import java.util.UUID;


/**
 * Created by wanghanping on 2018/3/20.
 */

public class AppPreference extends BasePreference {

    public static final String LOGIN = "is_login_mode";
    public static final String UUID = "uuid";
    public static final String MERCHANT_ID = "login_merchant_id";

    public AppPreference(Context context) {
        super(context, Preference.APP_PREFERENCE);
    }

    public void setLogin(String token) {
        editor = preferences.edit();
        editor.putString(LOGIN, token);
        editor.apply();
    }
    public void setMerchantId(int id) {
        editor = preferences.edit();
        editor.putInt(MERCHANT_ID, id);
        editor.apply();
    }
    public String getLoginToken() {
        return preferences.getString(LOGIN, "");
    }


    public void setCookies(StringBuffer cookies) {
        editor = preferences.edit();
        editor.putString(UUID, cookies.toString());
        editor.apply();
    }
    public String getCookies() {
        return preferences.getString(UUID, "");
    }

    public int getmerchantId() {
        return preferences.getInt(MERCHANT_ID, 2);
    }
}
