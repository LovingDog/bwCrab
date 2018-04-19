package com.bigwhite.crab.ui.dummy.merchant;

import com.bigwhite.crab.base.OnHttpCallBack;

/**
 * Created by admin on 2018/4/15.
 */

public class MerchantListInfoPresenter implements MerchantsContract.MerchantListPresenter {

    private final MerchantListInfoModel mMerchantListInfoModel;
    private MerchantsContract.MerchantListView mMerchantListView;

    public MerchantListInfoPresenter(MerchantsContract.MerchantListView merchantListView) {
        mMerchantListView = merchantListView;
        mMerchantListInfoModel = new MerchantListInfoModel();
    }
    @Override
    public void upLoad() {
        mMerchantListInfoModel.upLoad(mMerchantListView.getPageNow(), mMerchantListView.getPageSize(),
                mMerchantListView.getToke(),mMerchantListView.getMerchantid(), new OnHttpCallBack() {
            @Override
            public void onSuccessful(Object o) {
                mMerchantListView.uploadSuccess(o);
            }

            @Override
            public void onFailed(String errorMsg) {
                mMerchantListView.upLoadFail();
            }
        });
    }

    @Override
    public void delete() {
        mMerchantListInfoModel.upLoad(mMerchantListView.getGoodId(), mMerchantListView.getToke(), new OnHttpCallBack() {
            @Override
            public void onSuccessful(Object o) {
                mMerchantListView.deleteSuccess();
            };

            @Override
            public void onFailed(String errorMsg) {
                mMerchantListView.deleteFail(errorMsg);
            }
        });
    }
}
