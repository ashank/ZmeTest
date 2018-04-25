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
import android.graphics.Canvas;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Description ：
 * <p> 1创建ViewDragHelper实例
 * 2 处理ViewGroup的触摸事件
 * 3 ViewDragHelper.Callback的编写
 * <p/>
 * Author：ZME
 * Create Time ：2018/4/11 12:40
 * Modify Time：2018/4/11 12:40
 * Version：1.0
 */
public class DrapView extends LinearLayout {

  private ViewDragHelper mDragHelper;
  private int width;
  private int heigth;
  private int childWidth = 360;
  private int childHeight = 480;

  private int left=80;
  private int top=100;

  View dragView;
  View edgeDragView;
  View autoBackView;

  int autoBackViewOriginLeft;
  int autoBackViewOriginTop;

  public DrapView(Context context) {
    super(context);
    init();
  }

  public DrapView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public DrapView(Context context, AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public DrapView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  /**
   * 初始化ViewDragHelper
   */
  public void init() {
    // 滑动相关类 sensitivity主要用于设置touchSlop：
    mDragHelper = ViewDragHelper.create(this, 10f, new DragHelperCallback());
    mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    DisplayMetrics dm = new DisplayMetrics();
    heigth = dm.heightPixels;
    width = dm.widthPixels;
  }


  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    //获取view下的总子菜单数量
    final int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      //实例化子菜单
      final TextView child = (TextView) getChildAt(i);
      autoBackView=getChildAt(0);
      edgeDragView=getChildAt(1);
      dragView=getChildAt(2);

      //按钮的布局
//      child.layout(left + i * childWidth+10, top , left +(i+1)* childWidth+10, top + childHeight);
    }

    autoBackViewOriginLeft = autoBackView.getLeft();
    autoBackViewOriginTop = autoBackView.getTop();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  private class DragHelperCallback extends ViewDragHelper.Callback {

    /**
     * 尝试捕获子view，一定要返回true;返回true 表示捕获相关View，你可以根据第一个参数child决定捕获哪个View。
     *
     * @param view child 尝试捕获的view
     * @param pointerId pointerId 指示器id？ 这里可以决定哪个子view可以拖动 //return mCanDragView == view;
     */
    @Override
    public boolean tryCaptureView(View view, int pointerId) {
      return view==dragView||view==autoBackView;
    }

    /**
     * 处理水平方向上的拖动
     * @param child child 被拖动到view
     * @param left1 left 移动到达的x轴的距离
     * @param dx dx 建议的移动的x距离
     */
    @Override
    public int clampViewPositionHorizontal(View child, int left1, int dx) {
      System.out.println("left = " + 1 + ", dx = " + dx);
      left=left1;
      // 两个if主要是为了让view  ViewGroup里
      if (getPaddingLeft() > left1) {
        return getPaddingLeft();
      }

      if (getWidth() - child.getWidth() < left1) {
        return getWidth() - child.getWidth();
      }else if (left1<0){
        left1=0;
      }
      return left1;
    }

    /**
     * 处理竖直方向上的拖动
     *
     * @param child child 被拖动到view
     * @param top1 top 移动到达的y轴的距离
     * @param top1 top 建议的移动的y距离
     */
    @Override
    public int clampViewPositionVertical(View child, int top1, int dy) {

      top=top1;

      // 两个if主要是为了让viewViewGroup里
      if (getPaddingTop() > top1) {
        return getPaddingTop();
      }

      if (getHeight() - child.getHeight() < top1) {
        return getHeight() - child.getHeight();
      }

      return top1;
    }

    /**
     * 当拖拽到状态改变时回调
     *
     * @params 新的状态
     */
    @Override
    public void onViewDragStateChanged(int state) {
      switch (state) {
        case ViewDragHelper.STATE_DRAGGING:  // 正在被拖动
          //获取view下的总子菜单数量
          final int childCount = getChildCount();
          for (int i = 0; i < childCount; i++) {
            //实例化子菜单
            final TextView child = (TextView) getChildAt(i);
            //按钮的布局
            child.layout(left + i * width, top + i * heigth, left + childWidth + i * width, top + childHeight + i * heigth);
          }

          break;
        case ViewDragHelper.STATE_IDLE:  // view没有被拖拽或者 正在进行fling/snap

          break;
        case ViewDragHelper.STATE_SETTLING: // fling完毕后被放置到一个位置
          break;
      }
      super.onViewDragStateChanged(state);
    }

    @Override
    public void onViewReleased(View releasedChild, float xvel, float yvel) {
      super.onViewReleased(releasedChild, xvel, yvel);
      if(releasedChild==autoBackView){
        mDragHelper.settleCapturedViewAt(autoBackViewOriginLeft,autoBackViewOriginTop);
        invalidate();
      }
    }

    @Override
    public void onEdgeDragStarted(int edgeFlags, int pointerId) {
      super.onEdgeDragStarted(edgeFlags, pointerId);
      mDragHelper.captureChildView(edgeDragView, pointerId);
    }
  }


  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    switch (ev.getAction()) {
      case MotionEvent.ACTION_CANCEL:
      case MotionEvent.ACTION_DOWN:
        mDragHelper.cancel(); // 相当于调用 processTouchEvent收到ACTION_CANCEL
        break;
    }
    /**
     * 检查是否可以拦截touch事件
     * 如果onInterceptTouchEvent可以return true 则这里return true
     */
    return mDragHelper.shouldInterceptTouchEvent(ev);
  }


  @Override
  public boolean onTouchEvent(MotionEvent event) {
    /**
     * 处理拦截到的事件
     * 这个方法会在返回前分发事件
     */
    mDragHelper.processTouchEvent(event);
    return true;
  }


  @Override
  public void computeScroll() {
    super.computeScroll();
    if (mDragHelper.continueSettling(true)) {
      invalidate();
    }
  }
}
