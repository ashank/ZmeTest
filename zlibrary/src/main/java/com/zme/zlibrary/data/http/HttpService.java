package com.zme.zlibrary.data.http;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Query、QueryMap：用于Http Get请求传递参数
 * Field：用于Post方式传递参数,需要在请求接口方法上添加@FormUrlEncoded,即以表单的方式传递参数
 * Body：用于Post,根据转换方式将实例对象转化为对应字符串传递参数.比如Retrofit添加GsonConverterFactory则是将body转化为gson字符串进行传递
 * Path：用于URL上占位符
 * Part：配合@Multipart使用,一般用于文件上传
 * Header：添加http header
 * Headers：跟@Header作用一样,只是使用方式不一样,@Header是作为请求方法的参数传入,@Headers是以固定方式直接添加到请求方法上 <p>Created by
 * Zhiyahan on 2017/3/24 接口调用
 */

public interface HttpService<T> extends ISuperHttpApi{

  @GET("/open/api/lunar/json.shtml")
  Flowable<BaseEntity<Calendar>> getCalendar(@Query("date") String date);


  @GET("/toutiao/index")
  Flowable<BaseEntity<Calendar>> getNew(@Query("type") String type);


}
