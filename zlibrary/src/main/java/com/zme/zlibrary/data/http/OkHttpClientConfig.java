package com.zme.zlibrary.data.http;

import android.util.ArrayMap;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description ：OkHttpClientConfig  OKHttp配置
 * Author：ZME
 * Create Time ：2018/7/7 11:15
 * Modify Time：2018/7/7 11:15
 * Version：1.0
 */
public class OkHttpClientConfig {

    private static final int DEFAULT_TIMEOUT = 5 * 1000;
    private String url;
    private ArrayMap<Object,Object> headerMap;

    public OkHttpClientConfig(String url) {
        this.url = url;
    }

    public OkHttpClientConfig(String url,ArrayMap<Object, Object> headerMap) {
        this.url = url;
        this.headerMap = headerMap;
    }

    public void setHeaderMap(ArrayMap<Object, Object> headerMap) {
        this.headerMap = headerMap;
    }

    public Retrofit initRetrofit ()  {
        //创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder
                = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //添加Header
        if (headerMap !=null && !headerMap.isEmpty()){
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    return chain.proceed(addHeader(headerMap,chain));
                }
            });
        }
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }


    /**
     * 添加请求头
     * example
     *  httpClient.addInterceptor(new Interceptor() {
     public Response intercept(Chain chain) throws IOException {
     //网络拦截器，可以在这里打印信息，或者额外的事情
     return chain.proceed(addHeader(map, chain));
     }
     });
     * @param map 请求头集合
     * @param chain {@link Interceptor.Chain}
     * @return Request
     */
    private Request addHeader(final ArrayMap<Object, Object> map, Interceptor.Chain chain) {
        Request.Builder request = chain.request().newBuilder();
        if (map == null || map.isEmpty()) {
            return null;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object value = entry.getValue();
            request.addHeader((String) entry.getKey(), (String) value);
        }
        return request.build();
    }

}