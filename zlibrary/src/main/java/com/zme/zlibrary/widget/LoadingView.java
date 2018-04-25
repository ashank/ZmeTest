package com.zme.zlibrary.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * CLASS ： LoadingView
 * Author : zhiyahan
 * TIME : 2017/9/26 13:30
 *
 * 加载的view
 * example:
 *
 * mLlView = (LoadingView) findViewById(R.id.llView);
 *mLlView.setBitmap(R.mipmap.ic_launcher);
 *mLlView.start();
 */

public class LoadingView extends View {

    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private RectF dstRectF;
    private RectF mRectF = new RectF();
    private float percent;
    private float mMoveWidth = 50;
    private Xfermode mXfermode;
    private PorterDuff.Mode mMode = PorterDuff.Mode.DST_IN;


    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mXfermode = new PorterDuffXfermode(mMode);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        int heithMode=MeasureSpec.getMode(heightMeasureSpec);
        int heithSize=MeasureSpec.getSize(heightMeasureSpec);

        int resultW=widthSize;
        int resultH=heithSize;

        if (widthMode==MeasureSpec.AT_MOST){
            resultW=mBitmap.getWidth();
        }
        if (heithMode==MeasureSpec.AT_MOST){
            resultH=mBitmap.getHeight();
        }
        setMeasuredDimension(resultW,resultH);*/

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.saveLayer(mRectF, mPaint, Canvas.ALL_SAVE_FLAG);

        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        /*canvas.drawBitmap(mBitmap,null,dstRectF,mPaint);
        mPaint.setXfermode(mXfermode);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));

        mRectF.set(percent*(getWidth()+getHeight())-getHeight(),0,percent*(getWidth()+getHeight())-getHeight()*mMoveWidth,getHeight());

        canvas.skew(0.5f,0f);
        canvas.drawRect(mRectF,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saveCount);*/

        mPaint.setTextSize(20.0f);
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        canvas.drawText("sdjskjd", 100, 100, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dstRectF = new RectF(0, 0, getWidth(), getHeight());
    }

    public void setBitmap(int drawableId) {
        mBitmap = BitmapFactory.decodeResource(getResources(), drawableId);
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                percent = (float) valueAnimator.getAnimatedValue();
            }
        });
        valueAnimator.start();
    }

    public void start() {
        startAnim();
    }
}
