package com.bigwhite.crab.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.base.MyApplication;
import com.bigwhite.crab.ui.dummy.login.UserInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class SystemUtil {

    /**
     * Application instant.
     */
    private static MyApplication sApplication = null;

    /**
     * Get the Application instant.
     *
     * @return
     */
    public static MyApplication getApplication() {
        return sApplication;
    }

    /**
     * Set the Application instant.
     *
     * @param application
     */
    public static void setApplication(MyApplication application) {
        sApplication = application;
    }

    /**
     * Show the toast.
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (context != null && !TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Show the toast.
     *
     * @param msg
     */
    public static void showToast(String msg) {
        showToast(getApplication().getApplicationContext(), msg);
    }

    /**
     * Show the toast.
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show the toast.
     *
     * @param msg
     */
    public static void showToast(int msg) {
        showToast(getApplication().getApplicationContext(), msg);
    }

    /**
     * Get the network available state.
     *
     * @return
     */
    public static boolean isNetWork() {
        NetworkInfo networkInfo = ((ConnectivityManager) getApplication().getSystemService(Context
                .CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * Get the shared preference.
     *
     * @return
     */
    public static SharedPreferences getSharedPreference() {
        return getApplication().getSharedPreferences(GlobalField.DEFAULT_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    /**
     * Save the user to shared preference.
     *
     * @param userInfo
     */
    public static void saveUser(UserInfo userInfo) {
        SharedPreferences preferences = getSharedPreference();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(GlobalField.KEY_ID, userInfo.getId());
        editor.putString(GlobalField.KEY_NAME, userInfo.getName());
        editor.putString(GlobalField.KEY_INFO, userInfo.getInfo());
        editor.putString(GlobalField.KEY_PASSWORD, userInfo.getPassword());
        editor.putString(GlobalField.KEY_PHONE, userInfo.getPhone());
        editor.putString(GlobalField.KEY_TOKEN, userInfo.getToken());
        editor.apply();
    }

    /**
     * Get the user.
     *
     * @return
     */
    public static UserInfo getUser() {
        UserInfo userInfo = new UserInfo();
        SharedPreferences preferences = getSharedPreference();
        userInfo.setId(preferences.getLong(GlobalField.KEY_ID, 0));
        userInfo.setName(preferences.getString(GlobalField.KEY_NAME, ""));
        userInfo.setInfo(preferences.getString(GlobalField.KEY_INFO, ""));
        userInfo.setPassword(preferences.getString(GlobalField.KEY_PASSWORD, ""));
        userInfo.setPhone(preferences.getString(GlobalField.KEY_PHONE, ""));
        userInfo.setToken(preferences.getString(GlobalField.KEY_TOKEN, ""));
        return userInfo;
    }

    /**
     * MD5 security.
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
