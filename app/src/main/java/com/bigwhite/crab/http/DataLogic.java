package com.bigwhite.crab.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigwhite.crab.model.OrderInfo;
import com.bigwhite.crab.model.OrdersList;
import com.bigwhite.crab.model.UserInfo;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class DataLogic {
    private static DataLogic instance;

    public synchronized static DataLogic getInstance() {
        if (instance == null) {
            instance = new DataLogic();
        }
        return instance;
    }

    private static Object parseObject(String json, Class clazz) {
        try {
            return JSONObject.parseObject(json, clazz);
        } catch (Exception e) {
        }
        return null;
    }

    public UserInfo getUserInfo() {
        // TODO:
//        String json = HttpUtil.get("");
//        return (UserInfo) parseObject(json,UserInfo.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName("Test");
        userInfo.setIconURL("");
        return userInfo;
    }

    public OrdersList getOrdersInfo() {
        // TODO:
        OrdersList ordersList = new OrdersList();
        OrderInfo info1 = new OrderInfo();
        info1.setName("徐超");
        info1.setPhone("18661866172");
        info1.setAddress("江苏省润和创智中心");
        info1.setPoint("380");
        ordersList.addOrder(info1);

        OrderInfo info2 = new OrderInfo();
        info2.setName("任康");
        info2.setPhone("18661866172");
        info2.setAddress("江苏省润和创智中心");
        info2.setPoint("380");
        ordersList.addRelease(info2);

        OrderInfo info3 = new OrderInfo();
        info3.setName("王沛");
        info3.setPhone("18661866172");
        info3.setAddress("江苏省润和创智中心");
        info3.setPoint("380");
        ordersList.addRelease(info3);


        OrderInfo info4 = new OrderInfo();
        info4.setName("汪汉平");
        info4.setPhone("18661866172");
        info4.setAddress("江苏省润和创智中心");
        info4.setPoint("380");
        ordersList.addDone(info4);
        return ordersList;
    }
}
