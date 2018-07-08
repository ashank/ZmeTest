package com.zme.zlibrary.data.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
public class HttpManager extends ResourceSubscriber {

  private static final String TAG = "HttpManager";
  private HttpService httpService;
  private static volatile HttpManager httpManager;
  private static final int DEFAULT_TIMEOUT = 5 * 1000;
  private  int pageIndex;
  private static HttpRequestListener mHttpRequestListener;

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

  public  void setHttpRequestListener(HttpRequestListener httpRequestListener) {
    mHttpRequestListener = httpRequestListener;
  }

  @SuppressWarnings("unchecked")
  public void httpNewList(String channel,String start){
    this.pageIndex ++;
    httpService.getNewList(channel,String.valueOf(pageIndex),start)
            .map(new HttpResultFunction(mHttpRequestListener,pageIndex))
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this);
  }

  @SuppressWarnings("unchecked")
  public void httpNewType(){
    httpService.getNewType()
            .map(new HttpResultFunction(mHttpRequestListener,pageIndex))
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this);
  }

  @SuppressWarnings("unchecked")
  public void searchNew(String keyWord){
    httpService.searchNew(keyWord)
            .map(new HttpResultFunction(mHttpRequestListener,pageIndex))
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this);
  }




  @Override
  @SuppressWarnings("unchecked")
  public void onNext(Object entity) {
    if (mHttpRequestListener!=null){
      mHttpRequestListener.onRequestSuccess(entity,pageIndex);
    }
  }

  @Override
  public void onError(Throwable t) {
    //错误要重置
    pageIndex--;
    if (mHttpRequestListener!=null){
      mHttpRequestListener.onHttpFail(t,"加载失败",pageIndex);
    }
  }

  @Override
  public void onComplete() {

  }

}

