package com.zme.zlibrary.widget.viewguide;;

import static com.zme.zlibrary.widget.viewguide.HighLightPage.BOTTOM;
import static com.zme.zlibrary.widget.viewguide.HighLightPage.LEFT;
import static com.zme.zlibrary.widget.viewguide.HighLightPage.RIGHT;
import static com.zme.zlibrary.widget.viewguide.HighLightPage.TOP;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;

/**
 * @FileName: ViewGuide
 * @author: Zhiyahan
 * @date: 2018/5/9 15:20
 * @Description: 首次使用APP，展示页面某个功能或者按钮引导功能类
 */
public class ViewGuide implements com.zme.zlibrary.widget.viewguide.IGuide {

    private ViewGroup mDecorView;
    private boolean isAlwayShow = false;
    //根布局
    private ViewGroup mRootView;
    private Context mContext;
    private com.zme.zlibrary.widget.viewguide.HighLight mHighLight;
    private com.zme.zlibrary.widget.viewguide.HighLightLayout mHighLightLayout;
    private com.zme.zlibrary.widget.viewguide.HighLightPage mHighLightPage;

    private View.OnClickListener mOnClickListener;

    private int screenWith, screenHeigh;

    private static ViewGuide mViewGuide;
    private LayoutInflater inflater;

    //引导图的宽度高度
    private int guideViewWidth, guideViewHeight;
    private String mStoreKey = "VIEW_GUIDE";//Shareprefrence存储的键值名；
    private boolean isRemoveView = false;
    private int count;
    private com.zme.zlibrary.widget.viewguide.HighLightPage.OnChildViewSizeListener mOnChildViewSizeListener;

    private ViewGuide(Context mContext) {
        this.mContext = mContext;
        mHighLightPage = new com.zme.zlibrary.widget.viewguide.HighLightPage();
        mHighLight = new com.zme.zlibrary.widget.viewguide.HighLight();
        mHighLightLayout = new com.zme.zlibrary.widget.viewguide.HighLightLayout(mContext);
        screenWith = mContext.getResources().getDisplayMetrics().widthPixels;
        screenHeigh = mContext.getResources().getDisplayMetrics().heightPixels;
        inflater = LayoutInflater.from(mContext);
    }

    public static ViewGuide with(Context mContext) {
        if (mViewGuide == null) {
            mViewGuide = new ViewGuide(mContext);
        }
        return mViewGuide;
    }

    /**
     * 背景色
     *
     * @param color 颜色资源
     * @return {@link ViewGuide}
     */
    public ViewGuide backGroundColor(@ColorRes int color) {
        mHighLightPage.setBackGroundColor(color);
        return this;
    }

    /**
     * @param isShow 是否显示边框，默认是显示的
     * @return {@link ViewGuide}
     */
    public ViewGuide isShowStroke(boolean isShow) {
        mHighLight.setShowStroke(isShow);
        return this;
    }

    /**
     * 配置键值名，每个页面需要不同的键值名来替代，以保证引导正常的显示
     *
     * @param mStoreKey 存储的键值名
     * @return {@link PageGuide}
     */
    public ViewGuide storeKey(@NonNull String mStoreKey) {
        this.mStoreKey = mStoreKey;
        return this;
    }

    /**
     * 虚线宽度
     *
     * @param strokeWidth 是否显示边框，默认是显示的
     * @return {@link ViewGuide}
     */
    public ViewGuide strokeWidth(int strokeWidth) {
        mHighLight.setStrokeWidth(strokeWidth);
        return this;
    }

    /**
     * 虚线颜色
     *
     * @param strokeColor 显示边框颜色
     * @return {@link ViewGuide}
     */
    public ViewGuide strokeColor(@ColorRes int strokeColor) {
        mHighLight.setStrokeColor(strokeColor);
        return this;
    }


    /**
     * 是否显示
     *
     * @param isAlwayShow 是否永远显示
     * @return {@link ViewGuide}
     */
    public ViewGuide isAlwayShow(boolean isAlwayShow) {
        this.isAlwayShow = isAlwayShow;
        return this;
    }

    /**
     * 设置你要显示引导功能的View
     *
     * @param mTargetView 目标视图
     * @return {@link ViewGuide}
     */
    public ViewGuide targetView(View mTargetView) {
        mHighLight.setTargetView(mTargetView);
        return this;
    }


    /**
     * 设置你要提示目标视图的引导布局和点击的视图
     *
     * @param guideLayoutView 提示的布局视图
     * @param clickView       点击的View
     * @return {@link ViewGuide}
     * <p>
     * LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
     * View view=inflater.inflate(R.layout.guide_layout1, null);
     * ViewGuide.with(MainActivity.this).guideLayout();
     */
    public ViewGuide guideLayoutView(View guideLayoutView, View clickView) {
        mHighLightPage.setLayoutView(guideLayoutView);
        mHighLightPage.setClickView(clickView);
        return this;
    }


    /**
     * 设置你要提示目标视图的引导布局
     *
     * @param layoutRes 提示的布局视图layout资源文件
     * @return {@link ViewGuide}
     */
    public ViewGuide guideLayout(@LayoutRes  int layoutRes, @IdRes int clickViewID) {
        View view = inflater.inflate(layoutRes, null);
        View clickView = view.findViewById(clickViewID);
        return guideLayoutView(view, clickView);
    }


    /**
     * 为指定的视图设置点击事件，处理交互
     *
     * @param mOnClickListener 设置点击事件
     * @return {@link ViewGuide}
     */
    public ViewGuide clickListener(View.OnClickListener mOnClickListener) {
        mHighLightPage.setOnclickListener(mOnClickListener);
        return this;
    }


    /**
     * 高亮部分的内间距
     *
     * @param padding
     * @return {@link ViewGuide}
     */
    public ViewGuide highLightPadding(int padding) {
        mHighLight.setPadding(padding);
        return this;
    }

    /**
     * 高亮区域的形状
     *
     * @param shape see <code>HighLight.CIRCLE</code>.
     * @return {@link ViewGuide}
     */
    public ViewGuide highLightShape(int shape) {
        mHighLight.setShape(shape);
        return this;
    }


    /**
     * 图片引导区域与高亮区域的距离
     *
     * @param margin
     * @return {@link ViewGuide}
     */
    public ViewGuide guideImageViewMargin(int margin) {
        mHighLightPage.setMargin(margin);
        return this;
    }


    /**
     * 图片引导区域与高亮区域的相对位置
     *
     * @param location
     * @return {@link ViewGuide}
     */
    public ViewGuide location(int location) {
        mHighLightPage.setLocation(location);
        return this;
    }


    /**
     * 圆角矩形的半径
     *
     * @param radius
     * @return {@link ViewGuide}
     */
    public ViewGuide roundRadis(int radius) {
        mHighLight.setRoundRadius(radius);
        return this;
    }


    /**
     * @param mOnChildViewSizeListener view的大小适配
     * @return {@link ViewGuide}
     */
    public ViewGuide onChildViewSizeListener(com.zme.zlibrary.widget.viewguide.HighLightPage.OnChildViewSizeListener mOnChildViewSizeListener) {
        this.mOnChildViewSizeListener = mOnChildViewSizeListener;
        return this;
    }


    /**
     * 显示浮层,首次使用显示，显示过之后，不再显示
     */
    @Override
    public void showView() {
        if (mHighLightPage.getLayoutView()==null){
            throw new NullPointerException("LayoutView is null，Cannot add a null child view to a ViewGroup");
        }
        boolean isFirst = isFirstLoad();
        if (!isFirst) {
            return;
        }
        /*if (mHighLightPage.getLayoutView()==null){
            throw new NullPointerException("layoutView is null, please set up layout view");
        }*/
        mHighLightPage.setHighLight(mHighLight);
        mDecorView = (ViewGroup) ((Activity) mContext).getWindow().getDecorView();
        mDecorView.post(new Runnable() {
            @Override
            public void run() {
                removeView();
                mHighLightLayout.removeAllViews();
                mHighLightLayout.setHighLightPage(mHighLightPage);
                addView(mHighLightPage, mHighLightLayout);
                mDecorView.addView(mHighLightLayout);
            }
        });
    }


    private void addView(final com.zme.zlibrary.widget.viewguide.HighLightPage mHighLightPage, final com.zme.zlibrary.widget.viewguide.HighLightLayout mHighLightLayout) {
        RectF mRectF = mHighLightPage.getHighLight().getViewRectF();
        final View view = mHighLightPage.getLayoutView();
        final RectF targetViewRectF = mHighLightPage.getHighLight().getHasPaddingRectF();

        final int margin=mHighLightPage.getMargin();

        mHighLightLayout.addView(view);
        //动画
        Animation animator = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
        LayoutAnimationController controller = new LayoutAnimationController(animator);
        mHighLightLayout.setLayoutAnimation(controller);
        mHighLightLayout.startLayoutAnimation();

        if (targetViewRectF == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                if (guideViewWidth == 0) {
                    guideViewWidth = view.getWidth();
                }
                if (guideViewHeight == 0) {
                    guideViewHeight = view.getHeight();
                }
                RelativeLayout.LayoutParams params = null;
                switch (mHighLightPage.getLocation()) {
                    case TOP:
                        //Left:(int) sd.centerX()-whith1/2
                        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) targetViewRectF.top - guideViewHeight-margin, 0, 0);
                        break;
                    case BOTTOM:
                        //Left:(int) sd.centerX()-whith1/2
                        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) targetViewRectF.bottom+margin, 0, 0);
                        break;
                    case LEFT:
                        //top:(int) sd.centerY()-guideViewHeight/2
                        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.setMargins((int) targetViewRectF.left - guideViewWidth-margin, 0, 0, 0);
                        break;
                    case RIGHT:
                        //(int) sd.centerY()-guideViewHeight/2
                        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.setMargins((int) targetViewRectF.right+margin, 0, 0, 0);
                        break;
                }
                if (mOnChildViewSizeListener != null) {
                    mOnChildViewSizeListener.onModifySize(view, targetViewRectF);
                }
                view.setLayoutParams(params);
            }
        });
    }

    private void removeView() {
        int mDecorViewChildCount = mDecorView.getChildCount();
        for (int i = 0; i < mDecorViewChildCount; i++) {
            View view = mDecorView.getChildAt(i);
            if (view instanceof com.zme.zlibrary.widget.viewguide.HighLightLayout) {
                mDecorView.removeView(view);
            }
        }
    }

    /**
     * 移除所有的引导视图
     *
     * @param isActivityFinish 如果是Activity销毁时需要销毁的视图，则不会执行SharedPreferences 修改行为；只有用户点击引导视图才会修改
     */
    public void remove(boolean isActivityFinish) {
        count++;
        if (count > 1) {
            isRemoveView = true;
        }
        if (isRemoveView) {
            count=0;
            return;
        }
        mHighLightLayout.removeAllViews();
        mHighLightLayout.removeCallbacks(null);
        mHighLightLayout.clearAnimation();
        removeView();
        mDecorView = null;
        mHighLightPage = null;
        mViewGuide = null;
        //如果每次都需要显示，不需要存储
        if (isAlwayShow) {
            isAlwayShow = false;
            mContext=null;
            return;
        }
        if (!isActivityFinish) {
            notFistLoad();
            mContext=null;
        }
    }

    /*private int xp2dp(int px){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, mContext.getResources().getDisplayMetrics());
    }*/

    private boolean notFistLoad() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putBoolean(mStoreKey, true).commit();
    }

    @Override
    public boolean isFirstLoad() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(mStoreKey, true);
    }
}
