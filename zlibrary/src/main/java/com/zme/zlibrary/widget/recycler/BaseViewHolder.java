package com.zme.zlibrary.widget.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.zme.zlibrary.widget.recycler.listener.OnItemClickListener;
import com.zme.zlibrary.widget.recycler.listener.OnItemLongClickListener;
import com.zme.zlibrary.widget.recycler.listener.OnItemTouchListener;

/**
 * Description ：公共BaseViewHolder
 * Author：ZME
 * Create Time ：2018/4/25 22:21
 * Modify Time：2018/4/25 22:21
 * Version：1.0
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements OnClickListener,OnTouchListener,OnLongClickListener{

    private SparseArray<View> views;
    private Context mContext;
    private View itemView;
    private int type;

    private OnItemLongClickListener onItemLongClickListner;
    private OnItemClickListener onItemClickListner;
    private OnItemTouchListener onItemTouchListener;
    private OnClickListener mOnClickListener;

    public BaseViewHolder(Context context, View itemView,
        OnItemClickListener onItemClickListner,
        OnItemLongClickListener onItemLongClickListner,
        OnItemTouchListener onItemTouchListener) {
        super(itemView);
        this.mContext = context;
        views = new SparseArray<>();
        this.itemView=itemView;
        this.onItemTouchListener=onItemTouchListener;
        this.onItemLongClickListner=onItemLongClickListner;
        this.onItemClickListner=onItemClickListner;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        /*itemView.setOnTouchListener(this);*/
    }

    public BaseViewHolder(Context context, View itemView,
            OnClickListener mOnClickListener,
            OnItemClickListener onItemClickListner,
            OnItemLongClickListener onItemLongClickListner,
            OnItemTouchListener onItemTouchListener) {
        super(itemView);
        this.mContext = context;
        views = new SparseArray<>();
        this.itemView=itemView;
        this.onItemTouchListener=onItemTouchListener;
        this.onItemLongClickListner=onItemLongClickListner;
        this.onItemClickListner=onItemClickListner;
        this.mOnClickListener=mOnClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        /*itemView.setOnTouchListener(this);*/
    }

    public static BaseViewHolder  get(Context context, int  layoutId,ViewGroup parent,
        OnItemClickListener onItemClickListner,
        OnItemLongClickListener onItemLongClickListner,
        OnItemTouchListener onItemTouchListener) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BaseViewHolder(context,itemView,
            onItemClickListner,
            onItemLongClickListner,
            onItemTouchListener);
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }


    public void setItemViewVisible(int visible){
        this.itemView.setVisibility(visible);
    }


    public View getItemView() {
        return itemView;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * 设置TextView的值
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public BaseViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public BaseViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseViewHolder setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public BaseViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public BaseViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public BaseViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public BaseViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public BaseViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public BaseViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public BaseViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public BaseViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public BaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }


    public BaseViewHolder setOnClickListener(int viewId,
        View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnTouchListener(int viewId,
        View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseViewHolder setOnLongClickListener(int viewId,
        View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    @Override
    public void onClick(View v) {

        if (mOnClickListener!=null){
            Log.e("TAG", "onClick 2222222~: " );
            mOnClickListener.onClick(v);
            return;
        }
        Log.e("TAG", "onClick : "+getAdapterPosition() );
        if (onItemClickListner!=null){
            onItemClickListner.onItemClick(getItemView(),getAdapterPosition());

        }

    }

    @Override
    public boolean onLongClick(View v) {
        Log.e("TAG", "onLongClick: " );
        if (onItemLongClickListner!=null){
            onItemLongClickListner.onItemLongClick(getItemView(),getAdapterPosition());
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e("TAG", "onTouch: " );
        if (onItemTouchListener!=null){
            onItemTouchListener.onItemTouch(getItemView(),event,getAdapterPosition());
        }
        return true;
    }

    public void recycleView(){
        if (views!=null){
            views.clear();
            views=null;
        }
    }


    public void setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
}
