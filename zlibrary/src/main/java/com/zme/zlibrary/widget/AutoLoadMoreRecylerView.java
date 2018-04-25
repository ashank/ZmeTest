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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.bumptech.glide.Glide;
import com.zme.zlibrary.utils.LogUtils;

/**
 * Description ：实现能够自动加载更多的Recyclerview
 * Author：ZME
 * Create Time ：2018-03-28 15 ：55
 * Modify Time：2018-03-28 15 ：55
 * Version：1.0
 */
public class AutoLoadMoreRecylerView extends RecyclerView {

  private ILoadMoreListener iLoadMoreListener;    //加载更多回调
  private boolean isLoading = false; //是否正在加载中
  private int itemOfferset = 4;//视图偏移量，当滑倒底部还剩多少个的时候，就开始加载
  private boolean isScrollStop = false;//是否停止滑动了

  public AutoLoadMoreRecylerView(Context context) {
    super(context);
  }

  public AutoLoadMoreRecylerView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

  }

  public AutoLoadMoreRecylerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  /**
   * @param state 状态为0时：当前屏幕停止滚动；状态为1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；状态为2时：随用户的操作，屏幕上产生的惯性滑动
   */
  @Override
  public void onScrollStateChanged(int state) {
    switch (state) {
      case RecyclerView.SCROLL_STATE_IDLE:
        //停止滑动，显示图片
        LogUtils.e("0");
        if (Glide.with(getContext()).isPaused()) {
          Glide.with(getContext()).onStart();
        }
        isScrollStop = true;
        break;
      case RecyclerView.SCROLL_STATE_DRAGGING:
        //滚动，手指还在屏幕，显示图片
        LogUtils.e("1");
        isScrollStop = false;
        if (Glide.with(getContext()).isPaused()) {
          Glide.with(getContext()).resumeRequests();
        }
        break;
      case RecyclerView.SCROLL_STATE_SETTLING:
        isScrollStop = false;
        //惯性滚动，停止显示图片
        LogUtils.e("2");
        if (!Glide.with(getContext()).isPaused()) {
          Glide.with(getContext()).pauseRequests();
        }
        break;
    }
    super.onScrollStateChanged(state);
  }

  @Override
  public void onScrolled(int dx, int dy) {
    super.onScrolled(dx, dy);
    LogUtils.e(">>>>>" + dx + ">>>>>>" + dy);
    int lastPosition = ((LinearLayoutManager) getLayoutManager())
        .findLastCompletelyVisibleItemPosition();
    //滑动到底部显示最后一个/或者滑动到距离底部itemOfferset个才加载
    if (lastPosition == getAdapter().getItemCount() - 1 - itemOfferset) {
      if (null != iLoadMoreListener && !isLoading && isScrollStop) {
        iLoadMoreListener.onLoadMore();
        isLoading = true;
      }
    }
  }

  /**
   * 拦截
   * @param e
   * @return
   */
  @Override
  public boolean onInterceptTouchEvent(MotionEvent e) {
    return true;
  }

  /**
   * 分发
   *
   * @param ev
   * @return
   */
  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    return super.dispatchTouchEvent(ev);
  }

  public void setiLoadMoreListener(
      ILoadMoreListener iLoadMoreListener) {
    this.iLoadMoreListener = iLoadMoreListener;
  }

  public interface ILoadMoreListener {

    public void onLoadMore();
  }


}
