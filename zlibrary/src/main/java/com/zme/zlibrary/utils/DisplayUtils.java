package com.zme.zlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 作者：zhiyahan on 2018/3/10 00:59
 * 与显示相关的工具类
 */
public class DisplayUtils {

    private static DisplayMetrics dm = new DisplayMetrics();

    /**
     * 将px转换为与之相等的dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale =  context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将dp转换为与之相等的px
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 将px转换为sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

//    /**
//     * convert dp to its equivalent px
//     */
//    protected int dp2px(int dp){
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,getResources().getDisplayMetrics());
//    }
//
//    /**
//     * convert sp to its equivalent px
//     */
//    protected int sp2px(int sp){
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,getResources().getDisplayMetrics());
//    }

    /**
     * 获取屏幕宽度
     */
    public static int getWidthPixels(Activity activity) {
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    /**
     * 获取屏幕高度
     */
    public static int getHeightPixels(Activity activity) {
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

}
