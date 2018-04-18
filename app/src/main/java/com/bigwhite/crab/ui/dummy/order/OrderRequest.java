package com.bigwhite.crab.ui.dummy.order;

import com.bigwhite.crab.base.BaseRequest;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class OrderRequest implements BaseRequest {
    private int pageNow;
    private int pageSize;
    private int status;
    private int merchantId;
    private String token;

    public OrderRequest() {
        this.pageNow = 0;
        this.pageSize = 10;
    }

    public OrderRequest(int status) {
        this.pageNow = 0;
        this.pageSize = 10;
        this.status = status;
    }

    public OrderRequest(int pageNow, int status) {
        this.pageNow = pageNow;
        this.pageSize = 10;
        this.status = status;
    }

    public OrderRequest(int pageNow, int pageSize, int status) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.status = status;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
