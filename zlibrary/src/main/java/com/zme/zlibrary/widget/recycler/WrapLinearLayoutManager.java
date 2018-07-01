package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Description ：WrapLinearLayoutManager
 * Author：ZME
 * Create Time ：2018/6/30 10:32
 * Modify Time：2018/6/30 10:32
 * Version：1.0
 */
public class WrapLinearLayoutManager extends LinearLayoutManager {


    public WrapLinearLayoutManager(Context context) {
        super(context);
    }

    public WrapLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public WrapLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(Recycler recycler, State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            Log.e("TAG", "onLayoutChildren: "+e.getMessage());
            e.printStackTrace();

        }

    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            return super.scrollVerticallyBy(dy, recycler, state);
        } catch (Exception e) {
            Log.e("TAG", "scrollVerticallyBy: "+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return 0;
    }

}
