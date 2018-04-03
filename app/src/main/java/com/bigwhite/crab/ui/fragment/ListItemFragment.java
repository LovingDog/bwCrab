package com.bigwhite.crab.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.base.BaseFragment;
import com.bigwhite.crab.model.MerchandiseInfo;
import com.bigwhite.crab.ui.adapter.ListItemAdapter;
import com.bigwhite.crab.utils.DividerItemDecoration;
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
        OnNetWorkErrorListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private LRecyclerView mLRecyclerView;
    private int mColumnCount = 2;
    public List<MerchandiseInfo> mDatas;
    private ListItemAdapter mItemAdapter;

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
        mDatas = new ArrayList<>();
        MerchandiseInfo item = new MerchandiseInfo();
        for (int i = 0; i < 20; i++) {
            item.setmId(i);
            item.setmPrice(1350);
            item.setmIntegral(888);
            item.setmDescription(getString(R.string.product_description));
            mDatas.add(item);
        }
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
        mLRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        CommonAdapter mItemAdapter = new CommonAdapter(context, R.layout.layout_crab_detail, mDatas) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                holder.setText(R.id.product_price, getString(R.string.CNY) + ((MerchandiseInfo) o).getmPrice());
                ((TextView) holder.getView(R.id.product_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.setText(R.id.product_integral, ((MerchandiseInfo) o).getmIntegral() + "");
                holder.setText(R.id.product_description, ((MerchandiseInfo) o).getmDescription());
                ImageView preview = holder.getView(R.id.product_preview);
                Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521629002821&di=e04f7922f0bddff18469e0335e087f9d&imgtype=0&" +
                        "src=http%3A%2F%2Fimg14.360buyimg.com%2Fn2%2Fjfs%2Ft148%2F307%2F1675000766%2F403308%2F42bc856f%2F53b4ed8cN340de8d2.jpg").into(preview);
            }
        };
        mLRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        LRecyclerViewAdapter LRecyclerViewAdapter = new LRecyclerViewAdapter(mItemAdapter);
        mLRecyclerView.setAdapter(LRecyclerViewAdapter);
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
        mLRecyclerView.refreshComplete(mDatas.size());
    }

    @Override
    public void onLoadMore() {
        // TODO: Load more data.
        mLRecyclerView.refreshComplete(mDatas.size());
    }

    @Override
    public void reload() {
        // TODO: Load more data.
        mLRecyclerView.refreshComplete(mDatas.size());
    }
}
