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

package com.funhotel.hmvp.presenter;

import com.funhotel.hmvp.model.entity.NewEntity;
import com.funhotel.hmvp.model.http.HttpManager;
import com.funhotel.hmvp.model.viewmodel.NewViewModel;
import com.zme.zlibrary.data.http.HttpConstant;
import com.zme.zlibrary.utils.LogUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description ：处理新闻的逻辑
 * Author：ZME
 * Create Time ：2018/4/18 22:44
 * Modify Time：2018/4/18 22:44
 * Version：1.0
 */
public class NewPresenterImp implements NewPresenter {

  private NewViewModel newViewModel;
  private String type;

  public NewPresenterImp(String type) {
    this.type = type;
  }

  @Override
  public void attachView(final NewViewModel view) {
    this.newViewModel=view;
    onStartHttp();
  }

  public void onStartHttp(){
    if (newViewModel!=null){
      newViewModel.onStartHttp();
    }
    HttpManager httpManager = HttpManager.getInstance(HttpConstant.BASE_URL);
    httpManager.getNewA(type, new Callback<NewEntity>() {
      @Override
      public void onResponse(Call<NewEntity> call, Response<NewEntity> response) {

        NewEntity entity = response.body();
        if (null == entity) {
          LogUtils.e("entity为空");
          if (null != newViewModel) {
            newViewModel.bindData(null);
          }
          return;
        }
        LogUtils.e("entity==" + entity.getReason() + ">>>>>" + entity.getError_code());
        NewEntity.ResultEntity anew = entity.getResult();
        if (null == anew) {
          LogUtils.e("New为空");
          if (null != newViewModel) {
            newViewModel.bindData(null);
          }
          return;
        }
        if (null != newViewModel) {
          newViewModel.bindData(anew);
        }
      }
      @Override
      public void onFailure(Call<NewEntity> call, Throwable t) {
        if (null!=newViewModel){
          newViewModel.bindData(null);
        }

      }
    });
  }


  @Override
  public void detachView() {
    newViewModel = null;
  }

  public NewViewModel getNewViewModel() {
    return newViewModel;
  }

  public void setNewViewModel(NewViewModel newViewModel) {
    this.newViewModel = newViewModel;
  }

  @Override
  public void onCreate() {

  }

  @Override
  public void onResume() {

  }

  @Override
  public void onPause() {

  }

  @Override
  public void onStop() {

  }

  @Override
  public void onDestroy() {

  }
}
