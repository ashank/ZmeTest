package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import com.zme.zlibrary.R;

/**
 * Description ：WrapRecyclerView
 * Author：ZME
 * Create Time ：2018/6/28 21:35
 * Modify Time：2018/6/28 21:35
 * Version：1.0
 */
public class WrapRecyclerView extends RecyclerView {

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
        mAdapter=adapter;
        mWrapAdapter=new WrapAdapter(getContext(),adapter);
        super.setAdapter(mWrapAdapter);
    }

    public void notifyItemRangeChanged(int positionStart,int itemCount){
        mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    public void notifyDataChange(){
        mWrapAdapter.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
    }

    public void setmEnableLoadMore(boolean mEnableLoadMore) {
        this.mEnableLoadMore = mEnableLoadMore;
    }

    public void setNoMoreData(boolean noMoreData) {
        isNoMoreData = noMoreData;
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
        mBaseViewHodler=mWrapAdapter.getFooterViewHodler();
        if (mBaseViewHodler==null || !mEnableLoadMore){
            return;
        }
        mLoadState=LoadState.NONE;
        if (isNoMoreData){
            mBaseViewHodler.setText(R.id.tv,mNoMoreDataText);
        }else {
            if (success){
                mBaseViewHodler.setText(R.id.tv,"加载完成");
            }else {
                mBaseViewHodler.setText(R.id.tv,"加载失败，点击重试～");
                mBaseViewHodler.getItemView().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mLoadState!=LoadState.LOADING && mOnLoadMoreListener!=null){
                            mLoadState=LoadState.LOADING;
                            mOnLoadMoreListener.onLoadMore();
                        }
                    }
                });
            }
        }
    }

    private class MyOnScrollListener extends OnScrollListener{

        private int[] lastPositions;

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
                mBaseViewHodler.setText(R.id.tv,"正在加载中...");
                if (mOnLoadMoreListener!=null){
                    mLoadState=LoadState.LOADING;
                    mOnLoadMoreListener.onLoadMore();
                }
            }else {
                mLoadState=LoadState.NONE;
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

        private int findMax(int[] lastPositions) {
            int max = lastPositions[0];
            for (int value : lastPositions) {
                if (value > max) {
                    max = value;
                }
            }
            return max;
        }


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
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }
}
