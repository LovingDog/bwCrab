package com.bigwhite.crab.ui.dummy.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class OrderList {
    private int totalPages;
    private int totalElements;
    private List<GoodsInfo> content = new ArrayList<>();

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<GoodsInfo> getContent() {
        return content;
    }

    public void setContent(List<GoodsInfo> content) {
        this.content = content;
    }

    public int size() {
        return content.size();
    }

    @Override
    public String toString() {
        return "OrderList:{ totalPages = " + totalPages + ", totalElements = " + totalElements + ", goodsInfoList = "
                + content.size() + "}";
    }
}
