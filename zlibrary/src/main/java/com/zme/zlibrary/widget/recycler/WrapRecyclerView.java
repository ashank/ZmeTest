package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.wang.avi.AVLoadingIndicatorView;
import com.zme.zlibrary.R;

/**
 * Description ：WrapRecyclerView
 * Author：ZME
 * Create Time ：2018/6/28 21:35
 * Modify Time：2018/6/28 21:35
 * Version：1.0
 */
public class WrapRecyclerView extends RecyclerView implements View.OnClickListener {

    private boolean mEnableLoadMore=true;
    private boolean isNoMoreData=true;
    private int preLoadItem;
    private LoadState mLoadState;

    private WrapAdapter mWrapAdapter;
    private Adapter mAdapter;
    private BaseViewHolder mBaseViewHodler;

    public enum  LoadState{
        NONE,
        LOADING,
        LOAD_FINISH
    }
    private String mNoMoreDataText;
    private boolean add=false;

    private boolean isMoveDown=false;
    private int offsetdy;
    private int mLastVisibleItemPositon;
    private int mTotalItemCount;

    private OnLoadMoreListener mOnLoadMoreListener;


    public WrapRecyclerView(Context context) {
        this(context,null);
    }

    public WrapRecyclerView(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WrapRecyclerView(Context context,
            @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mNoMoreDataText="没有更多数据了";
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mEnableLoadMore && !add){
            setOnScrollListener(new MyOnScrollListener());
            add=true;
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof WrapAdapter){
            mWrapAdapter=(WrapAdapter) adapter;
            mAdapter=mWrapAdapter.getAdapter();
        }else {
            mAdapter=adapter;
            mWrapAdapter=new WrapAdapter(getContext(),adapter);
        }
        mWrapAdapter.setOnClickListener(this);
        super.setAdapter(mWrapAdapter);
    }

    public void notifyItemRangeChanged(int positionStart,int itemCount){
        if (mWrapAdapter!=null){
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }
        if ( mAdapter != null){
            mAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

    }

    public void notifyDataChange(){
        if (mWrapAdapter!=null){
            mWrapAdapter.notifyDataSetChanged();
        }
        if ( mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }


    }

    public void setmEnableLoadMore(boolean mEnableLoadMore) {
        this.mEnableLoadMore = mEnableLoadMore;
    }

    public void setNoMoreData(boolean noMoreData) {
        isNoMoreData = noMoreData;
        if (mWrapAdapter==null){
            return;
        }
        mBaseViewHodler=mWrapAdapter.getFooterViewHodler();
        if (mBaseViewHodler==null){
            return;
        }
        mLoadState=LoadState.NONE;
        if (isNoMoreData){
            mBaseViewHodler.getView(R.id.load).setVisibility(GONE);
            ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).hide();
            mBaseViewHodler.setText(R.id.tv,mNoMoreDataText);
        }else {
            LayoutManager manager= this.getLayoutManager();
            mLastVisibleItemPositon= getLastVisibleItemPostion(this,manager);
            mTotalItemCount=manager.getItemCount();

            mBaseViewHodler.getView(R.id.load).setVisibility(GONE);
            ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).hide();

            Log.e("TAG", "setNoMoreData: " + mLastVisibleItemPositon   +"    " +mTotalItemCount);
            if (mLastVisibleItemPositon == mTotalItemCount-1){
                //如果显示的最后一项，需要显示加载更多
                mBaseViewHodler.setText(R.id.tv,"点击加载更多...");

            }

        }

    }

    public void setPreLoadItem(int preLoadItem) {
        this.preLoadItem = preLoadItem;
    }

    public void setmLoadState(LoadState mLoadState) {
        this.mLoadState = mLoadState;
    }

    public void setmNoMoreDataText(String mNoMoreDataText) {
        this.mNoMoreDataText = mNoMoreDataText;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }


    public void finishLoadMore(boolean success,boolean isNoMoreData){

        this.isNoMoreData=isNoMoreData;
        if (mWrapAdapter==null){
            return;
        }
        mBaseViewHodler=mWrapAdapter.getFooterViewHodler();
        if (mBaseViewHodler==null || !mEnableLoadMore){
            return;
        }
        mBaseViewHodler.setItemViewVisible(View.VISIBLE);
        mLoadState=LoadState.NONE;
        if (isNoMoreData){
            mBaseViewHodler.getView(R.id.load).setVisibility(GONE);
            ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).hide();
            mBaseViewHodler.setText(R.id.tv,mNoMoreDataText);
        }else {
            if (success){
                mBaseViewHodler.getView(R.id.load).setVisibility(GONE);
                ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).hide();
                mBaseViewHodler.setText(R.id.tv,"加载完成");

                LayoutManager manager= this.getLayoutManager();
                mLastVisibleItemPositon= getLastVisibleItemPostion(this,manager);
                mTotalItemCount=manager.getItemCount();
                if (mLastVisibleItemPositon==mTotalItemCount-1){
                    //如果显示的最后一项，需要显示加载更多
                    mBaseViewHodler.setText(R.id.tv,"点击加载更多...");
                }
            }else {
                mBaseViewHodler.getView(R.id.load).setVisibility(GONE);
                ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).hide();
                mBaseViewHodler.setText(R.id.tv,"加载失败，点击重试～");
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (mWrapAdapter==null){
            return;
        }
        mBaseViewHodler=mWrapAdapter.getFooterViewHodler();
        if (mBaseViewHodler==null || !mEnableLoadMore){
            return;
        }
        if (mLoadState!=LoadState.LOADING && mOnLoadMoreListener!=null){
            mLoadState=LoadState.LOADING;
            mBaseViewHodler.getView(R.id.load).setVisibility(VISIBLE);
            ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).show();
            mBaseViewHodler.setText(R.id.tv,"正在加载中...");
            mOnLoadMoreListener.onLoadMore();
        }

    }

    private class MyOnScrollListener extends OnScrollListener{

        public MyOnScrollListener() {
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (mWrapAdapter==null
                    ||mLoadState==LoadState.LOADING
                    ||!isMoveDown){
                return;
            }
            mBaseViewHodler=mWrapAdapter.getFooterViewHodler();
            LayoutManager manager= recyclerView.getLayoutManager();
            mLastVisibleItemPositon=getLastVisibleItemPostion(recyclerView,manager);
            mTotalItemCount=manager.getItemCount();
            if (mBaseViewHodler==null){
                return;
            }
            if (mLastVisibleItemPositon==mTotalItemCount-preLoadItem-1 && !isNoMoreData){
                mBaseViewHodler.setItemViewVisible(View.VISIBLE);
                mBaseViewHodler.getView(R.id.load).setVisibility(VISIBLE);
                ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).show();
                mBaseViewHodler.setText(R.id.tv,"正在加载中...");
                if (mOnLoadMoreListener!=null){
                    mLoadState=LoadState.LOADING;
                    mOnLoadMoreListener.onLoadMore();
                }
            }else {
                mLoadState=LoadState.NONE;
                mBaseViewHodler.getView(R.id.load).setVisibility(GONE);
                ((AVLoadingIndicatorView)(mBaseViewHodler.getView(R.id.load))).hide();
                mBaseViewHodler.setText(R.id.tv,mNoMoreDataText);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy-offsetdy>0){
                isMoveDown=true;
            }else {
                isMoveDown=false;
            }
        }


    }

    private int[] lastPositions;
    private  int  getLastVisibleItemPostion(RecyclerView recyclerView,LayoutManager manager){
        int position;
        if (manager instanceof LinearLayoutManager){
            position=((LinearLayoutManager)manager).findLastVisibleItemPosition();
        } else if (manager instanceof GridLayoutManager){
            position=((GridLayoutManager)manager).findLastVisibleItemPosition();
        }else if (manager instanceof StaggeredGridLayoutManager){
            if (lastPositions == null) {
                lastPositions = new int[((StaggeredGridLayoutManager) manager).getSpanCount()];
            }
            ((StaggeredGridLayoutManager)manager).findLastVisibleItemPositions(lastPositions);
            position=findMax(lastPositions);
        }else {
            throw new RuntimeException("不支持的layoutManager");
        }
        return  position;
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }
}
