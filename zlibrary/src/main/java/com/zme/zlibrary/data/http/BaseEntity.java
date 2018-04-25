package com.zme.zlibrary.data.http;

/**
 * 作者：Zhiyahan on 2018/3/10 10:32
 * 数据统一封装类
 */
public class BaseEntity<T> {

  private int status;
  private String message;
  private T data;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
