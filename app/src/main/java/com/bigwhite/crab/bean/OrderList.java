package com.bigwhite.crab.bean;

import com.bigwhite.crab.ui.dummy.order.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class OrderList {
    /**
     * 订单列表
     */
    private List<GoodsInfo> content = new ArrayList<>();
    /**
     * 总共元素
     */
    private int totalElements;
    /**
     * 当前页数
     */
    private int number;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 是否是第一页
     */
    private boolean first;
    /**
     * 是否是最后一页
     */
    private boolean last;

    /**
     * 一页数量
     */
    private int size;
    /**
     * 订单总数量
     */
    private int numberOfElements;
    /**
     * 排序
     */
    private String sort;

    public OrderList() {
    }

    public OrderList(List<GoodsInfo> content, int totalElements, int totalPages, boolean last, int number, int size,
                     String sort, int numberOfElements, boolean first) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
        this.number = number;
        this.size = size;
        this.sort = sort;
        this.numberOfElements = numberOfElements;
        this.first = first;
    }

    public List<GoodsInfo> getContent() {
        return content;
    }

    public void setContent(List<GoodsInfo> content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    public int size(){
        return content.size();
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "content=" + content +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                ", number=" + number +
                ", size=" + size +
                ", sort='" + sort + '\'' +
                ", numberOfElements=" + numberOfElements +
                ", first=" + first +
                '}';
    }
}
