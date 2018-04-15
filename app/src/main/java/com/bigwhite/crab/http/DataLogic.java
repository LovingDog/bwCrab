package com.bigwhite.crab.http;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.base.HttpCallBack;
import com.bigwhite.crab.bean.JsonResultBean;
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
        RetrofitUtils.newInstence(GlobalField.BASE_URL,true)
                .create(APIService.class)
                .userLogin(request.getPhone(), request.getPassword())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult>() {
                    private UserInfo userInfo = null;

                    @Override
                    public void onCompleted() {
                        callBack.onCompleted(type, userInfo);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserHttpResult userHttpResult) {

                        String jsonData = userHttpResult.getObject().toString();
                        userInfo = GsonUtil.parseJsonWithGson(jsonData, UserInfo.class);
                    }
                });
    }


    /**
     * Get tht order list.
     *
     * @param type
     * @param request
     * @param callBack
     */


    public void getOrderListRetrofit(final int type, final OrderRequest request, final HttpCallBack<OrderList>
            callBack, final Context context) {
        RetrofitUtils.newInstence(GlobalField.GOODS_ORDER_URL,false)
                .create(APIService.class)
                .getOrderInfo(request.getPageNow(), request.getPageSize(), request.getStatus(),
                        request.getMerchantId(), request.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult<OrderList>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(type,"");
                    }

                    @Override
                    public void onNext(UserHttpResult<OrderList> userHttpResult) {
                        OrderList orderList = (OrderList) userHttpResult.getObject();
                        callBack.onCompleted(type,orderList);
                    }
                });
    }
}
