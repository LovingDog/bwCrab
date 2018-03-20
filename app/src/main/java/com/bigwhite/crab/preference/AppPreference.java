package com.bigwhite.crab.preference;

import android.content.Context;

/**
 * Created by wanghanping on 2018/3/20.
 */

public class AppPreference extends BasePreference {

    public static final String LOGIN = "is_login_mode";

    public AppPreference(Context context) {
        super(context, Preference.APP_PREFERENCE);
    }

    public void setLogin(boolean isLogin) {
        editor = preferences.edit();
        editor.putBoolean(LOGIN, isLogin);
        editor.apply();
    }

    public boolean isLogin() {
        return preferences.getBoolean(LOGIN, false);
    }
}
