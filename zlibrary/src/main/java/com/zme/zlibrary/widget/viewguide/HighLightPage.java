package com.zme.zlibrary.widget.viewguide;

import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.view.View;

/**
 * @FileName: HighLightPage
 * @author: Zhiyahan
 * @date: 2018/5/10 14:08
 * @Description: 高亮页面
 */
public class HighLightPage {

    private com.zme.zlibrary.widget.viewguide.HighLight mHighLight;

    /**
     * 相对高亮的方位
     */
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;

    /**
     * 图片在目标View的相对位置
     * 有上下左右四种，默认下面
     */
    private int mLocation = BOTTOM;
    //背景色
    private int mBackGroundColor;

    //点击的View的ID
    private View clickView;

    //这里的Margin指的高亮区域和引导视图之间的距离，负值代表接近和高亮视图有重叠
    private int margin;

    private View layoutView;



    public HighLightPage() {
        mBackGroundColor= android.R.color.black;

    }

    public HighLight getHighLight() {
        return mHighLight;
    }

    public void setHighLight(HighLight mHighLight) {
        this.mHighLight = mHighLight;
    }

    public int getLocation() {
        return mLocation;
    }

    /**
     * 设置View的相对位置
     *
     * @param mLocation
     */
    public void setLocation(int mLocation) {
        this.mLocation = mLocation;
    }

    public int getBackGroundColor() {
        return mBackGroundColor;
    }

    public void setBackGroundColor(@ColorRes int color) {
        this.mBackGroundColor = color;
    }

    public View getLayoutView() {
        return layoutView;
    }

    public void setLayoutView(View mLayoutView) {
        layoutView = mLayoutView;
    }

    public View getClickView() {
        return clickView;
    }

    public void setClickView(View mClickView) {
        clickView = mClickView;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int mMargin) {
        margin = mMargin;
    }

    public void setOnclickListener(View.OnClickListener mOnclickListener) {
        if (mOnclickListener == null) {
            return;
        }

        if (clickView == null) {
            return;
        }
        clickView.setOnClickListener(mOnclickListener);
    };


    public interface OnChildViewSizeListener{
        void onModifySize(View view,RectF targetViewRectF);
    }
}
