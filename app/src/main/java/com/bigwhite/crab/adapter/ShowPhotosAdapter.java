package com.bigwhite.crab.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.bean.info.MobileInfo;
import com.bigwhite.crab.ui.dummy.order.Goods;
import com.bigwhite.crab.ui.fragment.ListItemFragment;
import com.bigwhite.crab.utils.Utils;
import com.bigwhite.crab.view.ZoomImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ShowPhotosAdapter extends PagerAdapter {

    private ArrayList<Goods> mList;
    private Context mContext;
    private int mScreenWidth;
    private int mScreenheight;

    public ShowPhotosAdapter(ArrayList<Goods> list, Activity context) {
        this.mContext = context;
        this.mList = list;
        mScreenWidth = Utils.getMobileInfo(context).getmScreenWidth();
        mScreenheight = Utils.getMobileInfo(context).getmScreenHeight();
    }

    public void setData(ArrayList<Goods> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_takephotos, null);
        ZoomImageView zoomImageView = (ZoomImageView) view.findViewById(R.id.iv_takephotos);
        zoomImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Goods goods = mList.get(position);
        String path = goods.getPics();
        MobileInfo mobileInfo = Utils.getFileInfo(path);
        int width = mobileInfo.getmScreenWidth();
        int height = mobileInfo.getmScreenHeight();
        int targetWidth;
        int targetHeight;
//        if (width == 0 && height == 0) {
//            targetWidth = 360;
//            targetHeight = 360;
//        } else {
//            if (width < height) {
//                if (height < mScreenheight * 0.6f) {
//                    targetWidth = width;
//                    targetHeight = height;
//                } else {
//                    targetHeight = (int) (mScreenheight * 0.6f);
//                    float scale = targetHeight / height;
//                    targetWidth = (int) (width * scale);
//                }
//            } else {
//                if (width < mScreenWidth * 0.6f) {
//                    targetWidth = width;
//                    targetHeight = height;
//                } else {
//                    targetWidth = (int) (mScreenWidth * 0.6f);
//                    float scale = targetWidth / width;
//                    targetHeight = (int) (height * scale);
//                }
//            }
//        }
        Glide.with(mContext).load(goods.getPics())
//                .override(targetWidth == 0 ? 360 : targetWidth, targetHeight == 0 ? 360 : targetHeight)
                .placeholder(R.mipmap.thumbnail_bg)
                .into(zoomImageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }
}
