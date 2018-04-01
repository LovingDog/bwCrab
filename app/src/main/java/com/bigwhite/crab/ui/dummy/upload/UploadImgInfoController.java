package com.bigwhite.crab.ui.dummy.upload;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    public UploadImgInfoController(Activity context, View view) {
        this.mContext = context;
        this.mView = view;
    }

    public void initView() {
        mReleaseImageRecyclerView = (RecyclerView) mView.findViewById(R.id.release_recyclerview);
    }

    public void initImgData() {
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
                new AlertDialog.Builder(mContext)
                        .setTitle(mContext.getResources().getString(R.string.release_choose_title))
                        .setMessage(mContext.getResources().getString(R.string.release_choose_msg))
                        .setPositiveButton(mContext.getResources().getString(R.string.release_choose_camera),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startCameraCaptureListener.startCaptureListener(true);
                                    }
                                })
                        .setNegativeButton(mContext.getResources().getString(R.string.release_choose_photos),
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startCameraCaptureListener.startCaptureListener(false);
                            }
                        }).show();
            }
        });
    }

    public interface StartCameraCaptureListener {
        void startCaptureListener(boolean camera);
    }
}
