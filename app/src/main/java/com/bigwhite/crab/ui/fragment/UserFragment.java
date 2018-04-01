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
import com.bigwhite.crab.http.DataLogic;
import com.bigwhite.crab.http.HttpTask;
import com.bigwhite.crab.http.QueryData;
import com.bigwhite.crab.model.UserInfo;
import com.bigwhite.crab.ui.adapter.UserAdapter;
import com.bigwhite.crab.ui.dummy.order.OrderCallback;
import com.bigwhite.crab.ui.dummy.order.OrderList;
import com.bigwhite.crab.ui.dummy.order.OrderRequest;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Fragment to show the user.
 */
public class UserFragment extends Fragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener,
        OnNetWorkErrorListener, OrderCallback, HttpTask.HttpTaskListener {

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
    private Map<Integer, OrderList> mOrders = new HashMap<>();
    private OrderList mCurrentOrderList = new OrderList();

    private int mCurrentPageSize = 0;
    private int mCurrentType = TYPE_ORDER_MODEL;
    private static final int TYPE_RELEASE_MODEL = 0;
    private static final int TYPE_ORDER_MODEL = 1;
    private static final int TYPE_DONE_MODEL = 2;

    private static final int ID_GET_USER = 0;
    private static final int ID_GET_ALL_ORDERS = 1;

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
        mUserAdapter = new UserAdapter(getContext());
        mUserAdapter.setData(mCurrentOrderList);
        mLAdapter = new LRecyclerViewAdapter(mUserAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLRecyclerView.setAdapter(mLAdapter);
        mLRecyclerView.setPullRefreshEnabled(true);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.recyclerview_divider));
        mLRecyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new QueryData(ID_GET_USER, this).getData();
        new QueryData(ID_GET_ALL_ORDERS, this).getData();
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
                DataLogic.getInstance().getOrderList(new OrderRequest(mCurrentType), this);
                break;
            case R.id.orders_layout:
                mTitleView.setText(R.string.default_orders_title);
                mCurrentType = TYPE_ORDER_MODEL;
                DataLogic.getInstance().getOrderList(new OrderRequest(mCurrentType), this);
                break;
            case R.id.done_layout:
                mTitleView.setText(R.string.default_done_title);
                mCurrentType = TYPE_DONE_MODEL;
                DataLogic.getInstance().getOrderList(new OrderRequest(mCurrentType), this);
                break;
        }
    }

    @Override
    public void onRefresh() {
        DataLogic.getInstance().getOrderList(new OrderRequest(mCurrentType), this);
    }

    @Override
    public void onLoadMore() {
        DataLogic.getInstance().getOrderList(new OrderRequest(mCurrentType), this);
    }

    @Override
    public void reload() {
        DataLogic.getInstance().getOrderList(new OrderRequest(mCurrentType), this);
    }

    @Override
    @Deprecated
    public Object getData(int id) {
        switch (id) {
            case ID_GET_USER:
                return DataLogic.getInstance().getUserInfo();
            case ID_GET_ALL_ORDERS:
//                return DataLogic.getInstance().getOrderList();
        }
        return null;
    }

    @Override
    @Deprecated
    public void onSuccess(int id, Object object) {
        if (object != null) {
            switch (id) {
                case ID_GET_USER:
                    UserInfo userInfo = (UserInfo) object;
                    initUser(userInfo);
                    break;
                case ID_GET_ALL_ORDERS:
                    break;
            }
        }
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
        String iconURL = userInfo.getIconURL();
        if (!TextUtils.isEmpty(iconURL)) {
            Glide.with(this).load(iconURL).placeholder(R.mipmap.avatar).into(mUserIcon);
        }
        mUserInfo = userInfo;
    }

    /**
     * Init the order list.
     */
    private void initOrderList() {
        OrderList releaseList = mOrders.get(TYPE_RELEASE_MODEL);
        OrderList orderList = mOrders.get(TYPE_ORDER_MODEL);
        OrderList doneList = mOrders.get(TYPE_DONE_MODEL);

        int releaseCount = 0;
        if (releaseList != null) {
            releaseCount = releaseList.getTotalElements();
        }
        int orderCount = 0;
        if (orderList != null) {
            orderCount = releaseList.getTotalElements();
        }
        int doneCount = 0;
        if (doneList != null) {
            doneCount = doneList.getTotalElements();
        }
        mReleaseCount.setText("" + releaseCount);
        mOrdersCount.setText("" + orderCount);
        mDoneCount.setText("" + doneCount);
        switch (mCurrentType) {
            case TYPE_RELEASE_MODEL:
                mCurrentOrderList = releaseList;
                break;
            case TYPE_ORDER_MODEL:
                mCurrentOrderList = orderList;
                break;
            case TYPE_DONE_MODEL:
                mCurrentOrderList = doneList;
                break;
        }
        mLAdapter.notifyDataSetChanged();
        int size = 0;
        if (mCurrentOrderList != null) {
            size = mCurrentOrderList.size();
        }
        mLRecyclerView.refreshComplete(size);
    }

    @Override
    public void onCompleted(int status, OrderList list) {
        mOrders.remove(status);
        mOrders.put(status, list);
        initOrderList();
    }
}
