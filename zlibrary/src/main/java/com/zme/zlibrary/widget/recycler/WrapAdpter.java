package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.zme.zlibrary.R;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;

/**
 * Description ：WrapAdpter
 * Author：ZME
 * Create Time ：2018/6/27 22:07
 * Modify Time：2018/6/27 22:07
 * Version：1.0
 */
public class WrapAdpter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_FOOTER=-10;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public WrapAdpter(Context context,RecyclerView.Adapter adapter) {
        this.mContext=context;
        this.mAdapter=adapter;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==VIEW_FOOTER||mAdapter==null){
            return new BaseViewHolder(mContext,mLayoutInflater.inflate(R.layout.view_load_more,parent,false),
            null,null,null);
        }else {
            return (BaseViewHolder) mAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (mAdapter!=null&&position<getItemCount()-1){
            mAdapter.onBindViewHolder(holder,position);
        }else {
            LinearLayout.LayoutParams linearLayoutCompat=(LayoutParams) holder.getView(R.id.tv).getLayoutParams();
            linearLayoutCompat.gravity= Gravity.CENTER;
            holder.getView(R.id.tv).setLayoutParams(linearLayoutCompat);
        }
    }

    @Override
    public int getItemCount() {
        if (mAdapter==null||mAdapter.getItemCount()==0){
            return 1;
        }else {
            return mAdapter.getItemCount()+1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mAdapter==null||mAdapter.getItemCount()==0||position==mAdapter.getItemCount()){
            return VIEW_FOOTER;
        }
        return mAdapter.getItemViewType(position);
    }
}
