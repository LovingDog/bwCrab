package com.bigwhite.crab.ui.dummy.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import com.bigwhite.crab.utils.PermissionsActivity;
import com.bigwhite.crab.utils.PermissionsChecker;

/**
 * Created by wanghanping on 2018/3/20.
 */

public class SplashController {
    private Activity mContext;
    private PermissionsChecker mChecker;
    private SplashOutListener mSplashOutListener;

    public SplashController(Activity context) {
        this.mContext = context;
    }

    public void setmSplashOutListener(SplashOutListener splashOutListener){
        this.mSplashOutListener = splashOutListener;
    }

    public void initPermissionCheck(){
        mChecker = new PermissionsChecker(mContext);
        String[] graint = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE
                ,Manifest.permission.READ_EXTERNAL_STORAGE};
        if (mChecker.lacksPermissions(graint)) {
            Intent intent = new Intent(mContext, PermissionsActivity.class);
            intent.putExtra(PermissionsActivity.EXTRA_PERMISSIONS, graint);
            ActivityCompat.startActivityForResult(mContext, intent, 102, null);
        }else {
            mSplashOutListener.PermissonCheckoutListener();
            doLoginSession();
        }
    }

    public void doLoginSession(){
        //do login
        mSplashOutListener.LoginSuccessListener();
    }
}
