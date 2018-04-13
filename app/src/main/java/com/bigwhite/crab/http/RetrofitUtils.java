package com.bigwhite.crab.http;

import com.bigwhite.crab.preference.AppPreference;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.bigwhite.crab.utils.SystemUtil.getApplication;

/**
 * Retofit网络请求工具类
 */
public class RetrofitUtils {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private static Retrofit mRetrofit;
    private static AppPreference mApppreference;
    private static boolean mFirstLogin = true;

    private RetrofitUtils() {

    }

    public static Retrofit newInstence(String url,boolean login) {
        mRetrofit = null;
        mApppreference = new AppPreference(getApplication());
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.MINUTES);
        client.setConnectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS);
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response;
                String cookie = mApppreference.getCookies();
                if (!cookie.equals("")) {
                    Request compressedRequest = request.newBuilder()
                            .header("Content-type","application/x-www-form-urlencoded; charset=UTF-8")
                            .header("cookie", cookie.substring(0,cookie.length()-1))
                            .build();

                    response = chain.proceed(compressedRequest);
                }else{
                    response = chain.proceed(request);
                }
                return response;
            }
        });
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                    final StringBuffer cookieBuffer = new StringBuffer();
                    Observable.from(originalResponse.headers("Set-Cookie"))
                            .map(new Func1<String, String>() {
                                @Override
                                public String call(String s) {
                                    String[] cookieArray = s.split(";");
                                    return cookieArray[0];
                                }
                            })
                            .subscribe(new Action1<String>() {
                                @Override
                                public void call(String cookie) {
                                    cookieBuffer.append(cookie).append(";");
                                }
                            });
                    mApppreference.setCookies(cookieBuffer);
                }
                return originalResponse ;
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
