package com.bigwhite.crab.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.base.BaseFragment;
import com.bigwhite.crab.base.BaseRequest;
import com.bigwhite.crab.base.HttpCallBack;
import com.bigwhite.crab.http.DataLogic;
import com.bigwhite.crab.ui.dummy.login.UserInfo;
import com.bigwhite.crab.ui.adapter.UserAdapter;
import com.bigwhite.crab.bean.OrderList;
import com.bigwhite.crab.ui.dummy.order.OrderRequest;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Fragment to show the user.
 */
public class UserFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener,
        OnNetWorkErrorListener, HttpCallBack {

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
    private OrderList mCurrentOrderList = new OrderList();

    private int mCurrentPageSize = 0;
    private int mCurrentType = TYPE_ORDER_MODEL;
    private static final int TYPE_RELEASE_MODEL = 0;
    private static final int TYPE_ORDER_MODEL = 1;
    private static final int TYPE_DONE_MODEL = 2;

    private static final int ID_GET_USER = 0;
    private static final int ID_GET_ORDERS = 1;
    private boolean mLoadMore = false;

    public UserFragment() {
        // Required empty public constructor
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
        mLRecyclerView.setOnRefreshListener(this);
        mLRecyclerView.setOnLoadMoreListener(this);
        mLRecyclerView.setOnNetWorkErrorListener(this);
    }

    private void initData() {
        mUserAdapter = new UserAdapter(getActivity());
        mUserAdapter.setData(mCurrentOrderList);
        mLAdapter = new LRecyclerViewAdapter(mUserAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerView.setAdapter(mLAdapter);
        mLRecyclerView.setPullRefreshEnabled(true);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.recyclerview_divider));
        mLRecyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getOrders();
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
                getOrders();
                break;
            case R.id.orders_layout:
                mTitleView.setText(R.string.default_orders_title);
                mCurrentType = TYPE_ORDER_MODEL;
                getOrders();
                break;
            case R.id.done_layout:
                mTitleView.setText(R.string.default_done_title);
                mCurrentType = TYPE_DONE_MODEL;
                getOrders();
                break;
        }
    }

    @Override
    public void onRefresh() {
        mLoadMore = false;
        getOrders();
    }

    @Override
    public void onLoadMore() {
        mLoadMore = true;
        getOrders();
    }

    @Override
    public void reload() {
        mLoadMore = false;
        getOrders();
    }

    /**
     * Init the user info.
     *
     * @param userInfo
     */
    private void initUser(UserInfo userInfo) {
        String name = userInfo.getName();
        if (!TextUtils.isEmpty(name)) {
            mUserName.setText(name);
        }
//        String iconURL = userInfo.getIconURL();
//        if (!TextUtils.isEmpty(iconURL)) {
//            Glide.with(this).load(iconURL).placeholder(R.mipmap.avatar).into(mUserIcon);
//        }
        mUserInfo = userInfo;
    }

    /**
     * Init the order list.
     */
    private void initOrderList(OrderList list) {
        mUserAdapter.setmLoadMore(mLoadMore);
        mUserAdapter.setData(list);
        mLAdapter.notifyDataSetChanged();
        int size = 0;
        if (mCurrentOrderList != null) {
            size = mCurrentOrderList.size();
        }
        mLRecyclerView.refreshComplete(size);
        switch (mCurrentType) {
            case TYPE_RELEASE_MODEL:
                mReleaseCount.setText(String.valueOf(list.getTotalElements()));
                break;
            case TYPE_ORDER_MODEL:
                mOrdersCount.setText(String.valueOf(list.getTotalElements()));
                break;
            case TYPE_DONE_MODEL:
                mDoneCount.setText(String.valueOf(list.getTotalElements()));
                break;
        }
    }


    @Override
    public void onCompleted(int type, Object o) {
        switch (type) {
            case ID_GET_ORDERS:
                initOrderList((OrderList) o);
                break;
            case ID_GET_USER:
                initUser((UserInfo) o);
                break;
        }
    }

    @Override
    public void onError(int type, String fail) {

    }

    /**
     * Get the orders.
     */
    private void getOrders() {
        DataLogic.getInstance().getOrderListRetrofit(ID_GET_ORDERS, (OrderRequest) getRequest(), this,getActivity());
    }

    @Override
    public BaseRequest getRequest() {
        OrderRequest request = new OrderRequest(mCurrentType);
        if (mLoadMore) {
            request.setPageNow(request.getPageNow()+1);
        } else {
            request.setPageNow(0);
        }
        request.setMerchantId((int) id);
        request.setToken(token);
        return request;
    }
}
