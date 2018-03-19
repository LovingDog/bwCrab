package com.ws.crab.bwui.wscircle;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ws.crab.R;
import com.ws.crab.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/6.
 */

public class GoodsDetailActivity extends BaseActivity {
    @InjectView(R.id.layout_info)
    LinearLayout layout_info;
    @InjectView(R.id.scroll_all)
    ScrollView scroll_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_goods_details);
        ButterKnife.inject(this);
         initView();
    }

    private void initView() {
        ImageView img_back = (ImageView) findViewById(R.id.iv_title_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("产品详情");
    }

    @OnClick({R.id.tv_good_info,R.id.img_go_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_good_info:
                //查看相册
                break;
            case R.id.img_go_top:
                scroll_all.fullScroll(ScrollView.FOCUS_UP);
                break;

        }
    }
}
