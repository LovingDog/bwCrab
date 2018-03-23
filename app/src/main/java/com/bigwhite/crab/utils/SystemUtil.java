package com.bigwhite.crab.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import com.bigwhite.crab.base.MyApplication;

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
}
