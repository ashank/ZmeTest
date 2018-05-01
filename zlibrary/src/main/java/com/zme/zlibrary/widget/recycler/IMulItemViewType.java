package com.zme.zlibrary.widget.recycler;

/**
 * Description ：IMulItemViewType
 * Author：ZME
 * Create Time ：2018/4/27 22:53
 * Modify Time：2018/4/27 22:53
 * Version：1.0
 */
public interface IMulItemViewType<T> {

  int getItemViewType(int position,T t);

  int getViewTypeCount();

  int getLayoutId(int viewType);

}
