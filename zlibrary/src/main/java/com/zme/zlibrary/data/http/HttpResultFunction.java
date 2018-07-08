package com.zme.zlibrary.data.http;

import android.util.Log;
import io.reactivex.functions.Function;


/**
 * 作者：zhiyahan on 2018/3/27 14:34
 * 用来统一处理Http的resultCode,并将BaseEntity的Data部分剥离出来返回给subscriber
 *@param <T>  Subscriber 真正需要的数据类型，也就是Data部分的数据类型
 */
public  class HttpResultFunction<T> implements Function<BaseEntity<T>, T> {
  private HttpRequestListener<T> httpRequestListener;
  private int pageIndex;

  public HttpResultFunction(HttpRequestListener<T> httpRequestListener,int pageIndex) {
    this.httpRequestListener= httpRequestListener;
    this.pageIndex = pageIndex;
  }

  @Override
  public T apply(BaseEntity<T> baseEntity){
    Log.e("TAG", "apply: "+baseEntity.getMsg() +" "+baseEntity.getStatus() );
    if (baseEntity == null ){
      httpRequestListener.onRequestFail(null,pageIndex);
      return  null;
    }else if (!HttpStatusConstant.HTTP_STATUS_SUCCESS.equals(baseEntity.getStatus()) ) {
       httpRequestListener.onRequestFail(baseEntity.getResult(),pageIndex);
      return  baseEntity.getResult();
    }else {
      return baseEntity.getResult();
    }

  }

}
