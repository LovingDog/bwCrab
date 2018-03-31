package com.bigwhite.crab.ui.dummy.upload;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bigwhite.crab.R;

import java.io.File;

/**
 * Created by admin on 2018/3/31.
 */

public class UploadMerchantController implements UploadContract.UploadMerchantView,View.OnClickListener{
    private Activity mContext;
    private EditText mInfo;
    private EditText mPrice;
    private EditText mIntegral;
    private EditText mExchangeCode;
    private Button mUploadBt;
    private UploadMerchantPresenter mUploadMerchantPresenter;

    public UploadMerchantController(Activity context){
        this.mContext = context;
        mUploadMerchantPresenter = new UploadMerchantPresenter(this);
    }

    public void initView(){
        mInfo = (EditText) mContext.findViewById(R.id.et_product_description);
        mPrice = (EditText) mContext.findViewById(R.id.et_product_price);
        mIntegral = (EditText) mContext.findViewById(R.id.et_convertibility);
        mExchangeCode = (EditText) mContext.findViewById(R.id.et_convertibility_code);
        mUploadBt = (Button) mContext.findViewById(R.id.bt_upload);
    }

    public void initEvent(){
        mUploadBt.setOnClickListener(this);
    }

    @Override
    public void uploadSuccess() {

    }

    @Override
    public void upLoadFail() {

    }

    @Override
    public void reFreshActivity() {

    }

    @Override
    public UploadInfo getUploadData() {
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
                if (!TextUtils.isEmpty(mInfo.getText().toString().trim())
                        &&!TextUtils.isEmpty(mPrice.getText().toString().trim())
                        &&!TextUtils.isEmpty(mIntegral.getText().toString().trim())
                        &&!TextUtils.isEmpty(mExchangeCode.getText().toString().trim())) {
                    mUploadMerchantPresenter.upload();
                }
                break;
        }
    }
}
