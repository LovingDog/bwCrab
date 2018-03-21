package com.bigwhite.crab.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class MyApplication extends Application {

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = getRefWatcher();
    }

    public RefWatcher getRefWatcher() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getApplicationWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.getRefWatcher();
    }
}
