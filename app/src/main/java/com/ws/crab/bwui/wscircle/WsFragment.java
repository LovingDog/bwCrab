package com.ws.crab.bwui.wscircle;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;


import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.ws.crab.R;
import com.ws.crab.adapter.BannerAdapter;
import com.ws.crab.base.BaseFragment;
import com.ws.crab.bean.WsCircleModel;
import com.ws.crab.utils.DividerItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/19.
 */

public class WsFragment extends BaseFragment {
    @InjectView(R.id.img_arrow_down)
    ImageView img_arrow_down;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);

        }
        };
    private List<WsCircleModel>mDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ws, container, false);
        ButterKnife.inject(this, view);
        initView();
        initData();
        initAction();
        for(int i = 0;i <5;i++){
            WsCircleModel circleModel =new WsCircleModel();
            mDataList.add(circleModel);
        }
        loadData(mDataList);

        // 开启新线程，2秒一次更新Banner
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(2000);
                    Message msg = new Message();
                    msg.what= 1;
                    handler.sendMessage(msg);
                }
            }
        }).start();
        return view;
    }
    @OnClick({R.id.img_arrow,R.id.img_publish,R.id.img_arrow_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_down:
               layout_top.setVisibility(View.VISIBLE);
                img_arrow_down.setVisibility(View.GONE);
                break;
            case R.id.img_arrow:
                layout_top.setVisibility(View.GONE);
                img_arrow_down.setVisibility(View.VISIBLE);
                break;

            case R.id.img_publish:
                break;

        }
    }

    private void loadData(List<WsCircleModel>mList) {
        CommonAdapter<WsCircleModel> commonAdapter = new CommonAdapter<WsCircleModel>(getActivity(), R.layout.item_circle_list, mList) {

            @Override
            protected void convert(final ViewHolder holder, WsCircleModel subjectsBean, int position) {
                holder.getConvertView().findViewById(R.id.tv_good_info).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.getConvertView().findViewById(R.id.layout_info).getVisibility() == View.VISIBLE){
                            holder.setVisible(R.id.layout_info,false);
                        }else{
                            holder.setVisible(R.id.layout_info,true);
                        }
                    }
                });
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),GoodsDetailActivity.class));
                    }
                });
            }
        };
        rv_wy_list.setAdapter(commonAdapter);
        /**
         * 配置加载更多(通用适配器里面的类哦)
         */
//        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);//加载更多的包装器(传入通用适配器)
    }

    private void initView() {
        rv_wy_list.setLayoutManager(new LinearLayoutManager(getActivity()));//设置为listview的布局
        rv_wy_list.setItemAnimator(new DefaultItemAnimator());//设置动画
        rv_wy_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));//添加分割线
        rv_wy_list.setFocusable(false);

    }

    /**
     * 初始化事件
     */
    private void initAction() {
        bannerListener = new BannerListener();
        mViewPager.setOnPageChangeListener(bannerListener);
        //取中间数来作为起始位置
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % mlist.size());
        //用来监听器
        mViewPager.setCurrentItem(index);
        mLinearLayout.getChildAt(pointIndex).setEnabled(true);
    }
    // 声明控件
    @InjectView(R.id.viewpager)
    public ViewPager mViewPager;
    public List<ImageView> mlist;
    @InjectView(R.id.tv_bannertext)
    public TextView mTextView;
    @InjectView(R.id.points)
    public LinearLayout mLinearLayout;
    @InjectView(R.id.rv_wy_list)
    public RecyclerView rv_wy_list;
    private TextView load_more;//加载更多的按钮
    @InjectView(R.id.top)
     LinearLayout layout_top;



    // 广告图素材
    private int[] bannerImages = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3};
    // 广告语
    private String[] bannerTexts = {"因为专业 所以卓越", "坚持创新 行业领跑", "诚信 专业 双赢", "精细 和谐 大气 开放"};

    // ViewPager适配器与监听器
    private BannerAdapter mAdapter;
    private BannerListener bannerListener;

    // 圆圈标志位
    private int pointIndex = 0;
    // 线程标志
    private boolean isStop = false;




    /**
     * 初始化数据
     */
    private void initData() {
        mlist = new ArrayList<ImageView>();

        View view;
        LayoutParams params;
        for (int i = 0; i < bannerImages.length; i++) {
            // 设置广告图
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(bannerImages[i]);
            mlist.add(imageView);
            // 设置圆圈点
            view = new View(getActivity());
            params = new LinearLayout.LayoutParams(5, 5);
            params.leftMargin = 10;
            view.setBackgroundResource(R.drawable.banner_circle_unchecked);
            view.setLayoutParams(params);
            view.setEnabled(false);
            mLinearLayout.addView(view);
        }
        mAdapter = new BannerAdapter(mlist);
        mViewPager.setAdapter(mAdapter);
    }


    //实现VierPager监听器接口
    class BannerListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % bannerImages.length;
            mTextView.setText(bannerTexts[newPosition]);
            mLinearLayout.getChildAt(newPosition).setEnabled(true);
            mLinearLayout.getChildAt(pointIndex).setEnabled(false);
            // 更新标志位
            pointIndex = newPosition;

        }

    }

    @Override
    public void onDestroy() {
        isStop = true;
        super.onDestroy();
    }
}
