package com.bigwhite.crab.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bigwhite.crab.utils.SystemUtil;

/**
 * Created by Administrator on 2017/4/19.
 */

public abstract class BaseFragment extends Fragment {

    protected long id;
    protected String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = SystemUtil.getToken();
    }

    public BaseRequest getRequest(){
        // Should impl by sub class.
        return null;
    }

}
