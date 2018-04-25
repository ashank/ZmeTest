package com.zme.zlibrary.data.http;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhiyahan on 2017/3/28.
 * Http网络交互管理
 */
public class HttpManager {

  private static final String TAG = "HttpManager";
  private HttpService httpService;
  private static volatile HttpManager httpManager;
  private static final int DEFAULT_TIMEOUT = 5 * 1000;

  /**
   * HttpManager 构造器
   * @param url Http 请求的公共URL部分
   */
  private HttpManager(String url) {
    //创建一个OkHttpClient并设置超时时间
    OkHttpClient.Builder httpClientBuilder
        = new OkHttpClient.Builder()
        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

    Retrofit retrofit = new Retrofit.Builder()
        .client(httpClientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .build();

    //获取Service
    httpService = retrofit.create(HttpService.class);
  }

  /**
   * @param url Http 请求的公共URL部分
   * @return 返回一个单例HttpManager实例
   */
  public static HttpManager getInstance(String url) {
    if (httpManager == null) {
      synchronized (HttpManager.class) {
        if (httpManager == null) {
          httpManager = new HttpManager(url);
        }
      }
    }
    return httpManager;
  }

  /**
   * 获取
   * @param resourceSubscriber resourceSubscriber
   */
  public  <T> void getCalendar(String date,@NonNull  ResourceSubscriber<T>  resourceSubscriber)  {
    Flowable<Calendar> flowable = httpService.getCalendar(date)
        .map(new HttpResultFunction<Calendar>());
    toSubscribe(flowable, resourceSubscriber);
  }

  private <T> void toSubscribe(@NonNull Flowable flowable,@NonNull ResourceSubscriber<T> resourceSubscriber) {
    flowable.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(resourceSubscriber);
  }


}

