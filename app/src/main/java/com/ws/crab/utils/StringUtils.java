package com.ws.crab.utils;

/**
 * Created by Administrator on 2017/5/7.
 */

public class StringUtils {
    public static boolean isEmpty(String str){
        if(null==str || str.equals("")){
            return  true;
        }else{
            return  false;
        }
    }
}
