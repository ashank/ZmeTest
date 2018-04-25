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

package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

/**
 * Description ：万能Adapter的抽象类
 * Author：ZME
 * Create Time ：2018/4/22 22:38
 * Modify Time：2018/4/22 22:38
 * Version：1.0
 */
public abstract class BaseCommAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

  private Context mContext;
  private List<T> mDatas;
  private int layoutId;

  public BaseCommAdapter(List<T> mDatas) {
    this.mDatas = mDatas;
  }

  @Override
  public int getItemCount() {
    return mDatas == null ? 0 : mDatas.size();
  }

  @Override
  public long getItemId(int position) {
    return position;
  }


  @Override
  public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    return null;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    if (null == mDatas || mDatas.isEmpty()) {
      return;
    }
    //绑定数据和视图
    binViewHolder(holder,mDatas.get(position));
  }

  public abstract void binViewHolder(ViewHolder viewHolder,T t);
}
