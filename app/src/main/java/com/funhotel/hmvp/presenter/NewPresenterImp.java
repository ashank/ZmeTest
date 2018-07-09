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

import android.support.annotation.NonNull;
import android.util.Log;
import com.funhotel.hmvp.model.http.HttpManager;
import com.funhotel.hmvp.model.viewmodel.NewViewModel;
import com.zme.zlibrary.base.AbstractListPresenter;
import com.zme.zlibrary.base.BaseView;
import com.zme.zlibrary.data.http.HttpRequestListener;
import com.zme.zlibrary.data.http.HttpServiceImp;
import com.zme.zlibrary.data.http.NewEntityNew;

/**
 * Description ：处理新闻的逻辑
 * Author：ZME
 * Create Time ：2018/4/18 22:44
 * Modify Time：2018/4/18 22:44
 * Version：1.0
 */
public class NewPresenterImp extends AbstractListPresenter implements HttpRequestListener<NewEntityNew> {
  private NewViewModel newViewModel;
  private String type;
  private  HttpManager httpManager;

  private HttpServiceImp httpServiceImp;
  private String pageSize="20";

  public NewPresenterImp(@NonNull NewViewModel newViewModel, String type) {
    super(newViewModel);
    this.newViewModel=newViewModel;
    this.type = type;
//    httpManager = HttpManager.getInstance(HttpConstant.BASE_URL);
    httpServiceImp = HttpServiceImp.with().setHttpRequestListener(this).init();
  }

  @Override
  public void onRefresh() {
    super.onRefresh();
    onStartHttp();
  }

  @Override
  public void onLoadMore() {
    super.onLoadMore();
    pageIndex++;
    onStartHttp();
  }

  private void onStartHttp(){
    if (newViewModel!=null){
      newViewModel.onLoadingView(pageIndex,0);
    }
    httpServiceImp.getNewList(type,pageSize,String.valueOf(pageIndex));
  }

  @Override
  public void onRequestSuccess(NewEntityNew o, int pageIndex) {
    Log.e("TAG", "onRequestSuccess: ");
    if (null == o) {
      newViewModel.onLoadDataFailure(null,pageIndex,0);
      return;
    }
    if ( o.getList().size()==0) {
      newViewModel.onLoadDataFailure(o,pageIndex,0);
      return;
    }
    newViewModel.onLoadDataSuccess(o,pageIndex,1);
  }

  @Override
  public void onRequestFail(NewEntityNew o, int pageIndex) {
    newViewModel.onLoadDataFailure(null,pageIndex,0);
    this.pageIndex--;
  }

  @Override
  public void onHttpFail(Throwable t, String message, int pageIndex) {
    newViewModel.onLoadDataFailure(null,pageIndex,0);
    this.pageIndex--;
  }

  @Override
  public void attachView(BaseView baseView) {
    //加载一些数据

  }

  @Override
  public void   detachView() {
    //干掉
  }

}
