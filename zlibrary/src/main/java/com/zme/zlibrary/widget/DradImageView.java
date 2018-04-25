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

package com.zme.zlibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.zme.zlibrary.utils.LogUtils;

/**
 * Description ：仿微信的的图片下滑关闭样式GestureDetector
 * Author：ZME
 * Create Time ：2018/4/19 20:26
 * Modify Time：2018/4/19 20:26
 * Version：1.0
 */
public class DradImageView extends AppCompatImageView {

  public DradImageView(Context context) {
    super(context);
  }

  public DradImageView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public DradImageView(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }


 /* public DradImageView(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }*/

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    LogUtils.e(">>>>>>dispatchTouchEvent");
    return super.dispatchTouchEvent(event);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float x = 0;
    float y = 0;
    float x1 = 0;
    float y1 = 0;
    LogUtils.e(">>>>>>onTouchEvent");
    int count = event.getPointerCount();
    if (count > 1) {
      return false;
    }
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        x = event.getX();
        x = event.getY();
        break;
      case MotionEvent.ACTION_MOVE:
        LogUtils.e(">>>>>>>>>>action_move");
        x1 = event.getX();
        y1 = event.getY();
        //TODO 水平或者往上滑动无效
        if (x1 != x || y1 < y) {
          return false;
        }
        int offesty = (int) (1 - y1 - y);
        this.animate().scaleX(offesty).scaleY(offesty).translationY(y1).start();
        break;
      case MotionEvent.ACTION_UP:
        //TODO 当用户放开手指，则让图片回到原来的地方
        if (viewUpLisenter != null) {
          viewUpLisenter.onViewUpLisenter();
        }
        x=0;
        x1=0;
        y=0;
        y1=0;
        break;

      case MotionEvent.ACTION_CANCEL:
        //TODO 当用户取消动作，则让图片回到原来的地方
        if (viewUpLisenter != null) {
          viewUpLisenter.onViewUpLisenter();
        }
        break;
    }

    return super.onTouchEvent(event);
  }


  private ViewUpLisenter viewUpLisenter;

  public ViewUpLisenter getViewUpLisenter() {
    return viewUpLisenter;
  }

  public void setViewUpLisenter(ViewUpLisenter viewUpLisenter) {
    this.viewUpLisenter = viewUpLisenter;
  }

  public interface ViewUpLisenter {

    void onViewUpLisenter();
  }

}
