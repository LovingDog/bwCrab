package com.bigwhite.crab.ui.dummy.upload;

import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.base.OnHttpCallBack;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.http.RetrofitUtils;
import com.socks.library.KLog;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/3/31.
 */

public class UploadMerchantModel implements UploadContract.UploadModel {
    @Override
    public void Upload(UploadInfo info, final OnHttpCallBack callBack) {
        if (info.getmFile() == null) {
            return;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), info.getmFile());
        RetrofitUtils.newInstence(GlobalField.BASEURL)
                .create(APIService.class)
                .userUploadImg(info.getmInfo(), info.getmPrice(), info.getIntegral(), info.getExchangeCode(), requestBody)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<UserHttpResult>() {
                    @Override
                    public void onCompleted() {
                        //完成的时候调用
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserHttpResult userHttpResult) {

                    }

                });
    }
}
