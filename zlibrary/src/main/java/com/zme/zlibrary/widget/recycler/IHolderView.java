package com.zme.zlibrary.widget.recycler;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

/**
 * Description ：VH 的View的操作对象
 * Author：ZME
 * Create Time ：2018/4/26 20:55
 * Modify Time：2018/4/26 20:55
 * Version：1.0
 */
public interface IHolderView<VH> {

  VH setText(String string);

  VH setImageResource(int viewId, int resId);

  VH setImageBitmap(int viewId, Bitmap bitmap);

  VH setImageDrawable(int viewId, Drawable drawable);

  VH setBackgroundColor(int viewId, int color);

  VH setBackgroundRes(int viewId, int backgroundRes);

  VH setTextColor(int viewId, int textColor);

  VH setTextColorRes(int viewId, int textColorRes);

  @SuppressLint("NewApi")
  VH setAlpha(int viewId, float value);

  VH setVisible(int viewId, boolean visible);

  VH linkify(int viewId);

  VH setTypeface(Typeface typeface, int... viewIds);

  VH setProgress(int viewId, int progress);

  VH setProgress(int viewId, int progress, int max);

  VH setMax(int viewId, int max);

  VH setRating(int viewId, float rating);

  VH setRating(int viewId, float rating, int max);

  VH setTag(int viewId, Object tag);

  VH setTag(int viewId, int key, Object tag);

  VH setChecked(int viewId, boolean checked);

}
