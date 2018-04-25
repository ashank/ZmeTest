package com.zme.zlibrary.data.http;

import io.reactivex.functions.Function;


/**
 * 作者：zhiyahan on 2018/3/27 14:34
 * 用来统一处理Http的resultCode,并将BaseEntity的Data部分剥离出来返回给subscriber
 *@param <T>  Subscriber 真正需要的数据类型，也就是Data部分的数据类型
 */
public  class HttpResultFunction<T> implements Function<BaseEntity<T>, T> {

  private static final int HTTP_STATUS=200;
  @Override
  public T apply(BaseEntity<T> baseEntity) throws Exception {
    if (baseEntity.getStatus() != HTTP_STATUS) {
      throw new ApiException(baseEntity.getStatus(), "apiException:"+baseEntity.getMessage());
    }
    return baseEntity.getData();
  }
}
