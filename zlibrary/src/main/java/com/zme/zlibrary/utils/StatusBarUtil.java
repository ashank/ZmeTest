package com.zme.zlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.zme.zlibrary.R;

/**
 * Description ：实现全屏模式，沉浸式状态栏
 * Author：ZME
 * Create Time ：2018/5/5 14:25
 * Modify Time：2018/5/5 14:25
 * Version：1.0
 */
public class StatusBarUtil {


  /**
   * 全屏模式
   * @param context
   */
  public static void translucentBar(Context context,boolean isSetNavigationColor) {
    Window window = ((Activity) context).getWindow();
    int color = context.getResources().getColor(android.R.color.transparent);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //设置透明状态栏,这样才能让 ContentView 向上，和向下延伸到navigation
      if (isSetNavigationColor){
        //这里要用clearFlags，如果用addFlags 会出现半透明背景
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
          | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
      }else {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      }

      //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      //设置颜色
      window.setStatusBarColor(color);
      if (isSetNavigationColor){
        //设置导航栏颜色
        window.setNavigationBarColor(color);
      }

      ViewGroup contentView = ((ViewGroup) ((Activity) context).findViewById(android.R.id.content));
      View childAt = contentView.getChildAt(0);
      if (childAt != null) {
        //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其为系统 View 预留空间.
        ViewCompat.setFitsSystemWindows(childAt, true);
      }

    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      //透明状态栏
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      //透明导航栏
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

      //设置mContentView为fitsSystemWindows
      ViewGroup mContentView = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
      View statusBarView = mContentView.getChildAt(0);

      //移除假的 View
      if (statusBarView != null && statusBarView.getLayoutParams() != null &&
          statusBarView.getLayoutParams().height == getStatusBarHeight(context)) {
        mContentView.removeView(statusBarView);
      }
      //不预留空间
      if (mContentView.getChildAt(0) != null) {
        ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), true);
      }

    }
  }


  /**
   * 着色模式
   * @param context
   */
  public static void statebarColor(Context context,int colorRes,boolean isSetNavigationColor) {
    Window window = ((Activity) context).getWindow();
    int color = context.getResources().getColor(colorRes);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //设置透明状态栏,这样才能不让 ContentView 向上，和向下延伸到navigation
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
          | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
      //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      //设置状态栏颜色
      window.setStatusBarColor(color);

      if (isSetNavigationColor){
        //设置导航栏颜色
        window.setNavigationBarColor(color);
      }
      //为第一个布局设置setFitsSystemWindows
      ViewGroup contentView = ((ViewGroup) ((Activity) context).findViewById(android.R.id.content));
      View childAt = contentView.getChildAt(0);
      if (childAt != null) {
        //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View .为系统 View 预留空间.
        ViewCompat.setFitsSystemWindows(childAt, false);
      }

    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      //透明状态栏
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      //透明导航栏
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

      int statusBarHeight = getStatusBarHeight(context);
      int statusColor = context.getResources().getColor(R.color.colorPrimary);
      //设置mContentView为fitsSystemWindows
      ViewGroup mContentView = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
      View statusBarView = mContentView.getChildAt(0);
      if (statusBarView != null && statusBarView.getLayoutParams() != null &&
          statusBarView.getLayoutParams().height == statusBarHeight) {
        //避免重复添加 View
        statusBarView.setBackgroundColor(statusColor);
        return;
      }
      //预留空间
      if (statusBarView != null) {
        ViewCompat.setFitsSystemWindows(statusBarView, true);
      }

      //添加假 View
      statusBarView = new View(context);
      ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
          statusBarHeight);
      statusBarView.setBackgroundColor(statusColor);
      mContentView.addView(statusBarView, 0, lp);

    }
  }

  /**
   * 获取状态栏的高度
   * @param context
   * @return 状态栏的高度
   */
  public static int getStatusBarHeight(Context context) {
    int result = 0;
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
        "android");
    if (resourceId > 0) {
      result = context.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }


  /**
   * 获取导航栏高度
   *
   * @param context context
   * @return 导航栏高度
   */
  public static int getNavigationBarHeight(Context context) {
    int resourceId = context.getResources()
        .getIdentifier("navigation_bar_height", "dimen", "android");
    return context.getResources().getDimensionPixelSize(resourceId);
  }

  /**
   *  显示导航和状态栏
   * @param context
   */
  public static void showSystemUI(Context context) {
    ((Activity)context).getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
  }

  /**
   * 全屏
   * @param context
   */
  public static void hideSystemUI(Context context) {
    ((Activity)context).getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
  }

}
