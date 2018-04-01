package com.bigwhite.crab.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bigwhite.crab.R;
import com.bigwhite.crab.bean.info.MobileInfo;
import com.bigwhite.crab.ui.ReleaseFragment;
import com.bigwhite.crab.utils.ToastUtils;
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
    private addPhotoOnclickListener mAddPhotoOnclickListener;

    public UploadPhotoAdapter(Context context) {
        mImagePathList.add("");
        mContext = context;
    }

    public void setAddClickListener(addPhotoOnclickListener addPhotoOnclickListener) {
        mAddPhotoOnclickListener = addPhotoOnclickListener;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView uploadPhotoImage;
        private Button deleteBt;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            uploadPhotoImage = (ImageView) itemView.findViewById(R.id.upload_item_imageview);
            deleteBt = (Button) itemView.findViewById(R.id.bt_delete);
            int width = Utils.getMobileInfo((Activity) mContext).getmScreenWidth();
            int lastWidth = (width / ReleaseFragment.RELASE_IMAGE_COLUMNCOUNT) - 5;
            uploadPhotoImage.setLayoutParams(new RelativeLayout.LayoutParams(lastWidth, lastWidth - 10));
        }

        public void setImage(String imagePath) {
            Glide.with(mContext).load(imagePath).into(uploadPhotoImage);
        }

        public void setAddIcon() {
            uploadPhotoImage.setBackgroundResource(R.mipmap.add_photos);
        }

        public void setDeleteVisisbility(boolean visisbility) {
            deleteBt.setVisibility(visisbility ? View.VISIBLE : View.GONE);
        }
    }

    public void addImage(String imagePath) {
        mImagePathList.add(0, imagePath);
        notifyDataSetChanged();
    }

    public List getImagesList() {
        return mImagePathList;
    }

    public void removeImage(String imagePath) {
        mImagePathList.remove(imagePath);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
//        return 6;
        return mImagePathList.size();
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
        String imagePath = mImagePathList.get(i);
        if (i == mImagePathList.size() - 1) {
//            viewHolder.uploadPhotoImage.setBackgroundResource(R.mipmap.add_photos);
//            viewHolder.deleteBt.setVisibility(View.GONE);
            ((PhotoViewHolder) viewHolder).setAddIcon();
            ((PhotoViewHolder) viewHolder).setDeleteVisisbility(false);
            ((PhotoViewHolder) viewHolder).uploadPhotoImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mImagePathList.size() <= 6) {
                        mAddPhotoOnclickListener.addphotoOnclick();
                    } else {
                        ToastUtils.showToast(mContext.getApplicationContext(), mContext
                                .getResources().getString(R.string.release_no_photos));
                    }
                }
            });
        } else {
            ((PhotoViewHolder) viewHolder).setImage(imagePath);
            ((PhotoViewHolder) viewHolder).setDeleteVisisbility(true);
            ((PhotoViewHolder) viewHolder).uploadPhotoImage.setOnClickListener(null);
        }

    }

    public interface addPhotoOnclickListener {
        void addphotoOnclick();
    }
}
