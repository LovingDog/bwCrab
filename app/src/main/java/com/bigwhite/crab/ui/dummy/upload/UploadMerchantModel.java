package com.bigwhite.crab.ui.dummy.upload;

import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.base.OnHttpCallBack;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.http.RetrofitUtils;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/3/31.
 */

public class UploadMerchantModel implements UploadContract.UploadModel {

    private Map<String, RequestBody> params = new HashMap<String, RequestBody>();
    @Override
    public void Upload(UploadInfo info, final OnHttpCallBack callBack) {
        if (info.getmFile() == null) {
            return;
        }
        List<File> list = new ArrayList<>();
        list.add(info.getmFile());
        RetrofitUtils.newInstence(GlobalField.GOODS_BASEURL,true)
                .create(APIService.class)
                .userUploadImg(info.getmInfo(), info.getmPrice(), info.getIntegral(),info.getToken(),info.getmCount()
                        ,info.getMerchantId(), getFileParams(list))
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<UserHttpResult>() {
                    @Override
                    public void onCompleted() {
                        //完成的时候调用
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("");
                    }

                    @Override
                    public void onNext(UserHttpResult userHttpResult) {
                        if (userHttpResult.getCode() == 1001) {
                            callBack.onSuccessful(userHttpResult);
                        } else {
                            callBack.onFailed("");
                        }
                    }

                });
    }

//    private MultipartBody.Part [] filesToMultipartBodyParts(List<File> files) {
//        MultipartBody.Part [] part =new MultipartBody.Part[1];
//
//
//        RequestBody requestBody;
//            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), files.get(0));
//            part[0] = MultipartBody.Part.createFormData("files", files.get(0).getName(), requestBody);
//
//        return part;
//    }

    private Map<String, RequestBody> getFileParams(List<File> files) {
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            if (file.exists()) {
                RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), file);
                params.put("files\"; filename=\"" + file.getName() + "", body);
            }
        }
        return params;
    }
}
