package com.zme.zlibrary.data.http;

/**
 * Description ：网络回调接口
 * Author：ZME
 * Create Time ：2018/4/26 21:48
 * Modify Time：2018/4/26 21:48
 * Version：1.0
 */
public interface HttpRequestListener<T> {

  /**
   * 请求成功，且不为空
   * @param t
   * @param pageIndex
   */
  void onRequestSuccess(T t,int pageIndex);

  /**
   *  请求失败
   * @param t
   * @param pageIndex
   */
  void onRequestFail(T t,int pageIndex);

  /**
   * http 链接失败
   * @param t
   * @param message
   * @param pageIndex
   * @return
   */
  void onHttpFail(Throwable t,String message,int pageIndex);

}
