package com.bigwhite.crab.http;

import com.bigwhite.crab.ui.dummy.order.GoodsInfo;
import com.bigwhite.crab.bean.OrderList;
import com.bigwhite.crab.utils.GsonUtil;

import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2018/4/1 0001.
 */
public class DataLogicTest {
    @Test
    public void test1() throws Exception {
        String jsonData = "{ \"content\" : [ { \"id\" : 1, \"createtime\" : 1522150308562, \"updatetime\" : " +
                "1522150308562, \"username\" : \"钦哥\", \"phone\" : \"15195756146\", \"address\" : \"南京市 雨花台区 凤翔新城\", " +
                "\"goods\" : { \"id\" : 4, \"createtime\" : 1522150308562, \"updatetime\" : null, \"info\" : " +
                "\"大螃蟹8只\", \"price\" : \"128\", \"integral\" : 388, \"exchangeCode\" : \"abcde3456a\", \"pics\" : " +
                "\"f29d89bc-73ae-4c44-92d5-e33342fb282d.png\" }, \"status\" : 0, \"kuaidiNum\" : null } ], \"last\" :" +
                " true, \"totalPages\" : 1, \"totalElements\" : 1, \"size\" : 10, \"number\" : 0, \"sort\" : null, " +
                "\"first\" : true, \"numberOfElements\" : 1 }";
        json2OrderList(jsonData);
    }

    private OrderList json2OrderList(String jsonData) {
        OrderList orderList = GsonUtil.parseJsonWithGson(jsonData, OrderList.class);
        System.out.println("heqiang orderList = " + orderList);
        List<GoodsInfo> contents = orderList.getContent();
        for (GoodsInfo goodsInfo : contents) {
            System.out.println("heqiang goodsInfo = " + goodsInfo);
            System.out.println("heqiang goodsInfo = " + goodsInfo.getGoods());
        }
        return orderList;
    }

}