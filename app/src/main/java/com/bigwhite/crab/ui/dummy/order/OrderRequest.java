package com.bigwhite.crab.ui.dummy.order;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class OrderRequest {
    private int pageNow;
    private int pageSize;
    private int status;

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
}
