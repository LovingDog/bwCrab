package com.bigwhite.crab.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.ui.dummy.order.Goods;
import com.bigwhite.crab.ui.dummy.order.GoodsInfo;
import com.bigwhite.crab.bean.OrderList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class UserAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private boolean mLoadMore;

    public void setmLoadMore(boolean mLoadMore) {
        this.mLoadMore = mLoadMore;
    }

    public UserAdapter(Context context) {
        mContext = context;
    }

    private List<GoodsInfo> mGoodsInfos;

    public void setData(OrderList orderList) {
        if (mLoadMore) {
            if (mGoodsInfos == null) {
                mGoodsInfos = new ArrayList<>();
            }
            for (GoodsInfo goodsInfo :
                    orderList.getContent()) {
                mGoodsInfos.add(goodsInfo);
            }
        } else {
            mGoodsInfos = orderList.getContent();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.customer_order_info, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        GoodsInfo info = mGoodsInfos.get(i);
        ((UserViewHolder) viewHolder).initData(info);
    }

    @Override
    public int getItemCount() {
        if (mGoodsInfos != null) {
            return mGoodsInfos.size();
        } else {
            return 0;
        }
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;
        TextView phone;
        TextView point;

        UserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.customer_name);
            address = (TextView) itemView.findViewById(R.id.customer_address);
            phone = (TextView) itemView.findViewById(R.id.customer_phone);
            point = (TextView) itemView.findViewById(R.id.customer_point);
        }

        public void initData(GoodsInfo info) {
            name.setText(info.getUsername());
            address.setText(info.getAddress());
            phone.setText(info.getPhone());
            Goods goods = info.getGoods();
            int pointCount = 0;
            if (goods != null) {
                pointCount = goods.getIntegral();
            }
            point.setText(pointCount + "");
        }
    }
}
