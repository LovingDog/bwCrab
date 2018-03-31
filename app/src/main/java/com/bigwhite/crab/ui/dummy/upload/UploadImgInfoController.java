package com.bigwhite.crab.ui.dummy.upload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigwhite.crab.R;
import com.bigwhite.crab.adapter.UploadPhotoAdapter;
import com.bigwhite.crab.ui.ReleaseFragment;
import com.bigwhite.crab.utils.SpacesItemDecoration;
import com.bigwhite.crab.utils.Utils;

import java.io.File;

/**
 * Created by admin on 2018/3/31.
 */

public class UploadImgInfoController {

    private Context mContext;
    private View mView;
    private RecyclerView mReleaseImageRecyclerView;
    private UploadPhotoAdapter mUploadPhotoAdapter;

    public UploadImgInfoController(Activity context,View view){
        this.mContext = context;
        this.mView = view;
    }

    public void initView(){
        mReleaseImageRecyclerView = (RecyclerView) mView.findViewById(R.id.release_recyclerview);
    }

    public void initImgData(){
        mReleaseImageRecyclerView.setLayoutManager(new GridLayoutManager(mContext, ReleaseFragment.RELASE_IMAGE_COLUMNCOUNT));
        mReleaseImageRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        mUploadPhotoAdapter = new UploadPhotoAdapter(mContext);
        mReleaseImageRecyclerView.setAdapter(mUploadPhotoAdapter);
    }

    public void addImg(String path) {
        if (mUploadPhotoAdapter != null) {
            mUploadPhotoAdapter.addImage(path);
        }
    }

    public void setListener(final StartCameraCaptureListener startCameraCaptureListener) {
        mUploadPhotoAdapter.setAddClickListener(new UploadPhotoAdapter.addPhotoOnclickListener() {
            @Override
            public void addphotoOnclick() {
                startCameraCaptureListener.startCaptureListener();

            }
        });
    }

    public interface StartCameraCaptureListener{
        void startCaptureListener();
    }
}
