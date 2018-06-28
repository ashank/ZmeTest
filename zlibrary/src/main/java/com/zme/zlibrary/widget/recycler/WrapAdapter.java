package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.zme.zlibrary.R;

/**
 * Description ：WrapAdpter
 * Author：ZME
 * Create Time ：2018/6/27 22:07
 * Modify Time：2018/6/27 22:07
 * Version：1.0
 */
public class WrapAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_FOOTER = -10;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private View mFooterView;
    private BaseViewHolder mBaseViewHodler;

    public WrapAdapter(Context context, RecyclerView.Adapter adapter) {
        this.mContext = context;
        this.mAdapter = adapter;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_FOOTER || mAdapter == null) {
            mFooterView = mLayoutInflater.inflate(R.layout.view_load_more, parent, false);
            mBaseViewHodler = new BaseViewHolder(mContext, mFooterView,
                    null, null, null);
            return mBaseViewHodler;
        } else {
            return (BaseViewHolder) mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (mAdapter != null && position < getItemCount() - 1) {
            mAdapter.onBindViewHolder(holder, position);
        } else {
            LinearLayout.LayoutParams linearLayoutCompat = (LayoutParams) holder.getView(R.id.tv).getLayoutParams();
            linearLayoutCompat.gravity = Gravity.CENTER;
            holder.getView(R.id.tv).setLayoutParams(linearLayoutCompat);
        }
    }

    @Override
    public int getItemCount() {
        if (mAdapter == null || mAdapter.getItemCount() == 0) {
            return 1;
        } else {
            return mAdapter.getItemCount() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mAdapter == null || mAdapter.getItemCount() == 0 || position == mAdapter.getItemCount()) {
            return VIEW_FOOTER;
        }
        return mAdapter.getItemViewType(position);
    }

    public BaseViewHolder getFooterViewHodler() {
        return mBaseViewHodler;
    }


    /**
     * 定义一个内部类GridSpanSizeLookup继承GridLayoutManager.SpanSizeLookup，
     * 调用父类isHeader和isFooter方法判断是否是头或者尾，
     * 如果是则返回gridManager.getSpanCount();
     * 即一个item占据一行的span数，否则就返回1
     */

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //网格的处理
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isFooter(position) ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }


    /**
     * StaggeredGridLayoutManager中没有setSpanSizeLookup方法，
     * 庆幸的是StaggeredGridLayoutManager.LayoutParams中有setFullSpan方法可以达到同样的效果。
     * 这时候重写的不再是onAttachedToRecyclerView方法而是onViewAttachedToWindow方法
     *
     * @param holder {@link BaseViewHolder}
     */

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        //瀑布流的处理
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }

    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (isFooter(position)) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView
                    .getLayoutParams();
            p.setFullSpan(true);
        }
    }


    private boolean isFooter(int position) {
        if (position==getItemCount()-1){
            return true;
        }else {
            return false;
        }
    }


}
