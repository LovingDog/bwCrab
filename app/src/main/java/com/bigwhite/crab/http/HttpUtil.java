package com.bigwhite.crab.http;

import android.net.ParseException;
import android.text.TextUtils;
import android.util.Log;

import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.utils.SystemUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HttpUtil {
    public static String get(String uri) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String token = new AppPreference(SystemUtil.getApplication()).getLoginToken();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("Authorization", token); //认证token
        httpGet.addHeader("token", token); //认证token
        httpGet.setHeader("Cookie","JSESSIONID=2C8FA17E79CC6FF65F92ACE50A2D2412");
        String str = invoke(httpClient, new HttpGet(uri));
        Log.d("HttpUtil", "get uri = " + uri + ", str = " + str);
        httpClient.getConnectionManager().shutdown();
        return str;
    }

    private static String invoke(DefaultHttpClient httpClient, HttpUriRequest uriRequest) {
        return parseResponse(sendRequest(httpClient, uriRequest));
    }

    private static String parseResponse(HttpResponse response) {
        if (response == null) {
            return null;
        }
        try {
            HttpEntity entity = response.getEntity();
            EntityUtils.getContentCharSet(entity);
            return EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String uri, Map<String, Object> params) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String str = invoke(httpClient, postForm(uri, params));
        httpClient.getConnectionManager().shutdown();
        if ((str != null) && (str.startsWith("\"")) && (str.endsWith("\""))) {
            str = str.substring(1, -1 + str.length());
        }
        return str;
    }

    private static HttpPost postForm(String uri, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(uri);
        ArrayList paramList = new ArrayList();
        Iterator localIterator = params.keySet().iterator();
        String key;
        String value;
        while (localIterator.hasNext()) {
            key = (String) localIterator.next();
            if (!TextUtils.isEmpty(key)) {
                value = (String) params.get(key);
                paramList.add(new BasicNameValuePair(key, value));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpPost;
    }

    private static HttpResponse sendRequest(DefaultHttpClient client, HttpUriRequest request) {
        try {
            return client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
