package com.funhotel.hmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.model.entity.User;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.OnItemClickListner;
import java.util.List;

/**
 * CLASS ： MainRecyclerViewAdapter。
 * Author : zhiyahan
 * TIME : 2017/12/22 12:15
 */

public class GreenDaoListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private View itemView;
    private OnItemClickListner mOnItemClickListner;
    private MyViewHolder mMyViewHolder;
    private List<User> list;

    public GreenDaoListAdapter(final Context context, final  List<User> list, final OnItemClickListner onItemClickListner) {
        this.mContext = context;
        this.mOnItemClickListner = onItemClickListner;
        this.list = list;
    }

  /**
   *
   * @param parent {@link ViewGroup}
   * @param viewType s视图类型
   * @return
   */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(mContext).inflate(R.layout.item_main_list, parent,
                false);
        return new MyViewHolder(itemView, mOnItemClickListner);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        mMyViewHolder = (MyViewHolder) holder;
        mMyViewHolder.mTextView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        super.onViewRecycled(holder);


    }

     /**
      * ViewHolder
     */
     private class MyViewHolder extends BaseViewHolder {

        private TextView mTextView;

        MyViewHolder(View itemView, OnItemClickListner onItemClickListner) {
             super(itemView, onItemClickListner);
             mTextView = (TextView) itemView.findViewById(R.id.tvTitle);
         }


     }
}
