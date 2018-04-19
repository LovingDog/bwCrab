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
        int getGoodId();
        void deleteSuccess();
        void deleteFail(String error);
        String getToke();
        int getMerchantid();

    }

    public interface MerchantListPresenter{
        void upLoad();
        void delete();
    }

    public interface MerchantListModel {
        void upLoad(int pageNow,int pageSize,String token,int merchantId,OnHttpCallBack callBack);
        void upLoad(int id,String token,OnHttpCallBack callBack);
    }
}
