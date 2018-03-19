package com.ws.crab.bwui.wscircle;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ws.crab.R;
import com.ws.crab.base.BaseActivity;
import com.ws.crab.base.MyApplication;
import com.ws.crab.utils.StringUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/7.
 * 城市列表
 */

public class CityListActivity extends BaseActivity {
    @InjectView(R.id.iv_title_back)
    ImageView img_back;
    @InjectView(R.id.tv_location)
    TextView tv_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_city_list);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        if(!StringUtils.isEmpty(MyApplication.city)){
            tv_location.setText(MyApplication.city.substring(0, MyApplication.city.length()-1));
        }
    }

    @OnClick({R.id.iv_title_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_title_back:
                finish();
                break;
        }
    }
}
