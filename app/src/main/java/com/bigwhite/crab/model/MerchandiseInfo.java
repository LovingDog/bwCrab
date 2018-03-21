package com.bigwhite.crab.model;


import java.io.Serializable;

/**
 * Created by Jameson on 18-3-21.
 */

public class MerchandiseInfo implements Serializable {

    private static final long serialVersionUID = 6775863165713525837L;
    // 商品ID
    public long mId;
    // 商品价格
    public float mPrice;
    // 兑换商品所需积分
    public int mIntegral;
    // 商品描述
    public String mDescription;
    // 商品预览图片链接
    public String mPreviewUrl;
    // 选择模式下该商品是否选中
    public boolean mIsselected;

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public int getmIntegral() {
        return mIntegral;
    }

    public void setmIntegral(int mIntegral) {
        this.mIntegral = mIntegral;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPreviewUrl() {
        return mPreviewUrl;
    }

    public void setmPreviewUrl(String mPreviewUrl) {
        this.mPreviewUrl = mPreviewUrl;
    }

    public boolean ismIsselected() {
        return mIsselected;
    }

    public void setmIsselected(boolean mIsselected) {
        this.mIsselected = mIsselected;
    }

    @Override
    public String toString() {
        return "MerchandiseInfo{" +
                "mId=" + mId +
                ", mPrice=" + mPrice +
                ", mIntegral=" + mIntegral +
                ", mDescription='" + mDescription + '\'' +
                ", mPreviewUrl='" + mPreviewUrl + '\'' +
                ", mIsselected=" + mIsselected +
                '}';
    }
}


