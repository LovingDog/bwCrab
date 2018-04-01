package com.bigwhite.crab.http;

import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.model.UserInfo;
import com.bigwhite.crab.ui.dummy.order.OrderCallback;
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

    public UserInfo getUserInfo() {
        // TODO:
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName("Test");
        userInfo.setIconURL("");
        return userInfo;
    }

    public void getOrderList(final OrderRequest request, final OrderCallback callback) {
        RetrofitUtils.newInstence(GlobalField.BASE_ORDER_URL)
                .create(APIService.class)
                .getOrderInfo(request.getPageNow(), request.getPageSize(), request.getStatus())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserHttpResult>() {
                    private OrderList orderList = null;

                    @Override
                    public void onCompleted() {
                        callback.onCompleted(request.getStatus(), orderList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserHttpResult userHttpResult) {
                        String jsonData = userHttpResult.getData().toString();
                        orderList =  GsonUtil.parseJsonWithGson(jsonData, OrderList.class);
                    }
                });
    }
}
