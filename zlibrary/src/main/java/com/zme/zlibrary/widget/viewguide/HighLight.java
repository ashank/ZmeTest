package com.zme.zlibrary.widget.viewguide;

import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @FileName: HighLight
 * @author: Zhiyahan
 * @date: 2018/5/9 15:59
 * @Description: 高亮区域的属性
 */
public class HighLight {

    private static final String TAG = "HighLight";

    /**
     * 高亮区域的形状类型
     */
    public static final int CIRCLE = 4;
    public static final int RECTANGLE = 5;
    public static final int ROUND_RECTANGLE = 6;
    public static final int FULL_ROUND_RECTANGLE = 7;

    /**
     * 高亮区域的形状
     */
    private int shape = RECTANGLE;

    //圆角矩形的圆角半径
    private int roundRadius;

    //高亮区域半径
    private int radius;

    //高亮区域的Padding，padding越大，区域也越大
    private int padding = 10;

    //引导的视图
    private View mTargetView;

    //是否画线
    private boolean isShowStroke = true;
    //宽度
    private int mStrokeWidth;
    //线的颜色
    private int mStrokeColor;

    public HighLight(View mTargetView) {
        this.mTargetView = mTargetView;
        init();
    }

    public HighLight() {
        init();
    }

    /**
     * 初始化一些参数
     */
    private void init() {
        padding = 10;
        isShowStroke = true;
        roundRadius = 5;
        shape = RECTANGLE;
        mStrokeWidth=3;
        mStrokeColor=android.R.color.white;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int mShape) {
        shape = mShape;
    }

    public View getTargetView() {
        return mTargetView;
    }

    public void setTargetView(@NonNull View mTargetView) {
        this.mTargetView = mTargetView;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int mPadding) {
        padding = mPadding;
    }

    /**
     * 若区域形状为圆形。则可获取半径
     *
     * @return
     */
    public int getRadius() {
        if (mTargetView == null) {
            return 0;
        }
        radius = Math.max(mTargetView.getWidth() / 2, mTargetView.getHeight() / 2);

        return radius;
    }


    /**
     *半圆角矩形半径
     * @return
     */
    public int getFullRoundRadius() {
        if (mTargetView == null) {
            return 0;
        }
        radius = Math.min(mTargetView.getWidth() / 2, mTargetView.getHeight() / 2);

        return radius;
    }


    public int getRoundRadius() {
        return roundRadius;
    }

    public void setRoundRadius(int mRoundRadius) {
        roundRadius = mRoundRadius;
    }

    public boolean isShowStroke() {
        return isShowStroke;
    }

    public void setShowStroke(boolean mShowStroke) {
        isShowStroke = mShowStroke;
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
    }

    public int getStrokeColor() {
        return mStrokeColor;
    }

    public void setStrokeColor(@ColorRes int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }

    /**
     * 获取View在屏幕的位置
     * 需要activity view attach to window 之后才有效，高亮区域大小
     *
     * @return View的位置  see {@link RectF}
     */
    public RectF getViewRectF() {
        if (mTargetView == null) {
            return null;
        }
        RectF rectF = new RectF();
        int[] location = new int[2];
        mTargetView.getLocationOnScreen(location);
        rectF.left = location[0];
        rectF.top = location[1];
        rectF.right = location[0] + mTargetView.getWidth();
        rectF.bottom = location[1] + mTargetView.getHeight();
        return rectF;
    }

    /**
     * 获取View在屏幕的位置
     * 需要activity view attach to window 之后才有效，高亮区域大小
     *
     * @return View的位置  see {@link RectF}
     */
    public RectF getHasPaddingRectF() {
        if (mTargetView == null) {
            return null;
        }
        RectF rectF = new RectF();
        int[] location = new int[2];
        mTargetView.getLocationOnScreen(location);
        rectF.left = location[0] - padding;
        rectF.top = location[1] - padding;
        rectF.right = location[0] + mTargetView.getWidth() + padding;
        rectF.bottom = location[1] + mTargetView.getHeight() + padding;
        return rectF;
    }

}

