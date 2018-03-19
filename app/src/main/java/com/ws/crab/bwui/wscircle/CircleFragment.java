package com.ws.crab.bwui.wscircle;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ws.crab.R;
import com.ws.crab.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 微圈
 * Created by Administrator on 2017/4/16.
 */

public class CircleFragment extends BaseFragment {
    @InjectView(R.id.tv_ws)
    TextView tv_ws;
    @InjectView(R.id.tv_wy)
    TextView tv_wy;
    @InjectView(R.id.tv_select_city)
    TextView tv_select_city;
    @InjectView(R.id.img_search)
    ImageView img_search;
    private WsFragment wsFragment;
    private WyFragment wyFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_circle_main,container,false);
        ButterKnife.inject(this, view);
        initView(view);
        initFragment1();
        return view;
    }
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (wsFragment == null) {
            wsFragment = new WsFragment();
            transaction.add(R.id.layout_fragment, wsFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(wsFragment);
        //提交事务
        transaction.commit();
    }
    private void initFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (wyFragment == null) {
            wyFragment = new WyFragment();
            transaction.add(R.id.layout_fragment, wyFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(wyFragment);
        //提交事务
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(wsFragment != null){
            transaction.hide(wsFragment);
        }
        if(wyFragment != null){
            transaction.hide(wyFragment);
        }

    }
    public void initView(View view) {
        tv_ws.setSelected(true);
        tv_wy.setSelected(false);
        initFragment1();
    }
    @OnClick({R.id.tv_ws, R.id.tv_wy,R.id.tv_select_city,R.id.img_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ws:
                tv_ws.setSelected(true);
                tv_wy.setSelected(false);
                initFragment1();
                break;
            case R.id.tv_wy:
                tv_ws.setSelected(false);
                tv_wy.setSelected(true);
                initFragment2();
                break;
            case R.id.tv_select_city:
                startActivity(new Intent(getActivity(),CityListActivity.class));
                break;
            case R.id.img_search:
                startActivity(new Intent(getActivity(),SearchActivity.class));
                break;
        }
    }
}
