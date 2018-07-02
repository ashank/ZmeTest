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

package com.funhotel.hmvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import butterknife.ButterKnife;
import com.funhotel.hmvp.R;
import com.zme.zlibrary.data.http.HttpConstant;
import com.zme.zlibrary.data.http.IHttpRequestListener;
import com.zme.zlibrary.data.http.NewEntityNew;
import io.reactivex.disposables.Disposable;

/**
 * 权限页面的获取
 */
public class RxJavaDemoActivity extends AppCompatActivity {

  Disposable disposable = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rx_java);
    ButterKnife.bind(this);

//    //创建一个上游
//    Observable.create(new ObservableOnSubscribe<Integer>() {
//
//      @Override
//      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//        Log.d("所在的线程：", Thread.currentThread().getName());
//        Log.d("发送的数据:", 1 + "");
//        e.onNext(1);
//        e.onNext(2);
//        e.onNext(3);
//
//        e.onComplete();
//        e.onNext(4);
//      }
//    })
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Observer<Integer>() {
//          private int i;
//
//          @Override
//          public void onSubscribe(Disposable d) {
//            Log.e("Rxjava", "onSubscribe");
//            disposable = d;
//          }
//
//          @Override
//          public void onNext(Integer integer) {
//            Log.d("所在的线程：", Thread.currentThread().getName());
//            Log.d("接收到的数据:", "integer:" + integer);
//            Log.e("Rxjava", "onNext==" + integer);
//            i++;
//            if (i == 2) {
//              disposable.dispose();
//              Log.e("Rxjava", "disposable==" + disposable.isDisposed());
//            }
//
//          }
//          @Override
//          public void onError(Throwable e) {
//            Log.e("Rxjava", "onError");
//          }
//
//          @Override
//          public void onComplete() {
//            Log.e("Rxjava", "onComplete");
//          }
//        });

//      final HttpManager httpManager = HttpManager.getInstance(HttpConstant.BASE_URL);
//      Flowable.interval(5*1000, TimeUnit.MILLISECONDS)
//              .subscribeOn(Schedulers.io())
//              .doOnNext(new Consumer<Long>() {
//                  @Override
//                  public void accept(Long aLong) throws Exception {
//                      Log.e("TAG", "accept");
//                      httpManager.getNewA("top", new Callback<NewEntity>() {
//                          @Override
//                          public void onResponse(Call<NewEntity> call, Response<NewEntity> response) {
//                              Log.e("TAG", "onResponse: "+ response.body().getResult());
//                              ((TextView)findViewById(R.id.tv)).setText("sdsdsdsdsd");
//                          }
//                          @Override
//                          public void onFailure(Call<NewEntity> call, Throwable t) {
//
//                              Log.e("TAG", ": onFailure");
//                          }
//                      });
//                  }
//              }).subscribe();


      com.zme.zlibrary.data.http.HttpManager.getInstance(HttpConstant.BASE_URL1).setiHttpRequestListener(
              new IHttpRequestListener<Object>() {
                  @Override
                  public void onSuccess(Object o) {
                      if (o instanceof NewEntityNew)
                      Log.e("TAG", "onSuccess: " + ((NewEntityNew)o).getChannel());
                  }

                  @Override
                  public void onFail(Throwable t, String message) {

                  }
              });

    /*HandlerThread handlerThread=new HandlerThread("myThread");
    handlerThread.start();

     final Handler handler=new Handler(handlerThread.getLooper()){
      @Override
      public void handleMessage(Message msg) {
        LogUtils.e("current Thread=="+Thread.currentThread());
        super.handleMessage(msg);
      }
    };
    handler.sendEmptyMessage(1);
*/




  }

  @Override
  protected void onResume() {
    super.onResume();


  }


}
