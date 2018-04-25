package com.zme.zlibrary.data.http;

import android.util.ArrayMap;
import android.util.Log;
import java.util.Iterator;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 网络管理器
 * <p>
 * Created by zhiyahan on 2017/2/28.
 */
public class OkHttpClientConfigManager {

  private static volatile OkHttpClientConfigManager retrofitManager;
  private   OkHttpClient.Builder builder;

  /**
   * OkhttpClient配置的类
   */
  public OkHttpClientConfigManager() {

    Interceptor interceptor = new LogInterceptor(new LogInterceptor.Logger() {
      @Override
      public void log(String message) {
        Log.i("LOG", message);
      }
    });

    builder  = new OkHttpClient.Builder().addInterceptor(interceptor);
  }

  public static OkHttpClientConfigManager getInstance() {
    if (retrofitManager == null) {
      synchronized (OkHttpClientConfigManager.class) {
        if (retrofitManager == null) {
          retrofitManager = new OkHttpClientConfigManager();
        }
      }
    }
    return retrofitManager;
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
   * @param chain{Interceptor.Chain}
   * @return Request
   */
  public Request addHeader(final ArrayMap<Object, Object> map, Interceptor.Chain chain) {
    Request.Builder request = chain.request().newBuilder();
    if (map == null || map.size() == 0) {
      return null;
    }
    if (null != map && !map.isEmpty()) {
      Iterator it = map.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry entry = (Map.Entry) it.next();
        Object value = entry.getValue();
        request.addHeader((String) entry.getKey(), (String) value);
      }
    }
    return request.build();
  }


}