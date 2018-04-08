package com.bigwhite.crab.preference;

import android.content.Context;

/**
 * Created by wanghanping on 2018/3/20.
 */

public class AppPreference extends BasePreference {

    public static final String LOGIN = "is_login_mode";
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

    public int getmerchantId() {
        return preferences.getInt(MERCHANT_ID, 2);
    }
}
