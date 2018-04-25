package com.funhotel.hmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funhotel.hmvp.R;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.OnItemClickListner;

import java.util.List;

/**
 * CLASS ： MainRecyclerViewAdapter
 * Author : zhiyahan
 * TIME : 2017/12/22 12:15
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context mContext;
    private View itemView;
    private OnItemClickListner mOnItemClickListner;
    private MyViewHolder mMyViewHolder;
    private List<String> list;

    public MainRecyclerViewAdapter(Context context,List<String> list,OnItemClickListner onItemClickListner) {
        this.mContext=context;
        this.mOnItemClickListner=onItemClickListner;
        this.list=list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(mContext).inflate(R.layout.item_main_list, parent,
                false);
        return new MyViewHolder(itemView, mOnItemClickListner);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        mMyViewHolder=(MyViewHolder)holder;
        mMyViewHolder.mTextView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        super.onViewRecycled(holder);


    }

     class MyViewHolder extends BaseViewHolder{

        private TextView mTextView;

         public MyViewHolder(View itemView, OnItemClickListner onItemClickListner) {
             super(itemView, onItemClickListner);

             mTextView = (TextView) itemView.findViewById(R.id.tvTitle);
         }


     }
}
