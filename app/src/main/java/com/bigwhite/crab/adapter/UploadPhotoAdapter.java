package com.bigwhite.crab.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigwhite.crab.R;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/3/22.
 */

public class UploadPhotoAdapter extends RecyclerView.Adapter {

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView uploadPhotoImage;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            uploadPhotoImage = (ImageView) itemView.findViewById(R.id.upload_item_imageview);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upload_photo_item, viewGroup, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }
}
