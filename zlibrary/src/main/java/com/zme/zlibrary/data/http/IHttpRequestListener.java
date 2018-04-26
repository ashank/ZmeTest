package com.zme.zlibrary.data.http;

/**
 * Description ：网络回调接口
 * Author：ZME
 * Create Time ：2018/4/26 21:48
 * Modify Time：2018/4/26 21:48
 * Version：1.0
 */
public interface IHttpRequestListener<T> {

  void onSuccess(T t);

  void onFail(Throwable t,String message);

}
