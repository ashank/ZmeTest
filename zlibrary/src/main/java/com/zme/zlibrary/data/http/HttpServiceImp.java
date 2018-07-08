package com.zme.zlibrary.data.http;

import android.util.ArrayMap;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;

/**
 * Description ：HttpSeviveImp
 * Author：ZME
 * Create Time ：2018/7/7 11:17
 * Modify Time：2018/7/7 11:17
 * Version：1.0
 */
public class HttpServiceImp<T> implements HttpService<T> {

    private  HttpRequestListener mHttpRequestListener;
    private  int pageIndex;
    private HttpService httpService;
    private List<Flowable> flowables =new ArrayList<>();
    private  ArrayMap<Object,Object> headerMap;
    private String url;
    private static  HttpServiceImp httpServiceImp;
    private OkHttpClientConfig config;

    private HttpServiceImp() {
         config =new OkHttpClientConfig(HttpConstant.BASE_URL1);
    }

    private HttpServiceImp(ArrayMap<Object,Object> headerMap) {
        this.headerMap =headerMap;
        config =new OkHttpClientConfig(HttpConstant.BASE_URL1);
    }

    public  static HttpServiceImp with(){
        if (null == httpServiceImp){
            httpServiceImp=new HttpServiceImp();
        }
       return new HttpServiceImp();
    }

    public HttpServiceImp addHeaders(ArrayMap<Object, Object> headerMap) {
        this.headerMap = headerMap;
        config.setHeaderMap(headerMap);
        return  this;
    }

    public  HttpServiceImp setHttpRequestListener(HttpRequestListener mHttpRequestListener) {
        this.mHttpRequestListener = mHttpRequestListener;
        return  this;
    }

    public HttpServiceImp init(){
        Retrofit retrofit = config.initRetrofit();
        httpService = retrofit.create(HttpService.class);
        return  this;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Flowable<BaseEntity<List<String>>> getNewType() {
        Flowable flowable = httpService.getNewType()
                .onBackpressureBuffer()
                .map(new HttpResultFunction(mHttpRequestListener, pageIndex))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        startHttp(flowable);
        return flowable;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Flowable<BaseEntity<NewEntityNew>> getNewList(String channel, String num, String start) {
        Flowable flowable=httpService.getNewList(channel,num,start)
                .onBackpressureBuffer()
                .map(new HttpResultFunction(mHttpRequestListener,pageIndex))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        startHttp(flowable);
        return flowable;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Flowable<BaseEntity<T>> searchNew(String keyword) {
        Flowable flowable = httpService.searchNew(keyword)
                .onBackpressureBuffer()
                .map(new HttpResultFunction(mHttpRequestListener,pageIndex))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        startHttp(flowable);
        return flowable;
    }

    @SuppressWarnings("unchecked")
    private void startHttp(Flowable flowable){
        flowables.add(flowable);
        flowable.subscribe(new ResourceSubscriber() {
            @Override
            public void onNext(Object o) {
                if (mHttpRequestListener!=null){
                    mHttpRequestListener.onRequestSuccess(o,pageIndex);
                }
                this.dispose();
            }

            @Override
            public void onError(Throwable t) {
                if (mHttpRequestListener!=null){
                    mHttpRequestListener.onHttpFail(t,"加载失败",pageIndex);
                }
                this.dispose();
            }

            @Override
            public void onComplete() {
                this.dispose();
            }
        });
    }


    /**
     * 取消请求
     */
    private  void cancelHttp(){
        if (flowables ==null) {
            return;
        }
        flowables.clear();
    }

}
