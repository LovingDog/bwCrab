package com.bigwhite.crab.base;

import com.bigwhite.crab.bean.MerchantList;
import com.bigwhite.crab.bean.OrderList;
import com.bigwhite.crab.bean.UserHttpResult;
import com.bigwhite.crab.ui.dummy.order.Goods;
import com.squareup.okhttp.RequestBody;

import okhttp3.MultipartBody;
import retrofit.http.FormUrlEncoded;
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
    Observable<UserHttpResult<OrderList>> userUploadImg(
            @Query("info") String info,
            @Query("price") String price,
            @Query("integral") int integral,
            @Query("token") String token,
            @Query("count") int count,
            @Query("merchantId") int merchantId,
            @Part("files") MultipartBody.Part part);

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
    @GET("findPageByCondition.do")
    Observable<UserHttpResult<OrderList>> getOrderInfo(
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
    @GET("login.do")
    Observable<UserHttpResult> userLogin(
            @Query("phone") String phone,
            @Query("password") String password
    );

    @GET("findPageByCondition.do")
    Observable<UserHttpResult<MerchantList>> getMerchants(
            @Query("pageNow") int pageNow,
            @Query("pageSize") int pageSize,
            @Query("merchantId") int merchantId,
            @Query("token") String token
    );
}
