package com.ws.crab.bwui.wscircle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ws.crab.R;
import com.ws.crab.base.BaseFragment;
import com.ws.crab.bean.WsCircleModel;
import com.ws.crab.utils.DividerItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2017/4/19.
 */

public class WyFragment extends BaseFragment {
    @InjectView(R.id.rv_wy_list)
    RecyclerView rvWyList;
    @InjectView(R.id.store_house_ptr_frame)
    PtrFrameLayout storeHousePtrFrame;
    private TextView load_more;//加载更多的按钮

    private List<WsCircleModel>mDataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_wy,container,false);
        ButterKnife.inject(this, view);
        initView(view);
        for(int i = 0;i <8;i++){
            WsCircleModel circleModel  = new WsCircleModel();
            mDataList.add(circleModel);
        }
        loadData(mDataList);
        return view;
    }

    private void initView(View view){
        initPtr();
        rvWyList.setLayoutManager(new LinearLayoutManager(getActivity()));//设置为listview的布局
        rvWyList.setItemAnimator(new DefaultItemAnimator());//设置动画
        rvWyList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));//添加分割线
    };
    /**
     * 初始化(配置)下拉刷新组件
     */
    private void initPtr() {
        //下面是一些基础的配置,直接拿来用就可以 不用深究
        storeHousePtrFrame.setResistance(1.7f);
        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        storeHousePtrFrame.setDurationToClose(200);
        storeHousePtrFrame.setDurationToCloseHeader(1000);
        storeHousePtrFrame.setPullToRefresh(false);
        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
        storeHousePtrFrame.setHeaderView(header);//添加头
        storeHousePtrFrame.addPtrUIHandler(header);//同时也要加上这一句
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                mMoviePresenter.loadMoreMovie();//下拉刷新的时候加载数据
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                    }
                }, 150);//为了增加用户体验 延迟0.15s再通知刷新结束
            }
        });

    }



    private void loadData(List<WsCircleModel> mList) {
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

//                holder.setText(R.id.tv_movie_title, title);//设置电影名
//                String doc = "";
//                for (Movies.SubjectsBean.DirectorsBean directorsBean : subjectsBean.getDirectors()) {
//                    doc += directorsBean.getName() + "  ";
//                }
//                holder.setText(R.id.tv_movie_doc, "导演:" + doc);
//                String casts = "";
//                for (Movies.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
//                    casts += castsBean.getName() + "  ";
//                }
//
//                holder.setText(R.id.tv_movie_art, "主演:" + casts);
//                String genres = "";
//                for (String genre : subjectsBean.getGenres()) {
//                    genres += genre + " ";
//                }
//                holder.setText(R.id.tv_movie_type, subjectsBean.getYear() + " / " + genres);//年份+分级
//                holder.setText(R.id.tv_movie_grade, subjectsBean.getRating().getAverage() + "");//评分
//                ImageView iv_pic = holder.getView(R.id.iv_movie_pic);
//                Glide.with(mActivity)
//                        .load(subjectsBean.getImages().getSmall())
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)//加快显示速度---缓存在本地磁盘
//                        .into(iv_pic);//图片
            }
        };
        rvWyList.setAdapter(commonAdapter);
        /**
         * 配置加载更多(通用适配器里面的类哦)
         */
//        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);//加载更多的包装器(传入通用适配器)
    }
}
