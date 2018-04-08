package com.bigwhite.crab.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.ui.SplashActivity;
import com.bigwhite.crab.utils.SystemUtil;

/**
 * Created by Administrator on 2017/4/19.
 */

public abstract class BaseFragment extends Fragment {

    protected int id;
    protected String token;
    private AppPreference mAppPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppPreference = new AppPreference(getActivity());
        token = mAppPreference.getLoginToken();
        id = mAppPreference.getmerchantId();
    }

    public BaseRequest getRequest(){
        // Should impl by sub class.
        return null;
    }

}
