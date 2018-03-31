package com.bigwhite.crab.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bigwhite.crab.R;
import com.bigwhite.crab.bean.info.MobileInfo;
import com.bigwhite.crab.ui.ReleaseFragment;
import com.bigwhite.crab.utils.Utils;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class UploadPhotoAdapter extends RecyclerView.Adapter {

    private List<String> mImagePathList = new ArrayList<>();
    private Context mContext;
    public UploadPhotoAdapter(Context context) {
        mContext = context;
    }
    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView uploadPhotoImage;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            uploadPhotoImage = (ImageView) itemView.findViewById(R.id.upload_item_imageview);
            int width = Utils.getMobileInfo((Activity) mContext).getmScreenWidth();
            int lastWidth = (width / ReleaseFragment.RELASE_IMAGE_COLUMNCOUNT ) - 5;
            uploadPhotoImage.setLayoutParams(new RelativeLayout.LayoutParams(lastWidth, lastWidth));
        }

        public void setImage(String imagePath) {
//            Glide.with(mContext).load(imagePath).into(uploadPhotoImage);
        }
    }

    private void addImage(String imagePath) {
        mImagePathList.add(imagePath);
        notifyDataSetChanged();
    }

    private void removeImage(String imagePath) {
        mImagePathList.remove(imagePath);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 6;
//        return mImagePathList.size() + 1;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upload_photo_item, viewGroup, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.i("ren_kang", "onBindViewHolder");
//        String imagePath = mImagePathList.get(i);
//        ((PhotoViewHolder) viewHolder).setImage(imagePath);
    }
}
