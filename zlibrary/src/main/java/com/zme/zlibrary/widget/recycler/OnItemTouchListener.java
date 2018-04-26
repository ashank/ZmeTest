package com.zme.zlibrary.widget.recycler;

import android.view.MotionEvent;
import android.view.View;

/**
 * Description ：OnItemTouchListener
 * Author：ZME
 * Create Time ：2018/4/26 22:54
 * Modify Time：2018/4/26 22:54
 * Version：1.0
 */
public interface OnItemTouchListener {

  void onItemTouch(View v, MotionEvent event,int position);

}
