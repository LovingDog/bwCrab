package com.bigwhite.crab.ui.dummy.upload;

import com.bigwhite.crab.base.OnHttpCallBack;

import java.io.File;

/**
 * Created by admin on 2018/3/31.
 */

public class UploadContract {

    interface UploadMerchantView {
        void uploadSuccess(Object o);

        void upLoadFail();

        void reFreshActivity();

        UploadInfo getUploadData();

        File getFile();
    }

    interface UploadPresenter {
        void upload();
    }

    interface UploadModel {
        void Upload(UploadInfo info, OnHttpCallBack callBack);
    }

}
