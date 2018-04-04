package com.bigwhite.crab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bigwhite.crab.R;
import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.ui.dummy.splash.SplashController;
import com.bigwhite.crab.ui.dummy.splash.SplashOutListener;
import com.bigwhite.crab.utils.PermissionsActivity;

public class SplashActivity extends AppCompatActivity implements SplashOutListener {

    private SplashController mSplashController;
    private AppPreference mAppPreference;
    private Handler mHanlder = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHanlder.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SplashActivity.this == null) {
                    finish();
                }
                mSplashController = new SplashController(SplashActivity.this);
                mSplashController.setmSplashOutListener(SplashActivity.this);
                mAppPreference = new AppPreference(SplashActivity.this);
                mSplashController.initPermissionCheck();
            }
        }, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            mSplashController.doLoginSession();
        }
    }

    @Override
    public void PermissonCheckoutListener() {
    }

    @Override
    public void LoginSuccessListener() {
        this.finish();
        if (mAppPreference.getLoginToken().equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
        }else {
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
