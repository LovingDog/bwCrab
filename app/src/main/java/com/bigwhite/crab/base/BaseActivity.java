package com.bigwhite.crab.base;

import android.app.Activity;
import android.os.Bundle;

import com.bigwhite.crab.utils.ZTLUtils;


/**
 * Created by Administrator on 2016/7/22.
 */
public class BaseActivity extends Activity {
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        new ZTLUtils(mActivity).setTranslucentStatus();
        initViews();
    }


    public void initViews() {

    }
}
