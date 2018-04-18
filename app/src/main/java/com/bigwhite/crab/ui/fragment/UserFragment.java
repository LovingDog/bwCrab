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
import com.bigwhite.crab.bean.MerchantList;
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
    /**
     * 用户信息
     *
     * @deprecated
     */
    private UserInfo mUserInfo;
    /**
     * 未接订单
     */
    private OrderList mOrderList = new OrderList();
    /**
     * 已接订单
     */
    private OrderList mDoneList = new OrderList();
    /**
     * 发布商品列表
     *
     * @deprecated
     */
    private MerchantList mMerchantList = new MerchantList();

    /**
     * 当前页面
     */
    private int mCurrentPageSize = 0;
    /**
     * 当前类型
     */
    private int mCurrentType = ID_GET_ORDER;

    /**
     * 获取用户ID
     *
     * @deprecated
     */
    private static final int ID_GET_USER = 3;
    /**
     * 获取未接订单ID
     */
    private static final int ID_GET_ORDER = 0;
    /**
     * 获取已接订单ID
     */
    private static final int ID_GET_DONE = 1;
    /**
     * 获取商品订单ID
     *
     * @deprecated
     */
    private static final int ID_GET_RELEASE = 2;
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

    /**
     * Init the UI.
     *
     * @param view root view
     */
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

    /**
     * Init the data.
     */
    private void initData() {
        mUserAdapter = new UserAdapter(getActivity());
        mUserAdapter.setData(mOrderList);
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
        getAllData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            //15195756146  123456
//            case R.id.release_layout:
//                mTitleView.setText(R.string.default_release_title);
//                mCurrentType = ID_GET_RELEASE;
//                getOrders();
//                break;
            case R.id.orders_layout:
                mTitleView.setText(R.string.default_orders_title);
                mCurrentType = ID_GET_ORDER;
                initOrderList(mCurrentType, mOrderList);
                break;
            case R.id.done_layout:
                mTitleView.setText(R.string.default_done_title);
                mCurrentType = ID_GET_DONE;
                initOrderList(mCurrentType, mDoneList);
                break;
        }
    }

    @Override
    public void onRefresh() {
        mLoadMore = false;
        getAllData();
    }

    @Override
    public void onLoadMore() {
        mLoadMore = true;
        getOrdersByStatus(mCurrentType);
    }

    @Override
    public void reload() {
        mLoadMore = false;
        getAllData();
    }

    /**
     * Init the user info.
     *
     * @param userInfo
     * @deprecated
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
    private void initOrderList(int status, OrderList list) {
        mUserAdapter.setmLoadMore(mLoadMore);
        // 更新当前状态
        if (status == mCurrentType) {
            // 如果当前已经是最后一页，设置没有更多
            if (list.isLast()) {
                mLRecyclerView.setNoMore(true);
            }
            // 设置数据
            mUserAdapter.setData(list);
            mLAdapter.notifyDataSetChanged();
            // 刷新加载数量
            int size = list.size();
            mLRecyclerView.refreshComplete(size);
        }
        switch (status) {
//            case ID_GET_RELEASE:
//                mReleaseCount.setText(String.valueOf(list.getTotalElements()));
//                break;
            case ID_GET_ORDER:
                mOrderList = list;
                mOrdersCount.setText(String.valueOf(list.getTotalElements()));
                break;
            case ID_GET_DONE:
                mDoneList = list;
                mDoneCount.setText(String.valueOf(list.getTotalElements()));
                break;
        }
    }


    @Override
    public void onCompleted(int type, Object o) {
        switch (type) {
            case ID_GET_ORDER:
            case ID_GET_DONE:
                initOrderList(type, (OrderList) o);
                break;
//            case ID_GET_USER:
//                initUser((UserInfo) o);
//                break;
        }
    }

    @Override
    public void onError(int type, String fail) {

    }

    /**
     * Get all the orders.
     */
    private void getAllData() {
        // 获取已经发布的商品
//        getGoods();
        // 获取未接订单
        getOrdersByStatus(ID_GET_ORDER);
        // 获取已接订单
        getOrdersByStatus(ID_GET_DONE);
    }

    /**
     * 获取已经发布信息
     */
    private void getGoods() {
        OrderRequest request = (OrderRequest) getRequest();
        DataLogic.getInstance().getGoodsListRetrofit(ID_GET_ORDER, request, this, getActivity());
    }

    /**
     * 通过状态获取订单
     *
     * @param status
     */
    private void getOrdersByStatus(int status) {
        OrderRequest request = (OrderRequest) getRequest();
        request.setStatus(status);
        DataLogic.getInstance().getOrderListRetrofitByStatus(status, request, this, getActivity());
    }

    @Override
    public BaseRequest getRequest() {
        OrderRequest request = new OrderRequest();
        if (mLoadMore) {
            //获取总页数
            int totalPages = 0;
            int currentPages = 0;
            boolean isLast = false;
            switch (mCurrentType) {
                case ID_GET_ORDER:
                    isLast = mOrderList.isLast();
                    currentPages = mOrderList.getNumber();
                    totalPages = mOrderList.getTotalPages();
                    break;
                case ID_GET_DONE:
                    isLast = mDoneList.isLast();
                    currentPages = mDoneList.getNumber();
                    totalPages = mDoneList.getTotalPages();
                    break;
            }
            // 如果已经是最后一页就加载当前页，其他加载下一页
            if (isLast || totalPages == currentPages + 1) {
                request.setPageNow(currentPages);
            } else {
                request.setPageNow(currentPages + 1);
            }
        } else {
            request.setPageNow(0);
        }
        request.setMerchantId((int) id);
        request.setToken(token);
        return request;
    }
}
