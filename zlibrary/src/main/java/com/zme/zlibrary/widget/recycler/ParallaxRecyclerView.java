package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description ：拖动视差效果的RecyclerView
 * Author：ZME
 * Create Time ：2018/4/24 23:40
 * Modify Time：2018/4/24 23:40
 * Version：1.0
 */
public class ParallaxRecyclerView extends RecyclerView {

  public ParallaxRecyclerView(Context context) {
    super(context);
  }

  public ParallaxRecyclerView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public ParallaxRecyclerView(Context context,
      @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }


  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    int action = event.getAction();
    if (!isEnabled()){
      return super.onInterceptTouchEvent(event);
    }

    switch (action){
      case MotionEvent.ACTION_DOWN:
        break;
        case MotionEvent.ACTION_MOVE:
          break;
      case MotionEvent.ACTION_UP:
        break;
    }
    return super.onInterceptTouchEvent(event);
  }
}
