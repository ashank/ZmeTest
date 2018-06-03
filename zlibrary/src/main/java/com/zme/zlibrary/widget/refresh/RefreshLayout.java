package com.zme.zlibrary.widget.refresh;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.getSize;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.zme.zlibrary.utils.LogUtils;
import com.zme.zlibrary.widget.refresh.listener.IFooter;
import com.zme.zlibrary.widget.refresh.listener.IHeader;
import com.zme.zlibrary.widget.refresh.listener.IRefresh;
import com.zme.zlibrary.widget.refresh.listener.OnRefreshListener;

/**
 * Author：ZME
 * Create Time ：2018/6/2 21:15
 * Modify Time：2018/6/2 21:15
 * Version：1.0
 * Description ：<p>
 *     1、阻尼系数
 *     2、是否开启刷新，如果不开启，1⃣、以纯滚动的形式存在，2，关闭纯滚动
 *     3、自动刷新
 *     4、判断滑动距离
 *     5、判断是下拉还是上拉
 *     6、动画移动的动画
 *     7、触发的高度比例/或者高度
 *     8、在刷新中，保持显示的高度/或者比例
 *     9、具有滑动属性的View的滚动判断
 *     10、多个手指滑动
 *     11、二级滑动的判断
 *
 * </p>
 */
public class RefreshLayout extends ViewGroup implements IRefresh{



    private AbstractHeader mHeaderView;
    private AbstractFooter mFooterView;
    private IHeader mIHeader;
    private IFooter mIFooter;
    //刷新监听
    private OnRefreshListener onRefreshListener;
    //滚动
    private ScrollerAnimationHelper mScrollerAnimationHelper;

    //<editor-fold desc="高度属性变量 ">

    //布局高度和宽度
    private int layoutWidth;
    private int layoutHeight;

    private int mScreenHeight;
    /**
     * 头部视图的真实高度
     */
    private int mHeaderHeight;
    /**
     * 底部视图的真实高度
     */
    private int mFooterHeight;

    /**
     * 头部触发刷新的高度
     */
    private int mHeaderTriggerHeight;

    /**
     * 触发的高度和中高度的比例
     */
    private float mHeaderTriggerHeightRate=1.0f;

    /**
     * 头部在刷新中显示的高度
     */
    private int mHeaderShowHeight;

    /**
     * 显示的头部高度和真实头部总高度的比例
     */
    private float mHeaderShowHeightRate=1.0f;


    /**
     * 底部触发加载的高度
     */
    private int mFooterTriggerHeight;
    /**
     * 底部在加载中显示的高度
     */
    private int mFooterShowHeight;

    /**
     * 触发的高度和中高度的比例
     */
    private float mFooterTriggerHeightRate=1.0f;

    /**
     * 显示的底部高度和真实底部总高度的比例
     */
    private float mFooterShowHeightRate=1.0f;

    //</editor-fold>

    //<editor-fold desc="滑动属性变量 ">
    /**
     * 滑动对象
     */
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
   /**
     * 最小移动距离
     */
    private int mTouchSlop;

    private  int mCurrentVelocity;
    private int mMaximumVelocity;


    /**
     * 按下屏幕的坐标
     */
    private float mXDown;
    private float mYDown;
    /**
     * 当前ACTION_MOVE事件时的屏幕坐标
     */
    private float mXMove;
    private float mYMove;
    /**
     * 记录手指上次按下的屏幕坐标
     */
    private float mXLastMove;
    private float mYLastMove;

    //</editor-fold>

    /**
     * 界面可滚动的顶部边界
     */
    private int mTopBorder;

    /**
     * 界面可滚动的底部边界
     */
    private int mBottomBorder;

    private final int mBorderOffset=0;

    //阻尼系数
    private float damp = 0.3f;

    /**
     *   拉动的状态
     */
    private PullStatus status = PullStatus.DEFAULT;

    int heightSpec;

    private boolean isResfreshing=false;


    public RefreshLayout(Context context) {
        this(context,null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mVelocityTracker = VelocityTracker.obtain();
        mScreenHeight=context.getResources().getDisplayMetrics().heightPixels;

        if (mHeaderView==null){
            mHeaderView=new HeaderView(context);
        }

        mScrollerAnimationHelper=new ScrollerAnimationHelper();
    }

    public void setHeader(AbstractHeader mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public void setFooter(AbstractFooter mFooterView) {
        this.mFooterView = mFooterView;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        layoutHeight = h;
        layoutWidth = w;
        Log.e("TAG", "onSizeChanged"+layoutHeight+layoutWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            LayoutParams lp = child.getLayoutParams();
            if (lp.height > 0) {
                heightSpec = makeMeasureSpec(lp.height, EXACTLY);
                measureChild(child, widthMeasureSpec, heightSpec);
            }else if (lp.height == WRAP_CONTENT){
                heightSpec = makeMeasureSpec(Math.max(getSize(heightMeasureSpec), 0), AT_MOST);
                measureChild(child, widthMeasureSpec, heightSpec);
            }else if (lp.height == MATCH_PARENT) {
                heightSpec = makeMeasureSpec(Math.max(heightMeasureSpec, 0), EXACTLY);
                measureChild(child, widthMeasureSpec, heightSpec);
            } else {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            if (child instanceof IHeader){
                mHeaderView=(HeaderView)child;
                //获得高度
                mHeaderHeight=child.getMeasuredHeight();
                mHeaderTriggerHeight=(int)(mHeaderHeight*mHeaderTriggerHeightRate);
                mHeaderShowHeight=(int)(mHeaderHeight*mHeaderShowHeightRate);
                //头部布局
                child.layout(0, 0-mHeaderHeight, child.getMeasuredWidth(),  0);
                mTopBorder = child.getTop()-mBorderOffset;
            } else if (child instanceof IFooter){
                mFooterView=(FooterView)child;
                //获得高度
                mFooterHeight=child.getMeasuredHeight();
                mFooterTriggerHeight=(int)(mFooterHeight*mFooterTriggerHeightRate);
                mFooterShowHeight=(int)(mFooterHeight*mFooterShowHeightRate);
                child.layout(0, mScreenHeight, child.getMeasuredWidth(), mScreenHeight + mFooterHeight);
                mBottomBorder = child.getBottom()+mBorderOffset;
            }else {
                child.layout(0, 0, child.getMeasuredWidth(),  child.getMeasuredHeight());
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final int count = super.getChildCount();
        if (count > 3) {
            throw new RuntimeException("最多只支持3个子View，Most only support three sub view");
        }
    }

    /**
     * 拦截事件
     * @param event {@link MotionEvent}
     * @return 返回true 拦截事件，返回fasle 不拦截事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = event.getX();
                mYDown=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getX();
                mYMove=event.getY();
                //TODO 计算移动的x坐标和按下时的坐标差，如果大于touchSlop的距离，则允许滑动
                float dy = Math.abs(mYMove - mYDown);
                float dx =  Math.abs(mXMove - mYDown);
                if (dy > mTouchSlop && dy > dx) {
                    //TODO 返回ture 则消费这个事件
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }



    /**
     * 处理事件
     * @param event {@link MotionEvent}
     * @return 是否处理了该事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //触摸重置
                mCurrentVelocity = 0;
                mVelocityTracker.addMovement(event);
                mScroller.forceFinished(true);

                mXDown = event.getX();
                mYDown=event.getY();
                //TODO 保存最后按下的x坐标
                mXLastMove = mXDown;
                mYLastMove=mYDown;
                return true;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);//速度追踪
                mXMove = event.getX();
                mYMove=event.getY();
                //TODO 计算移动的距离,右滑为正数，左滑为负数
                int dy = (int) (mYMove-mYLastMove);
                if (getScrollY()<0){
                    pullDownMoveAction(event,dy);
                }else {
                    pullUpMoveAction(event,dy);
                }
                //记录最后的一个坐标
                mXLastMove = mXMove;
                mYLastMove = mYMove;
                break;
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.clear();
                break;
            case MotionEvent.ACTION_UP:
                mXLastMove = mXMove;
                mYLastMove = mYMove;
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                mCurrentVelocity = (int) mVelocityTracker.getYVelocity();

                //处理惯性滑动
                if (status==PullStatus.REFRESH_TRIGGER){
                   /* autoScrollOnRefreshTrigger();*/
                    int targetIndeY = (getScrollY() + getHeight() / 2) / getHeight();
                    int dy1 = targetIndeY * getHeight() - getScrollY()-mHeaderShowHeight;
                    // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                    mScroller.startScroll(0, getScrollY(), 0, dy1,500);
                    invalidate();
                    updateStatus(PullStatus.REFRESHING);

                    if (onRefreshListener!=null){
                        onRefreshListener.onRefresh();
                    }
                }else if (status==PullStatus.REFRESHING){
                    //滑动多少，都要恢复到自动的显示高度
                    if (getScrollY()<0) {
                        autoScrollOnRefreshTrigger();
                    }
                }else {
                    int targetIndeY = (getScrollY() + getHeight() / 2) / getHeight();
                    LogUtils.e(">>>>UP=="+getHeight()+"=="+getScrollY());
                    int dy1 = targetIndeY * getHeight() - getScrollY();
                    // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                    mScroller.startScroll(0, getScrollY(), 0, dy1);
                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }


    /**
     * 下拉时移动的动作
     *
     * @param event  MotionEvent
     * @param offsetY  y轴上的移动偏移量
     */
    private void pullDownMoveAction(MotionEvent event,int offsetY){
        scrollBy(0, (int)(-offsetY*damp));
        //滑动的距离需要大于触发的高度
        if ( Math.abs(getScrollY())-Math.abs(mHeaderTriggerHeight)>0&&!isResfreshing) {
            //达到header高度了
            updateStatus(PullStatus.REFRESH_TRIGGER);
        }else {
            //未达到
            updateStatus(PullStatus.REFRESH_NOT_TRIGGER);
        }
    }

    /**
     * 上拉时移动的动作
     *
     * @param event  MotionEvent
     * @param offsetY  y轴上的移动偏移量
     */
    private void pullUpMoveAction(MotionEvent event,int offsetY){
        scrollBy(0, (int)(-offsetY*damp));
        if (Math.abs(getScrollY())-Math.abs(mFooterTriggerHeight)>0) {
            //达到footer高度了
            /* scrollTo(0, mBottomBorder);*/
            Log.e("TAG", "pullUpMoveAction: dadao gaodule " );
        }else {
            //未达到
            /*scrollBy(0, (int)(-dy*damp));*/
        }

    }


    //刷新状态
    private void updateStatus(PullStatus status) {
        this.status = status;
        int scrollY = getScrollY();
        if (isResfreshing && status != PullStatus.DEFAULT) {
            //正在刷新的状态，不允许更新状态
            return;
        }
        // 判断本次触摸系列事件结束时,Layout的状态
        switch (status) {
            //默认状态
            case DEFAULT:
                isResfreshing=false;
                break;
            //下拉刷新
            case REFRESH_NOT_TRIGGER:
                mHeaderView.onRefreshNotTrigger(scrollY);
                break;
            case REFRESH_TRIGGER:
                mHeaderView.onRefreshTrigger(scrollY);
                break;
            case REFRESH_SCROLLING:
                mHeaderView.onRefreshingScrolling(scrollY);
                break;
            case REFRESHING:
                mHeaderView.onRefreshing(scrollY);
                isResfreshing=true;
                break;
            case REFRESH_FINISH:
                mHeaderView.onRefreshFinish(scrollY, true);
            break;
        }
    }

    /**
     * 达到刷新高度后放开的自动回滚处理
     */
    private void autoScrollOnRefreshTrigger() {
        int start = getScrollY();
        int end = -mHeaderShowHeight;
        mScrollerAnimationHelper.setStartDelay(0);
        updateStatus(PullStatus.REFRESHING);
        mScrollerAnimationHelper.autoScrollByAnim(start, end, new
                AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                updateStatus(PullStatus.REFRESH_SCROLLING);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                updateStatus(PullStatus.REFRESHING);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    /**
     *  刷新完成后，需要自动回滚到隐藏头部视图为止
     */
    private void autoScrollOnRefreshFinish() {
        isResfreshing=false;
        updateStatus(PullStatus.REFRESH_FINISH);
        int start = -mHeaderShowHeight;
        int end = 0;
        mScrollerAnimationHelper.setStartDelay(2000);
        mScrollerAnimationHelper.autoScrollByAnim(start, end, new
                AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        updateStatus(PullStatus.DEFAULT);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        updateStatus(PullStatus.DEFAULT);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
    }


    /**
     *  自动刷新
     */
    private void autoRefresh() {
        int start =0;
        int end =  -mHeaderShowHeight;
        mScrollerAnimationHelper.setStartDelay(0);
        mScrollerAnimationHelper.autoScrollByAnim(start, end, new
                AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        updateStatus(PullStatus.REFRESH_NOT_TRIGGER);
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        updateStatus(PullStatus.REFRESHING);
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        updateStatus(PullStatus.DEFAULT);
                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
    }


    @Override
    public void onRefreshFinish() {
        autoScrollOnRefreshFinish();
    }

    @Override
    public void onAutoRefresh() {
        int start =0;
        int end =  -mHeaderShowHeight;
        int dy=getScrollY()+mHeaderShowHeight;
        mScroller.startScroll(0, mHeaderShowHeight, 0, dy);
        invalidate();
    }

    @Override
    public void startRefresh() {

    }

    @Override
    public void setEnableRefresh() {

    }

    /**
     * 滚动动画帮助类
     */
    private  class ScrollerAnimationHelper {

        private Interpolator interpolator;
        private AnimatorListener animatorListener;
        private ValueAnimator animator;
        private int startDelay=2000;
        private int duration=500;
        private ScrollerAnimationHelper() {
            interpolator=new ViscousFluidInterpolator();
        }

        public void setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
        }

        public void setStartDelay(int startDelay) {
            this.startDelay = startDelay;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        private void autoScrollByAnim(int start,int end,AnimatorListener
                animatorListener){
            if (animator!=null){
                animator.cancel();
            }
            animator = ValueAnimator.ofInt(start, end);
            animator.setDuration(duration);
            animator.setInterpolator(interpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    scrollTo(0, value);
                    postInvalidate();
                }
            });
            animator.addListener(animatorListener);
            animator.setStartDelay(startDelay);
            animator.start();
        }
    }
}
