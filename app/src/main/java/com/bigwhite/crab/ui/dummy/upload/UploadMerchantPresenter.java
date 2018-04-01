package com.bigwhite.crab.ui.dummy.upload;

import com.bigwhite.crab.base.OnHttpCallBack;

/**
 * Created by admin on 2018/3/31.
 */

public class UploadMerchantPresenter implements UploadContract.UploadPresenter {

    private UploadContract.UploadMerchantView mUploadMerchantView;
    private UploadMerchantModel mMerchantModel;

    public UploadMerchantPresenter(UploadContract.UploadMerchantView uploadMerchantView) {
        this.mUploadMerchantView = uploadMerchantView;
        mMerchantModel = new UploadMerchantModel();
    }

    @Override
    public void upload() {
        mMerchantModel.Upload(this.mUploadMerchantView.getUploadData(), new OnHttpCallBack() {
            @Override
            public void onSuccessful(Object o) {
                mUploadMerchantView.uploadSuccess();
            }

            @Override
            public void onFaild(String errorMsg) {
                mUploadMerchantView.upLoadFail();
            }
        });

    }
}
