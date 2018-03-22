package com.bigwhite.crab.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.model.CustomerOrderInfo;
import com.bigwhite.crab.model.UserInfo;
import com.bigwhite.crab.ui.adapter.UserAdapter;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Fragment to show the user.
 */
public class UserFragment extends Fragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener,
        OnNetWorkErrorListener {

    private TextView mReleaseCount;
    private TextView mOrdersCount;
    private TextView mDoneCount;
    private TextView mTitleView;
    private TextView mUserName;
    private CircleImageView mUserIcon;
    private LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLAdapter;
    private UserAdapter mUserAdapter;

    private UserInfo mUserInfo;
    private List<CustomerOrderInfo> mOrdersList;
    private int mCurrentType;
    private static final int TYPE_RELEASE_MODEL = 0;
    private static final int TYPE_ORDER_MODEL = 1;
    private static final int TYPE_DONE_MODEL = 2;

    public UserFragment() {
        // Required empty public constructor
        mUserInfo = new UserInfo();
        mOrdersList = new ArrayList<>();

        CustomerOrderInfo info1 = new CustomerOrderInfo();
        info1.setName("汪涵平");
        info1.setPhone("18661866172");
        info1.setAddress("江苏省润和创智中心");
        info1.setPoint("380");
        mOrdersList.add(info1);
        CustomerOrderInfo info2 = new CustomerOrderInfo();
        info1.setName("周小美");
        info1.setPhone("18661866172");
        info1.setAddress("江苏省润和创智中心");
        info1.setPoint("380");
        mOrdersList.add(info1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initData();
    }

    private void initUI(View view) {
        view.findViewById(R.id.release_layout).setOnClickListener(this);
        view.findViewById(R.id.orders_layout).setOnClickListener(this);
        view.findViewById(R.id.done_layout).setOnClickListener(this);

        mReleaseCount = (TextView) view.findViewById(R.id.release_count);
        mOrdersCount = (TextView) view.findViewById(R.id.orders_count);
        mDoneCount = (TextView) view.findViewById(R.id.done_count);
        mTitleView = (TextView) view.findViewById(R.id.title_info);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserIcon = (CircleImageView) view.findViewById(R.id.user_icon);
        mUserIcon.setOnClickListener(this);

        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.recycler_view);
    }

    private void initData() {
        String name = mUserInfo.getName();
        if (!TextUtils.isEmpty(name)) {
            mUserName.setText(name);
        }
        String iconURL = mUserInfo.getIconURL();
        if (!TextUtils.isEmpty(iconURL)) {
            Glide.with(this).load(iconURL).placeholder(R.mipmap.avatar).into(mUserIcon);
        }

        mUserAdapter = new UserAdapter(getContext());
        mUserAdapter.setData(mOrdersList);
        mLAdapter = new LRecyclerViewAdapter(mUserAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLRecyclerView.setAdapter(mLAdapter);
        mLRecyclerView.setPullRefreshEnabled(true);
        mLRecyclerView.setOnRefreshListener(this);
        mLRecyclerView.setOnLoadMoreListener(this);
        mLRecyclerView.setOnNetWorkErrorListener(this);
        mLRecyclerView.refreshComplete(mOrdersList.size());
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.recyclerview_divider));
        mLRecyclerView.addItemDecoration(decoration);
        mLAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.release_layout:
                mTitleView.setText(R.string.default_release_title);
                mCurrentType = TYPE_RELEASE_MODEL;
                break;
            case R.id.orders_layout:
                mTitleView.setText(R.string.default_orders_title);
                mCurrentType = TYPE_ORDER_MODEL;
                break;
            case R.id.done_layout:
                mTitleView.setText(R.string.default_done_title);
                mCurrentType = TYPE_DONE_MODEL;
                break;
        }
    }

    @Override
    public void onRefresh() {
        // TODO: Refresh the data.
        mLRecyclerView.refreshComplete(mOrdersList.size());
    }

    @Override
    public void onLoadMore() {
        // TODO: Load more data.
        mLRecyclerView.refreshComplete(mOrdersList.size());
    }

    @Override
    public void reload() {
        // TODO: Load more data.
        mLRecyclerView.refreshComplete(mOrdersList.size());
    }
}
