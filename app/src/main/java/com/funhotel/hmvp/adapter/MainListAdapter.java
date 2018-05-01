package com.funhotel.hmvp.adapter;

import android.content.Context;
import com.funhotel.hmvp.R;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.SuperBaseAdapter;
import java.util.List;

/**
 * Description ：MainListAdapter
 * Author：ZME
 * Create Time ：2018/4/26 22:25
 * Modify Time：2018/4/26 22:25
 * Version：1.0
 */
public class MainListAdapter extends SuperBaseAdapter<String>  {

  public MainListAdapter(Context mContext, List<String> mDatas) {
    super(mContext,mDatas,R.layout.item_main_list);
  }

  public MainListAdapter(Context mContext, List<String> mDatas,
      int layoutId) {
    super(mContext, mDatas, layoutId);
  }

  @Override
  public void binViewHolder(BaseViewHolder viewHolder, String s, int position) {
    viewHolder.setText(R.id.tvTitle,s);
  }


}
