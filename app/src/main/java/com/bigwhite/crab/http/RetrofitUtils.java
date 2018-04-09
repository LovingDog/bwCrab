package com.bigwhite.crab.http;

import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.utils.SystemUtil;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Retofit网络请求工具类
 */
public class RetrofitUtils {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private static Retrofit mRetrofit;

    private RetrofitUtils() {

    }

    public static Retrofit newInstence(String url) {
        mRetrofit = null;
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.MINUTES);
        client.setConnectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS);
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("Cookie","JSESSIONID=2C8FA17E79CC6FF65F92ACE50A2D2412")
//                        .addHeader("Authorization",new AppPreference(SystemUtil.getApplication()).getLoginToken())
//                        .addHeader("token",new AppPreference(SystemUtil.getApplication()).getLoginToken())
                        .build();
                return chain.proceed(request);
            }
        });

        mRetrofit = new Retrofit.Builder()
                .client(client)//添加一个client,不然retrofit会自己默认添加一个
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        return mRetrofit;
    }
}
