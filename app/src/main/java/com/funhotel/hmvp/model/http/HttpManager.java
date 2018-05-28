/*
 *   Copyright (C) 2018  ZME
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.funhotel.hmvp.model.http;

import com.funhotel.hmvp.model.entity.New;
import com.funhotel.hmvp.model.entity.NewEntity;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhiyahan on 2017/3/28.
 * Http网络交互管理
 */
public class HttpManager {

  private static final String TAG = "HttpManager";
  private IHttpService httpService;
  private static volatile HttpManager httpManager;
  private static final int DEFAULT_TIMEOUT = 5 * 1000;

  /**
   * HttpManager 构造器
   *
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
    httpService = retrofit.create(IHttpService.class);
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
   *
   * @param resourceSubscriber resourceSubscriber
   */
  public <T> void getNews(String type, @NonNull ResourceSubscriber<T> resourceSubscriber) {
    Flowable<New> flowable = httpService.getNew("APPCODE 4698d85543574f4d9c12a2fc889c5814", type)
        .map(new HttpResultFunction<New>());
    toSubscribe(flowable, resourceSubscriber);
  }

  private <T> void toSubscribe(@NonNull Flowable flowable,
      @NonNull ResourceSubscriber<T> resourceSubscriber) {
    flowable.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(resourceSubscriber);
  }


  /**
   * 获取新闻列表
   * @param type
   * @param callback
   */
  public void getNewA(String type, Callback<NewEntity> callback) {
    Call<NewEntity> call = httpService.getNew1("APPCODE 4698d85543574f4d9c12a2fc889c5814", type);
    call.enqueue(callback);
  }


}

