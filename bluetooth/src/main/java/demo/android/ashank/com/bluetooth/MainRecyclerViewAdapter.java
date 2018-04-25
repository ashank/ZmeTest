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

package demo.android.ashank.com.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.OnItemClickListner;
import java.util.LinkedList;
import java.util.List;

/**
 * CLASS ： MainRecyclerViewAdapter
 * Author : zhiyahan
 * TIME : 2017/12/22 12:15
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

  private Context mContext;
  private View itemView;
  private OnItemClickListner mOnItemClickListner;
  private MyViewHolder mMyViewHolder;
  private List<BluetoothDevice> list = new LinkedList<>();

  public MainRecyclerViewAdapter(Context context, List<BluetoothDevice> list,
      OnItemClickListner onItemClickListner) {
    this.mContext = context;
    this.mOnItemClickListner = onItemClickListner;
    this.list = list;
  }

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    itemView = LayoutInflater.from(mContext).inflate(R.layout.item_main_list, parent,
        false);
    return new MyViewHolder(itemView, mOnItemClickListner);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder holder, int position) {
    mMyViewHolder = (MyViewHolder) holder;
    if (null == list || list.isEmpty()) {
      return;
    }
    mMyViewHolder.mTextView.setText(list.get(position).getName());
    mMyViewHolder.tvAdress.setText(list.get(position).getAddress());
//    mMyViewHolder.tvStatus.setText(list.get(position).getBondState());
  }

  @Override
  public int getItemCount() {

    if (null == list || list.isEmpty()) {
      return 0;
    }
    return list.size();
  }


  @Override
  public void onViewRecycled(BaseViewHolder holder) {
    super.onViewRecycled(holder);


  }

  public void setList(List<BluetoothDevice> list) {
    this.list = list;
  }

  class MyViewHolder extends BaseViewHolder {

    private TextView mTextView;
    private TextView tvAdress;
    private TextView tvStatus;

    public MyViewHolder(View itemView, OnItemClickListner onItemClickListner) {
      super(itemView, onItemClickListner);
      mTextView = (TextView) itemView.findViewById(R.id.item_tv_name);
      tvAdress = (TextView) itemView.findViewById(R.id.item_tv_adress);
      tvStatus = (TextView) itemView.findViewById(R.id.item_tv_state);
    }

  }
}
