package com.bigwhite.crab.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bigwhite.crab.ui.dummy.login.UserInfo;
import com.bigwhite.crab.utils.SystemUtil;

/**
 * Created by Administrator on 2017/4/19.
 */

public abstract class BaseFragment extends Fragment {

    protected long id;
    protected String token;
    private UserInfo userInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = SystemUtil.getUser();
        id = userInfo.getId();
        token = userInfo.getToken();
    }

    public BaseRequest getRequest(){
        // Should impl by sub class.
        return null;
    }

}
