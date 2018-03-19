package com.ws.crab.bwui.login;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import com.socks.library.KLog;
import com.ws.crab.R;
import com.ws.crab.base.APIService;
import com.ws.crab.base.BaseActivity;
import com.ws.crab.base.GlobalField;
import com.ws.crab.bean.UserHttpResult;
import com.ws.crab.http.RetrofitUtils;
import com.ws.crab.utils.StringUtils;
import com.ws.crab.utils.ToastUtils;
import com.ws.crab.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 注册
 */

public class RegisterActivity extends BaseActivity {
    @InjectView(R.id.btn_send)
    Button btn_send;
    @InjectView(R.id.et_login_tel)
    EditText et_reg_tel;
    @InjectView(R.id.et_reg_yzm)
    EditText et_reg_yzm;

    private String tel, yzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_send,R.id.btn_reg})
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                tel = et_reg_tel.getText().toString().trim();
                if (StringUtils.isEmpty(tel)) {
                    ToastUtils.showToast(this, "请输入手机号");
                    return;
                }
                if (!Utils.isChinaPhoneLegal(tel)) {
                    ToastUtils.showToast(this, "请输入正确的手机号");
                    return;
                }
                sendSMS(tel);
                break;
            case R.id.btn_reg:
                tel = et_reg_tel.getText().toString().trim();
                yzm = et_reg_yzm.getText().toString().trim();
                if (StringUtils.isEmpty(tel)) {
                    ToastUtils.showToast(this, "请输入手机号");
                    return;
                }
                if (StringUtils.isEmpty(yzm)) {
                    ToastUtils.showToast(this, "请输入验证码");
                    return;
                }
                //注册接口

                break;
        }

    }

    public void sendSMS(String tel) {
        //登录的网络请求
        RetrofitUtils.newInstence(GlobalField.BASEURL)//实例化Retrofit对象
                .create(APIService.class)//创建Rxjava---->LoginService对象
                .senSMS(tel, 1)//调用接口
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<UserHttpResult<Object>>() {//网络(登录)请求回调
                    @Override
                    public void onCompleted() {
                        //完成的时候调用

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.getMessage() + "--");
                        e.printStackTrace();
                        Log.d("SMS", "失败");
                    }

                    @Override
                    public void onNext(UserHttpResult<Object> userResult) {
                        if(userResult.getResult()==0){
                            ToastUtils.showToast(RegisterActivity.this,"发送成功");
                        }else{
                            ToastUtils.showToast(RegisterActivity.this,"发送失败");
                        }
                    }
                });
    }
}
