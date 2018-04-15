package com.bigwhite.crab.ui.dummy.merchant;

import com.bigwhite.crab.base.APIService;
import com.bigwhite.crab.base.GlobalField;
import com.bigwhite.crab.base.OnHttpCallBack;
import com.bigwhite.crab.bean.MerchantList;
import com.bigwhite.crab.bean.OrderList;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.http.RetrofitUtils;
import com.bigwhite.crab.ui.dummy.order.Goods;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/4/15.
 */

public class MerchantListInfoModel implements MerchantsContract.MerchantListModel {
    @Override
    public void upLoad(int pageNow, int pageSize, String token, int merchantId, final OnHttpCallBack callBack) {
        RetrofitUtils.newInstence(GlobalField.GOODS_BASEURL,false)
                .create(APIService.class)
                .getMerchants(pageNow,pageSize,
                        merchantId,token)
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
                    public void onNext(UserHttpResult<MerchantList> userHttpResult) {
                        MerchantList orderList = (MerchantList) userHttpResult.getObject();
                        callBack.onSuccessful(orderList);
                    }
                });
    }
}
