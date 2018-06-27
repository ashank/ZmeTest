package com.zme.zlibrary.widget.viewguide;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

;



/**
 * @FileName: HighLightLayout
 * @author: Zhiyahan
 * @date: 2018/5/9 15:46
 * @Description: 高亮引导视图
 */
public class HighLightLayout extends RelativeLayout {

    private Paint mPaint;
    private Paint mLinePaint;
    private int backGroundColor;

    private com.zme.zlibrary.widget.viewguide.HighLightPage mHighLightPage;
    private com.zme.zlibrary.widget.viewguide.HighLight mHighLight;

    private RectF mRectF;
    private PathEffect LineEffects;

    private int padding;

    public HighLightLayout(Context context) {
        super(context);
        init();
    }

    public HighLightLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HighLightLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mPaint.setAntiAlias(true);
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        mPaint.setXfermode(porterDuffXfermode);
        //设置画笔遮罩滤镜,可以传入BlurMaskFilter或EmbossMaskFilter，前者为模糊遮罩滤镜而后者为浮雕遮罩滤镜
        mPaint.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.INNER));
        //关闭当前view的硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setClickable(true);
        setWillNotDraw(false);
        //设置虚线的间隔和点的长度
        LineEffects = new DashPathEffect(new float[]{12, 12, 12, 12}, 1);
    }

    public com.zme.zlibrary.widget.viewguide.HighLightPage getHighLightPage() {
        return mHighLightPage;
    }

    public void setHighLightPage(@NonNull com.zme.zlibrary.widget.viewguide.HighLightPage mHighLightPage) {
        this.mHighLightPage = mHighLightPage;
        mHighLight = mHighLightPage.getHighLight();
        if (mHighLight==null||mHighLight.getTargetView()==null){
            return;
        }
        padding = mHighLight.getPadding();
        mRectF = mHighLight.getViewRectF();
        /*lineRectF = new RectF(mRectF.left - padding / 4, mRectF.top - padding / 4, mRectF.right + padding / 4, mRectF.bottom + padding / 4);*/
        int mStrokeColor = getContext().getResources().getColor(mHighLight.getStrokeColor());
        //画虚线
        mLinePaint.setColor(mStrokeColor);
        mLinePaint.setStrokeWidth(mHighLight.getStrokeWidth());
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setDither(true);
        mLinePaint.setPathEffect(LineEffects);

        backGroundColor = getResources().getColor(mHighLightPage.getBackGroundColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(backGroundColor);
        if (mHighLight == null ||mHighLight.getTargetView()==null|| mRectF == null) {
            return;
        }
        Log.e("TAG", "getViewRectF: " + mRectF.left + " " + mRectF.right + " " + mRectF.top + "  " + mRectF.bottom);
        Log.e("TAG", "onDraw: " + mRectF.centerX() + mRectF.centerY());

        switch (mHighLight.getShape()) {
            case com.zme.zlibrary.widget.viewguide.HighLight.CIRCLE:
                //画区域
                canvas.drawCircle(mRectF.centerX(), mRectF.centerY(),
                        mHighLight.getRadius() + padding / 4,
                        mPaint);
                //画线
                canvas.drawCircle(mRectF.centerX(), mRectF.centerY(),
                        mHighLight.getRadius() + padding
                        , mLinePaint);
                break;
            case com.zme.zlibrary.widget.viewguide.HighLight.RECTANGLE:
                //划区域
                canvas.drawRect(mRectF, mPaint);
                //画线
                canvas.drawRect(mHighLight.getHasPaddingRectF(), mLinePaint);
                break;
            case com.zme.zlibrary.widget.viewguide.HighLight.ROUND_RECTANGLE:
                //划区域
                canvas.drawRoundRect(mRectF, mHighLight.getRoundRadius(),
                        mHighLight.getRoundRadius(), mPaint);
                //画虚线
                canvas.drawRoundRect(mHighLight.getHasPaddingRectF(),
                        mHighLight.getRoundRadius(),
                        mHighLight.getRoundRadius(), mLinePaint);
                break;
            case com.zme.zlibrary.widget.viewguide.HighLight.FULL_ROUND_RECTANGLE:
                //划区域
                canvas.drawRoundRect(mRectF, mHighLight.getFullRoundRadius(),
                        mHighLight.getFullRoundRadius(), mPaint);
                //画虚线
                canvas.drawRoundRect(mHighLight.getHasPaddingRectF(),
                        mHighLight.getFullRoundRadius()+padding,
                        mHighLight.getFullRoundRadius()+padding, mLinePaint);
                break;
        }
    }
}
