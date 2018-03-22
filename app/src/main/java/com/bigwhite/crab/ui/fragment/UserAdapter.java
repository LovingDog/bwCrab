package com.bigwhite.crab.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.model.CustomerOrderInfo;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class UserAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public UserAdapter(Context context) {
        mContext = context;
    }

    private List<CustomerOrderInfo> mOrdersList = new ArrayList<>();

    public void setData(List<CustomerOrderInfo> ordersList) {
        mOrdersList.clear();
        mOrdersList.addAll(ordersList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.customer_order_info, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        CustomerOrderInfo info = mOrdersList.get(i);
        ((UserViewHolder) viewHolder).initData(info);
    }

    @Override
    public int getItemCount() {
        return mOrdersList.size();
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

        public void initData(CustomerOrderInfo info) {
            name.setText(info.getName());
            address.setText(info.getAddress());
            phone.setText(info.getPhone());
            point.setText(info.getPoint());
        }
    }
}
