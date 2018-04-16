package com.bigwhite.crab.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.base.BaseFragment;
import com.bigwhite.crab.bean.MerchantList;
import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.ui.dummy.merchant.MerchantListInfoPresenter;
import com.bigwhite.crab.ui.dummy.merchant.MerchantsContract;
import com.bigwhite.crab.ui.dummy.order.Goods;
import com.bigwhite.crab.utils.DividerItemDecoration;
import com.bigwhite.crab.utils.Utils;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ListItemFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener,
        OnNetWorkErrorListener, MerchantsContract.MerchantListView {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private LRecyclerView mLRecyclerView;
    private int mColumnCount = 2;
    public List<Goods> mDatas;
    private MerchantListInfoPresenter mPresenter;
    private CommonAdapter mItemAdapter;
    private boolean mLoadMore;
    private int mScreenWidth;
    private int mDecoration = 30;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListItemFragment newInstance(int columnCount) {
        ListItemFragment fragment = new ListItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        mPresenter = new MerchantListInfoPresenter(this);
        mDatas = new ArrayList<>();
        mScreenWidth = Utils.getMobileInfo(getActivity()).getmScreenWidth() / 2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.item_list);
        // Set the adapter
//        mItemAdapter = new ListItemAdapter(context);
//        mItemAdapter.setData(mDatas);
        mLoadMore = false;
        mPresenter.upLoad();
        setAdapter(mDatas);
        mLRecyclerView.setPullRefreshEnabled(true);
        mLRecyclerView.setOnRefreshListener(this);
        mLRecyclerView.setOnLoadMoreListener(this);
        mLRecyclerView.setOnNetWorkErrorListener(this);
        mLRecyclerView.addItemDecoration(new DividerItemDecoration(context));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
        // TODO: Refresh the data.
        mLoadMore = false;
        mPresenter.upLoad();
    }

    @Override
    public void onLoadMore() {
        // TODO: Load more data.
        mLoadMore = true;
        mLRecyclerView.refreshComplete(mDatas.size());
    }

    @Override
    public void reload() {
        // TODO: Load more data.
        mPresenter.upLoad();

    }

    public void setAdapter(List<Goods> mDatas) {
        mItemAdapter = new CommonAdapter<Goods>(getActivity(), R.layout.layout_crab_detail, mDatas) {
            @Override
            protected void convert(ViewHolder holder, Goods good, int position) {
                holder.setText(R.id.product_price, getString(R.string.CNY) + good.getPrice());
                ((TextView) holder.getView(R.id.product_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.setText(R.id.product_integral, good.getIntegral() + "");
                holder.setText(R.id.product_description, good.getInfo());
                ImageView preview = holder.getView(R.id.product_preview);
                int width = mScreenWidth - mDecoration * 3;
                preview.setLayoutParams(new LinearLayout.LayoutParams(width, width));
                Glide.with(getActivity()).load(good.getPics()).placeholder(R.mipmap.thumbnail_bg).override(width, width).into(preview);
            }
        };
        mLRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        mLRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        LRecyclerViewAdapter LRecyclerViewAdapter = new LRecyclerViewAdapter(mItemAdapter);
        mLRecyclerView.setAdapter(LRecyclerViewAdapter);
    }

    @Override
    public void uploadSuccess(Object o) {
        if (!mLoadMore) {
            mDatas.clear();
        }
        mLRecyclerView.refreshComplete(mDatas.size());
        MerchantList orderList = (MerchantList) o;
        List<Goods> goods = orderList.getContent();
        for (Goods goods1 : goods) {
            mDatas.add(goods1);
        }
        setAdapter(goods);
    }

    @Override
    public void upLoadFail() {

    }

    @Override
    public void reFreshActivity() {

    }

    @Override
    public int getPageNow() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 10;
    }

    @Override
    public String getToke() {
        return new AppPreference(getActivity()).getLoginToken();
    }

    @Override
    public int getMerchantid() {
        return new AppPreference(getActivity()).getmerchantId();
    }
}
