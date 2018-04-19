package com.bigwhite.crab.http;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.base.HttpCallBack;
import com.bigwhite.crab.bean.JsonResultBean;
import com.bigwhite.crab.bean.MerchantList;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.ui.dummy.login.LoginRequest;
import com.bigwhite.crab.ui.dummy.login.UserInfo;
import com.bigwhite.crab.bean.OrderList;
import com.bigwhite.crab.ui.dummy.order.OrderRequest;
import com.bigwhite.crab.utils.GsonUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class DataLogic {
    private static DataLogic instance;

    public synchronized static DataLogic getInstance() {
        if (instance == null) {
            instance = new DataLogic();
        }
        return instance;
    }

    private static Object parseObject(String text, Class clazz) {
        JsonResultBean resultBean;
        try {
            return JSONObject.parseObject(text, clazz);
        } catch (Exception e) {
            resultBean = new JsonResultBean();
            resultBean.setResult("error");
            resultBean.setDesc("");
            resultBean.setObject("");
        }
        return resultBean;
    }

    /**
     * Not work.
     *
     * @param type
     * @param request
     * @param callBack
     */
    public void userLoginRetrofit(final int type, final LoginRequest request, final HttpCallBack callBack) {
        Log.d("heqiang", "userLoginRetrofit -- type = " + type + ", phone = " + request.getPhone() + ", password = "
                + request.getPassword());
        RetrofitUtils.newInstence(GlobalField.LOGIN_URL, true)
                .create(APIService.class)
                .userLogin(request.getPhone(), request.getPassword())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult>() {
                    private UserInfo userInfo = null;

                    @Override
                    public void onCompleted() {
                        Log.d("heqiang", "userLoginRetrofit -- onCompleted");
                        callBack.onCompleted(type, userInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("heqiang", "userLoginRetrofit -- onError ", e);
                    }

                    @Override
                    public void onNext(UserHttpResult userHttpResult) {
                        Log.d("heqiang", "userLoginRetrofit -- onNext");
                        String jsonData = userHttpResult.getObject().toString();
                        userInfo = GsonUtil.parseJsonWithGson(jsonData, UserInfo.class);
                    }
                });
    }

    /**
     * Get the goods.
     *
     * @param type     请求类型
     * @param request  请求参数
     * @param callBack 请求回调
     * @param context  请求上下文
     */
    public void getGoodsListRetrofit(final int type, final OrderRequest request, final HttpCallBack<OrderList>
            callBack, final Context context) {
        RetrofitUtils.newInstence(GlobalField.GOODS_URL, false)
                .create(APIService.class)
                .getMerchants(request.getPageNow(), request.getPageSize(), request.getMerchantId(), request.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult<MerchantList>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserHttpResult<MerchantList> merchantListUserHttpResult) {

                    }
                });
    }

    /**
     * Get the order list.
     *
     * @param type     请求类型
     * @param request  请求参数
     * @param callBack 请求回调
     * @param context  请求上下文
     */
    public void getOrderListRetrofitByStatus(final int type, final OrderRequest request, final HttpCallBack<OrderList>
            callBack, final Context context) {
        RetrofitUtils.newInstence(GlobalField.ORDER_URL, false)
                .create(APIService.class)
                .getOrderInfo(request.getPageNow(), request.getPageSize(), request.getStatus(),
                        request.getMerchantId(), request.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult<OrderList>>() {
                    private OrderList orderList;

                    @Override
                    public void onCompleted() {
                        callBack.onCompleted(type, orderList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(type, "");
                    }

                    @Override
                    public void onNext(UserHttpResult<OrderList> userHttpResult) {
                        orderList = (OrderList) userHttpResult.getObject();
                    }
                });
    }
}
