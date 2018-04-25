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

package com.funhotel.hmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.model.entity.NewEntity;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.OnItemClickListner;
import java.util.List;

/**
 * CLASS ： 新闻列表适配器
 * Author : zhiyahan
 * TIME : 2017/12/22 12:15
 */

public class NewListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

  private Context mContext;
  private View itemView;
  private OnItemClickListner mOnItemClickListner;
  private MyViewHolder mMyViewHolder;
  private NewEntity.ResultEntity aNew;
  private List<NewEntity.ResultEntity.DataEntity> list;

  /**
   * @param context {@link Context}
   * @param aNew {@link NewEntity.ResultEntity}
   * @param onItemClickListner {@link OnItemClickListner}
   */
  public NewListAdapter(Context context, NewEntity.ResultEntity aNew,
      OnItemClickListner onItemClickListner) {
    this.mContext = context;
    this.mOnItemClickListner = onItemClickListner;
    this.aNew = aNew;
    if (null != aNew) {
      list = aNew.getData();
    }
  }

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    itemView = LayoutInflater.from(mContext).inflate(R.layout.item_new_list, parent,
        false);

    return new MyViewHolder(itemView, mOnItemClickListner);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder holder, int position) {
    mMyViewHolder = (MyViewHolder) holder;
    if (null == list || list.isEmpty()) {
      return;
    }
    mMyViewHolder.mTextView.setText(list.get(position).getTitle());
    /*mMyViewHolder.tvNewType.setText(list.get(position).getCategory());*/
    mMyViewHolder.tvNewpaper.setText(list.get(position).getAuthor_name());

    Glide.with(mContext).load(list.get(position).getThumbnail_pic_s())
        .centerCrop().into(mMyViewHolder.imageView1);
    if (null == list.get(position).getThumbnail_pic_s02() || list.get(position)
        .getThumbnail_pic_s02().isEmpty()) {
      mMyViewHolder.imageView2.setVisibility(View.INVISIBLE);
    } else {
      Glide.with(mContext).load(list.get(position).getThumbnail_pic_s02())
          .centerCrop().into(mMyViewHolder.imageView2);
    }

    if (null == list.get(position).getThumbnail_pic_s03() || list.get(position)
        .getThumbnail_pic_s03().isEmpty()) {
      mMyViewHolder.imageView3.setVisibility(View.INVISIBLE);
    } else {
      Glide.with(mContext).load(list.get(position).getThumbnail_pic_s03())
          .centerCrop().into(mMyViewHolder.imageView3);
    }

  }

  @Override
  public int getItemCount() {
    if (null == list || list.isEmpty()) {
      return 0;
    }
    return list.size();
  }

  @Override
  public int getItemViewType(int position) {

    return super.getItemViewType(position);
  }

  @Override
  public long getItemId(int position) {
    return super.getItemId(position);
  }

  @Override
  public void onViewRecycled(BaseViewHolder holder) {
    super.onViewRecycled(holder);
  }

  class MyViewHolder extends BaseViewHolder {

    private TextView mTextView;
    private TextView tvNewType;
    private TextView tvNewpaper;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    public MyViewHolder(View itemView, OnItemClickListner onItemClickListner) {
      super(itemView, onItemClickListner);
      mTextView = (TextView) itemView.findViewById(R.id.item_tv_name);
      tvNewType = (TextView) itemView.findViewById(R.id.item_tv_new_type);
      tvNewpaper = (TextView) itemView.findViewById(R.id.item_tv_newspaper);
      imageView1 = (ImageView) itemView.findViewById(R.id.iv_thumb01);
      imageView2 = (ImageView) itemView.findViewById(R.id.iv_thumb02);
      imageView3 = (ImageView) itemView.findViewById(R.id.iv_thumb03);


    }
  }

  public void setaNew(NewEntity.ResultEntity aNew) {
    this.aNew = aNew;
  }
}
