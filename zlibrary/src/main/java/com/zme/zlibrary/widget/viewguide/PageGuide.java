package com.zme.zlibrary.widget.viewguide;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.util.List;

/**
 * @FileName: PageGuid
 * @author: Zhiyahan
 * @date: 2018/5/8 14:39
 * @Description: 首次使用APP，展示页面引导功能类
 *
 *<p>PageGuide.with(MainActivity.this)
    .backGroundColor(R.color.coloor)
    .imageList(drawableList)
    .addView();
 * <p/>
 *
 */
public class PageGuide implements IGuide, View.OnClickListener{

    private Context mContext;
    //引导页图片集合
    private List<Drawable> mImageList;
    //背景色
    private int mBackGroundColor;
    //已经显示的图片数量
    private int hasShowImageCount;
    //根布局
    private  ViewGroup mRootView;
    //图片View容器
    private  FrameLayout frameLayout;
    //是否全屏
    private boolean fullScreen=false;
    //图片显示类型
    private ImageView.ScaleType mScaleType;

    private boolean isAlwayShow = false;

    private String mStoreKey="PAGE_GUIDE";//Shareprefrence存储的键值名；
    private boolean isRemoveView=false;

    private PageGuide(Context mContext) {
        this.mContext = mContext;
        //默认透明
        mBackGroundColor=mContext.getResources().getColor(android.R.color.transparent);
    }

    public static PageGuide with(Context mContext){
        return new PageGuide(mContext);
    }

    /**
     * 背景色
     * @param color 颜色资源
     * @return  {@link PageGuide}
     */
    public PageGuide backGroundColor(int color){
        this.mBackGroundColor=color;
        return this;
    }

    /**
     * 配置键值名，每个页面需要不同的键值名来替代，以保证引导正常的显示
     * @param mStoreKey 存储的键值名
     * @return  {@link PageGuide}
     */
    public PageGuide storeKey(@NonNull String mStoreKey){
        this.mStoreKey=mStoreKey;
        return this;
    }


    /**
     * 是否显示
     *
     * @param isAlwayShow 是否永远显示
     * @return {@link PageGuide}
     */
    public PageGuide isAlwayShow(boolean isAlwayShow) {
        this.isAlwayShow = isAlwayShow;
        return this;
    }

    /**
     * 全屏判断
     * @param fullScreen 是否全屏
     * @return {@link PageGuide}
     */
    public PageGuide fullScreen(boolean fullScreen){
        this.fullScreen=fullScreen;
        return this;
    }

    /**
     * 设置引导层的图片列表
     * @param mImageList {@link Drawable} 数组
     * @return {@link PageGuide}
     */
    public PageGuide imageList(List<Drawable> mImageList){
        this.mImageList=mImageList;
        return this;
    };

    /**
     * 图片显示的缩放类型
     * @param mScaleType  see {@link ImageView} and {@link android.widget.ImageView.ScaleType}
     * @return {@link PageGuide}
     */
    public PageGuide scaleType(ImageView.ScaleType mScaleType){
        this.mScaleType=mScaleType;
        return this;
    };

    /**
     * 1、添加引导层到页面中
     * 2、利用 DecorView 来添加引导浮层
     */
    private void addView(){
        boolean isFisrt=isFirstLoad();
        if (!isFisrt){
            return;
        }
        if (mContext instanceof Activity){
            if (mImageList != null && mImageList.size() > 0) {
                mRootView = (ViewGroup) ((Activity) mContext).getWindow().getDecorView();
                //添加帧布局
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                lp.topMargin = getStatusBarHeight(mContext.getResources());
                frameLayout = new FrameLayout(mContext);
                frameLayout.setOnClickListener(null);
                frameLayout.setBackgroundColor(mContext.getResources().getColor(mBackGroundColor));
                //添加图片
                final ImageView ivFloatGuide = new ImageView(mContext);
                ivFloatGuide.setImageDrawable(mImageList.get(0));
                if (mScaleType!=null){
                    ivFloatGuide.setScaleType(mScaleType);
                }
                FrameLayout.LayoutParams imageLp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
                int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        25, mContext.getResources().getDisplayMetrics());
                imageLp.leftMargin = margin;
                imageLp.rightMargin = margin;
                imageLp.gravity = Gravity.CENTER;
                frameLayout.addView(ivFloatGuide, imageLp);
                ivFloatGuide.setOnClickListener(this);
                hasShowImageCount++;
                //添加帧布局
                mRootView.addView(frameLayout, lp);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (mImageList!=null&&mImageList.size()>hasShowImageCount){
            ((ImageView)v).setImageDrawable(mImageList.get(hasShowImageCount));
        }
        hasShowImageCount++;
        if (mImageList!=null&&hasShowImageCount>mImageList.size()){
            ((ImageView)v).removeCallbacks(null);
            removeView(false);
            isRemoveView=true;
        }
    }


    /**
     * 移除所有View
     * @param isActivityFinish Activity是否是销毁状态，如果是销毁状态
     */
    public void removeView(boolean isActivityFinish){
        if (!isRemoveView){
            frameLayout.removeAllViews();
            mRootView.removeView(frameLayout);
            frameLayout=null;
            mRootView=null;
            hasShowImageCount=0;
            mImageList.clear();
            mImageList=null;
        }

        //如果每次都需要显示，不需要存储
        if (isAlwayShow) {
            isAlwayShow = false;
            mContext=null;
            return;
        }

        if (!isActivityFinish){
            notFistLoad();
            mContext=null;
        }
    }




    /**
     * 状态栏的高度
     * @param resources
     * @return 返回状态栏的高度
     */
    private int getStatusBarHeight(Resources resources) {
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    @Override
    public void showView() {
        addView();
    }


    private boolean notFistLoad(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putBoolean(mStoreKey, false).commit();
    }

    @Override
    public boolean isFirstLoad() {
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(mStoreKey, true);
    }

}
