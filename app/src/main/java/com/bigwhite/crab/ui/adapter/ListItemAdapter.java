package com.bigwhite.crab.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.ui.dummy.order.Goods;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jameson on 18-3-22.
 */

public class ListItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Goods> mItemsList = new ArrayList<>();
    private static final String DEMO_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521629002821&di=e04f7922f0bddff18469e0335e087f9d&imgtype=0&" +
            "src=http%3A%2F%2Fimg14.360buyimg.com%2Fn2%2Fjfs%2Ft148%2F307%2F1675000766%2F403308%2F42bc856f%2F53b4ed8cN340de8d2.jpg";

    public ListItemAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Goods> itemsList) {
        mItemsList.clear();
        mItemsList.addAll(itemsList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_crab_detail, null);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Goods info = mItemsList.get(i);
//        ((ItemHolder) viewHolder).setData(info);
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView integral;
        TextView price;
        ImageView preview;

        ItemHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.product_description);
            integral = (TextView) itemView.findViewById(R.id.product_integral);
            price = (TextView) itemView.findViewById(R.id.product_price);
            preview = (ImageView) itemView.findViewById(R.id.product_preview);
        }

//        public void setData(MerchandiseInfo info) {
//            description.setText(info.getmDescription());
//            integral.setText(info.getmIntegral() + "");
//            price.setText(mContext.getString(R.string.CNY) + info.getmPrice());
//            price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//            Glide.with(mContext).load(DEMO_URL).into(preview);
//        }
    }
}
