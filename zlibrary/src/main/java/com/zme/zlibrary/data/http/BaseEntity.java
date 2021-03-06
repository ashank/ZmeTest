package com.zme.zlibrary.data.http;

/**
 * 作者：Zhiyahan on 2018/3/10 10:32
 * 数据统一封装类
 */
public class BaseEntity<T> {

  private String status;
  private String msg;
  private T result;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
