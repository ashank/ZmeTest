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
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.zme.zlibrary.utils.LogUtils;

/**
 * Description ：DragLayout
 * Author：ZME
 * Create Time ：2018/4/19 23:44
 * Modify Time：2018/4/19 23:44
 * Version：1.0
 */
public class DragLayout extends ViewGroup {

  /**
   * 滑动对象
   */
  private Scroller scroller;
  /**
   * 最小移动距离
   */
  private int mTouchSlop;

  private float mXDown;
  private float mXMove;


  /**
   * 上次触发ACTION_MOVE事件时的屏幕坐标
   */
  private float mXLastMove;

  /**
   * 界面可滚动的左边界
   */
  private int leftBorder;

  /**
   * 界面可滚动的右边界
   */
  private int rightBorder;


  public DragLayout(Context context) {
    super(context);
  }

  public DragLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public DragLayout(Context context, AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);

  }

  public DragLayout(Context context, AttributeSet attrs,
      int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context);
  }

  private void init(Context context) {
    scroller = new Scroller(context);
    mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
  }


  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      View view = getChildAt(i);
      measureChild(view, widthMeasureSpec, heightMeasureSpec);
    }

  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      View view = getChildAt(i);
      view.layout(i * getMeasuredWidth(), i + 50 * i, (i + 1) * getMeasuredWidth(),
          getMeasuredHeight());
      // TODO 初始化左右边界值
      //TODO 如果要做回弹，可以把边界扩大
      leftBorder = getChildAt(0).getLeft()-100;
      rightBorder = getChildAt(getChildCount() - 1).getRight()+100;
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    LogUtils.e(">>>onInterceptHoverEvent>>"+event.getAction());
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        //获取按下时针对anroid 坐标系的x坐标
        mXDown = event.getRawX();
        //TODO 保存最后按下的x坐标
        mXLastMove = mXDown;
        break;
      case MotionEvent.ACTION_MOVE:
        //TODO 在移动过程中不断获取x坐标
        mXDown = event.getRawX();
        //TODO 计算移动的x坐标和按下时的坐标差，如果大于touchSlop的距离，则允许滑动
        float diff = Math.abs(mXMove - mXDown);
        if (diff > mTouchSlop) {
          //TODO 返回ture 则消费这个事件
          return true;
        }
        break;
    }
    return super.onInterceptTouchEvent(event);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mXDown = event.getRawX();
        //TODO 获得移动开始的点坐标
        mXLastMove = mXDown;
        return true;
      case MotionEvent.ACTION_MOVE:
        //TODO 实时获取移动的坐标
        mXMove = event.getRawX();
        //TODO 计算移动的距离,右滑为正数，左滑为负数
        int scrolledX = (int) (mXLastMove - mXMove);
        //TODO 为了防止拖动的距离超出边界值，需要自动滚动处理
        if (getScrollX() + scrolledX < leftBorder) {
          scrollTo(leftBorder, 0);
          return true;
        } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
          scrollTo(rightBorder - getWidth(), 0);

          return true;
        }
        scrollBy(scrolledX, 0);
        mXLastMove = mXMove;
        break;
      case MotionEvent.ACTION_UP:
        //TODO 在用户手指弹起时，此时的x坐标则等于mXLastMove，这里判断弹起后滑动的距离是否大于某个值，如果大于，则自动滚动
        int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
        LogUtils.e(">>>>UP=="+getWidth()+"=="+getScrollX());
        int dx = targetIndex * getWidth() - getScrollX();
        // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
        scroller.startScroll(getScrollX(), 0, dx, 0);
        invalidate();
        break;
    }
    return super.onTouchEvent(event);
  }

  @Override
  public void computeScroll() {
    if (scroller.computeScrollOffset()) {
      scrollTo(scroller.getCurrX(), scroller.getCurrY());
      invalidate();
    }
  }

}
