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

import com.zme.zlibrary.data.http.HttpException;
import io.reactivex.functions.Function;


/**
 * 作者：zhiyahan on 2018/3/27 14:34
 * 用来统一处理Http的resultCode,并将BaseEntity的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber 真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunction<T> implements Function<BaseEntity<T>, T> {

  private static final int HTTP_STATUS = 0;

  @Override
  public T apply(BaseEntity<T> baseEntity) throws Exception {
    if (baseEntity.getError_code() != HTTP_STATUS) {
      throw new HttpException(baseEntity.getError_code(), "apiException:" + baseEntity.getReason());
    }
    return baseEntity.getData();
  }
}
