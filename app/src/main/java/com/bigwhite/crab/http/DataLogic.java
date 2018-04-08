package com.bigwhite.crab.http;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.base.HttpCallBack;
import com.bigwhite.crab.bean.JsonResultBean;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.ui.dummy.login.LoginRequest;
import com.bigwhite.crab.ui.dummy.login.UserInfo;
import com.bigwhite.crab.ui.dummy.order.OrderList;
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
     * User login.
     *
     * @param type
     * @param request
     * @param callBack
     * @deprecated
     */
    public void userLogin(final int type, final LoginRequest request, final HttpCallBack callBack) {
        new QueryData(type, new HttpTask.HttpTaskListener() {
            @Override
            public Object getData(int id) {
                String str = HttpUtil.get("http://101.37.149.35:8686/login.do?phone=" + request.getPhone() +
                        "&password=" + request.getPassword());
                return parseObject(str, JsonResultBean.class);
            }

            @Override
            public void onSuccess(int id, Object object) {
                if (object instanceof JsonResultBean) {
                    String desc = ((JsonResultBean) object).getDesc();
                    if ("request success".equalsIgnoreCase(desc)) {
                        UserInfo userInfo = GsonUtil.parseJsonWithGson(((JsonResultBean) object).getObject().toString(),
                                UserInfo.class);
                        callBack.onCompleted(type, userInfo);
                    } else {
                        callBack.onError(type, desc);
                    }
                }

            }
        }).getData();
    }

    /**
     * Not work.
     *
     * @param type
     * @param request
     * @param callBack
     */
    public void userLoginRetrofit(final int type, final LoginRequest request, final HttpCallBack callBack) {
        RetrofitUtils.newInstence(GlobalField.BASE_URL)
                .create(APIService.class)
                .useLogin(request.getPhone(), request.getPassword())
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
     * @deprecated
     */
    public void getOrderList(final int type, final OrderRequest request, final HttpCallBack<OrderList> callBack) {
        new QueryData(type, new HttpTask.HttpTaskListener() {
            @Override
            public Object getData(int id) {
                String str = HttpUtil.get(GlobalField.GOODS_ORDER_URL +
                        "findByPage.do?pageNow=" + request.getPageNow() + "&pageSize=" + request
                        .getPageSize() + "&status=" + request.getStatus() + "&merchantId=" + request.getMerchantId()
                        + "&token=" + request.getToken());
                return parseObject(str, JsonResultBean.class);
            }

            @Override
            public void onSuccess(int id, Object object) {
                if (object instanceof JsonResultBean) {
                    String desc = ((JsonResultBean) object).getDesc();
                    if ("request success".equalsIgnoreCase(desc)) {
                        OrderList orderList = GsonUtil.parseJsonWithGson(((JsonResultBean) object).getObject()
                                .toString(), OrderList.class);
                        callBack.onCompleted(type, orderList);
                    } else {
                        callBack.onError(type, desc);
                    }
                }
            }
        }).getData();
    }

    /**
     * Get tht order list.
     *
     * @param type
     * @param request
     * @param callBack
     */
    public void getOrderListRetrofit(final int type, final OrderRequest request, final HttpCallBack<OrderList>
            callBack) {
        RetrofitUtils.newInstence(GlobalField.BASE_URL)
                .create(APIService.class)
                .getOrderInfo(request.getPageNow(), request.getPageSize(), request.getStatus(),
                        request.getMerchantId(), request.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult>() {
                    private OrderList orderList = null;

                    @Override
                    public void onCompleted() {
                        callBack.onCompleted(type, orderList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(type, e.getMessage());
                    }

                    @Override
                    public void onNext(UserHttpResult userHttpResult) {
                        String jsonData = userHttpResult.getObject().toString();
                        orderList = GsonUtil.parseJsonWithGson(jsonData, OrderList.class);
                    }
                });
    }
}
