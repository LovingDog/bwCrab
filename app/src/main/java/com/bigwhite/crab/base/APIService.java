package com.bigwhite.crab.base;

import com.bigwhite.crab.bean.UserHttpResult;
import com.squareup.okhttp.RequestBody;

import retrofit.http.GET;
import retrofit.http.Multipart;
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
     *
     * @return RxJava 对象
     */

    @Multipart
    @POST("addGoods.do")
    Observable<UserHttpResult> userUploadImg(
            @Query("info") String info,
            @Query("price") String price,
            @Query("integral") int integral,
            @Query("exchangeCode") String exchangeCode,
            @Query("token") String token,
            @Query("merchantId") String merchantId,
            @Part("files\";fileName = \"test.jpg\"") RequestBody img);

    /**
     * 用户订单的接口
     *
     * @param pageNow
     * @param pageSize
     * @param status
     * @param merchantId
     * @param token
     * @return
     */
    @GET("/goods/findByPage.do")
    Observable<UserHttpResult> getOrderInfo(
            @Query("pageNow") int pageNow,
            @Query("pageSize") int pageSize,
            @Query("status") int status,
            @Query("merchantId") int merchantId,
            @Query("token") String token
    );

    /**
     * 用户登录的接口
     *
     * @param phone
     * @param password
     * @return
     */
    @GET("/login.do")
    Observable<UserHttpResult> useLogin(
            @Query("phone") String phone,
            @Query("password") String password
    );
    /**
     * 登录的接口
     *
     * @return
     */
    @GET("login.do")
    Observable<UserHttpResult> userLogin(
            @Query("phone") String pageNow,
            @Query("password") String pageSize
    );
}
