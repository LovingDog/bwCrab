package com.bigwhite.crab.ui.dummy.upload;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bigwhite.crab.R;
import com.bigwhite.crab.adapter.UploadPhotoAdapter;
import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.ui.ReleaseFragment;
import com.bigwhite.crab.utils.SpacesItemDecoration;
import com.bigwhite.crab.utils.ToastUtils;

import java.io.File;
import java.util.List;

/**
 * Created by admin on 2018/3/31.
 */

public class UploadMerchantController implements UploadContract.UploadMerchantView, View.OnClickListener {
    private Activity mContext;
    private EditText mInfo;
    private EditText mPrice;
    private EditText mIntegral;
    private EditText mExchangeCode;
    private Button mUploadBt;
    private UploadMerchantPresenter mUploadMerchantPresenter;
    private View mView;
    private RecyclerView mReleaseImageRecyclerView;
    private UploadPhotoAdapter mUploadPhotoAdapter;

    public UploadMerchantController(Activity context, View view) {
        this.mContext = context;
        this.mView = view;
        mUploadMerchantPresenter = new UploadMerchantPresenter(this);
    }

    public void initView() {
        mInfo = (EditText) mView.findViewById(R.id.et_product_description);
        mPrice = (EditText) mView.findViewById(R.id.et_product_price);
        mIntegral = (EditText) mView.findViewById(R.id.et_convertibility);
        mExchangeCode = (EditText) mView.findViewById(R.id.et_convertibility_code);
        mUploadBt = (Button) mView.findViewById(R.id.bt_upload);
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

    public List getImageList() {
        if (mUploadPhotoAdapter != null) {
            return mUploadPhotoAdapter.getImagesList();
        }
        return null;
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

    public void initEvent() {
        mUploadBt.setOnClickListener(this);
    }

    @Override
    public void uploadSuccess(Object o) {
        ToastUtils.showToast(mContext.getApplicationContext(), mContext.getResources().getString(R.string.release_add_completed));
    }

    @Override
    public void upLoadFail() {
        ToastUtils.showToast(mContext.getApplicationContext(), mContext.getResources().getString(R.string.release_add_completed));
    }

    @Override
    public void reFreshActivity() {

    }

    @Override
    public UploadInfo getUploadData() {
        if (isCompleteInfo()) {
            UploadInfo uploadInfo = new UploadInfo();
            uploadInfo.setmInfo(mInfo.getText().toString().trim());
            uploadInfo.setmPrice(mPrice.getText().toString().trim());
            uploadInfo.setIntegral(Integer.parseInt(mIntegral.getText().toString().trim()));
            uploadInfo.setExchangeCode(mExchangeCode.getText().toString().trim());
            uploadInfo.setToken(new AppPreference(mContext).getLoginToken());
            List<String> pathList = mUploadPhotoAdapter.getImagesList();
            uploadInfo.setmFile(new File(pathList.get(0)));
            return uploadInfo;
        }
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_upload:
                if (isCompleteInfo()) {
                    mUploadMerchantPresenter.upload();
                } else {
                    ToastUtils.showToast(mContext.getApplicationContext(), mContext.getResources()
                            .getString(R.string.release_info_complete));
                }
                break;
        }
    }

    private boolean isCompleteInfo() {
        return !TextUtils.isEmpty(mInfo.getText().toString().trim())
                && !TextUtils.isEmpty(mPrice.getText().toString().trim())
                && !TextUtils.isEmpty(mIntegral.getText().toString().trim())
                && !TextUtils.isEmpty(mExchangeCode.getText().toString().trim());
    }
}
