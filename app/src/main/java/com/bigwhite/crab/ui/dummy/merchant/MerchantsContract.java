package com.bigwhite.crab.ui.dummy.merchant;

import com.bigwhite.crab.base.OnHttpCallBack;
import com.bigwhite.crab.ui.dummy.upload.UploadInfo;

/**
 * Created by admin on 2018/4/15.
 */

public class MerchantsContract {
    public interface MerchantListView{
        void uploadSuccess(Object o);
        void upLoadFail();
        void reFreshActivity();
        int getPageNow();
        int getPageSize();
        String getToke();
        int getMerchantid();

    }

    public interface MerchantListPresenter{
        void upLoad();
    }

    public interface MerchantListModel {
        void upLoad(int pageNow,int pageSize,String token,int merchantId,OnHttpCallBack callBack);
    }
}
