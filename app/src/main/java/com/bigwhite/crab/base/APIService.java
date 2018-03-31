package com.bigwhite.crab.base;



import com.bigwhite.crab.bean.IPHttpResult;
import com.bigwhite.crab.bean.IpInfo;
import com.bigwhite.crab.bean.Movies;
import com.bigwhite.crab.bean.TokenResult;
import com.bigwhite.crab.bean.UserHttpResult;
import com.squareup.okhttp.RequestBody;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import rx.Observable;

/**
 * API--接口  服务[这里处理的是同一的返回格式 resultCode  resultInfo Data<T> --->这里的data才是返回的结果,T就是泛型 具体返回的been对象或集合]
 */
public interface APIService {

    /**
     * 用户登录的接口
     * @return RxJava 对象
     */
    @POST("addGoods.do")
    Observable<UserHttpResult> userUploadImg(
            @Query("info") String info,
            @Query("price") String price,
            @Query("integral") int integral,
            @Query("exchangeCode") String exchangeCode,
            @Part("file\";fileName = \"test.jpg\"")RequestBody img) ;

}
