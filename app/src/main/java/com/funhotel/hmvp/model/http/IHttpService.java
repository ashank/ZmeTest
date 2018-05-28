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

package com.funhotel.hmvp.model.http;

import com.funhotel.hmvp.model.entity.New;
import com.funhotel.hmvp.model.entity.NewEntity;
import com.funhotel.hmvp.model.entity.NewEntity1;
import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

public interface IHttpService<T> {

  @GET("/toutiao/index")
  Flowable<BaseEntity<New>> getNew(@Header("Authorization") String authorization,@Query("type") String type);


  @GET("/toutiao/index")
  Call<NewEntity> getNew1(@Header("Authorization") String authorization,@Query("type") String type);


  @GET("/news/get")
  Call<NewEntity1> getNewList(@Header("Authorization") String authorization,@Query("type") String type);



}
