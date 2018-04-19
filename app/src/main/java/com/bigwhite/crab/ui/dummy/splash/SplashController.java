package com.bigwhite.crab.ui.dummy.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.bean.LoginInfo;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.http.RetrofitUtils;
import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.utils.GsonUtil;
import com.bigwhite.crab.utils.PermissionsActivity;
import com.bigwhite.crab.utils.PermissionsChecker;
import com.bigwhite.crab.utils.ToastUtils;
import com.bigwhite.crab.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanghanping on 2018/3/20.
 */

public class SplashController {
    private Activity mContext;
    private PermissionsChecker mChecker;
    private SplashOutListener mSplashOutListener;

    public SplashController(Activity context) {
        this.mContext = context;
    }

    public void setmSplashOutListener(SplashOutListener splashOutListener){
        this.mSplashOutListener = splashOutListener;
    }

    public void initPermissionCheck(){
        mChecker = new PermissionsChecker(mContext);
        String[] graint = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE
                ,Manifest.permission.READ_EXTERNAL_STORAGE};
        if (mChecker.lacksPermissions(graint)) {
            Intent intent = new Intent(mContext, PermissionsActivity.class);
            intent.putExtra(PermissionsActivity.EXTRA_PERMISSIONS, graint);
            ActivityCompat.startActivityForResult(mContext, intent, 102, null);
        }else {
            mSplashOutListener.PermissonCheckoutListener();
        }
    }

    public void doLoginSession(final Context context){
        //do login

        RetrofitUtils.newInstence(GlobalField.LOGIN_URL,true)
                .create(APIService.class)
                .userLogin("18226981749", Utils.md5("123456"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult>() {
                    @Override
                    public void onCompleted() {
                        int a = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        int a = 0;
                    }

                    @Override
                    public void onNext(UserHttpResult userHttpResult) {
                        String obj = userHttpResult.getObject().toString();

                        ToastUtils.showToast(context.getApplicationContext(),obj);
                        LoginInfo loginInfo = GsonUtil.parseJsonWithGson(obj,LoginInfo.class);
                        AppPreference appPreference = new AppPreference(mContext);
                        appPreference.setLogin(loginInfo.getToken());
                        if (obj != null ) {
                            mSplashOutListener.LoginSuccessListener();
                        }
                    }

                });
    }
}
