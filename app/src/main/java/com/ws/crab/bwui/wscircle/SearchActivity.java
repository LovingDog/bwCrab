package com.ws.crab.bwui.wscircle;

import android.os.Bundle;
import android.view.View;

import com.ws.crab.R;
import com.ws.crab.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索
 * Created by Administrator on 2017/5/7.
 *
 */

public class SearchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        ButterKnife.inject(this);
    }
    @OnClick({R.id.tv_cancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_cancel:
                finish();
                break;
        }
    }
}
