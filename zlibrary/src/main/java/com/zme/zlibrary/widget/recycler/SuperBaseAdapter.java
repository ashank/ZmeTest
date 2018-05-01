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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Description ：万能Adapter的抽象类
 * Author：ZME
 * Create Time ：2018/4/22 22:38
 * Modify Time：2018/4/22 22:38
 * Version：1.0
 */
public abstract class SuperBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

  private Context mContext;
  private List<T> mDatas;
  private int layoutId;
  private  View itemView;
  private BaseViewHolder holder;

  private  OnItemClickListner onItemClickListner;
  private OnItemLongClickListner onItemLongClickListner;
  private  OnItemTouchListener onItemTouchListener;

  private IMulItemViewType<T> mulItemViewType;

  public SuperBaseAdapter(Context mContext,List<T> mDatas,int layoutId) {
    this.mContext=mContext;
    this.mDatas = mDatas;
    this.layoutId=layoutId;
  }

  public SuperBaseAdapter(Context mContext, List<T> mDatas,IMulItemViewType<T> mulItemViewType) {
    this.mContext = mContext;
    this.mDatas = mDatas;
    this.mulItemViewType = mulItemViewType;
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
    if (mulItemViewType!=null){
      mulItemViewType.getItemViewType(position,mDatas.get(position));
    }
    return super.getItemViewType(position);
  }

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (mulItemViewType==null){
      itemView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
    }else {
      if (mulItemViewType.getViewTypeCount()>1){
        layoutId=mulItemViewType.getLayoutId(viewType);
      }
      itemView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
    }
    return new BaseViewHolder(mContext, itemView);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder holder, int position) {
    if (null == mDatas || mDatas.isEmpty()) {
      return;
    }
    //绑定数据和视图
    binViewHolder(holder,mDatas.get(position),position);
    if (onItemClickListner!=null){
      holder.setOnItemClickListener(onItemClickListner,position);
    }
    if (onItemTouchListener!=null){
      holder.setOnItemTouchListener(onItemTouchListener,position);
    }
    if (onItemLongClickListner!=null){
      holder.setOnItemLongClickListener(onItemLongClickListner,position);
    }
  }
  public abstract void binViewHolder(BaseViewHolder viewHolder,T t,int position);




  public void setOnItemClickListener(OnItemClickListner onItemClickListner) {
    this.onItemClickListner = onItemClickListner;
  }

  public void setOnItemLongClickListener(
      OnItemLongClickListner onItemLongClickListner) {
    this.onItemLongClickListner = onItemLongClickListner;
  }

  public void setOnItemTouchListener(
      OnItemTouchListener onItemTouchListener) {
    this.onItemTouchListener = onItemTouchListener;
  }

}
