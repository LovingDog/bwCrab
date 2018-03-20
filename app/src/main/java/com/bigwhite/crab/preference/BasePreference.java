package com.bigwhite.crab.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wanghanping on 2018/3/20.
 */

public class BasePreference {

    protected SharedPreferences.Editor editor;
    protected final SharedPreferences preferences;
    protected final Context context;

    public enum Preference {
        /**
         * splash和login
         */
        APP_PREFERENCE,

        /**
         * main页面
         */
        MAIN_PREFERENCE,

        /**
         * 个人主页
         */
        PERSON_PREFERENCE,
    }

    public BasePreference(Context context, Preference which) {
        this.context = context;
        String name = which.toString().toLowerCase();
        this.preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

}
