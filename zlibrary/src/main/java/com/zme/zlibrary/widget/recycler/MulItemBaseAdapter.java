package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import java.util.List;

/**
 * Description ：MulItemBaseAdapter
 * @param <T>
 *
 * Author：ZME
 * Create Time ：2018/5/21 21:48
 * Modify Time：2018/5/21 21:48
 * Version：1.0
 */
public abstract class MulItemBaseAdapter<T> extends SuperBaseAdapter<T> {

    private IMulItemViewType<T> iMulItemViewType;
    private List<T> mDatas;
    private Context mContext;
    private BaseViewHolder holder;

    public MulItemBaseAdapter(Context mContext, List<T> mDatas) {
        super(mContext, mDatas, -1);
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    public MulItemBaseAdapter(Context mContext, List<T> mDatas, @NonNull IMulItemViewType<T> mulItemViewType) {
        super(mContext, mDatas, -1);
        this.iMulItemViewType = mulItemViewType;
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    /**
     * 获取多种的布局的接口
     * @return {@link IMulItemViewType}
     */
    public IMulItemViewType<T> getiMulItemViewType() {
        return iMulItemViewType;
    }

    /**
     * 设置 MulItemViewType
     * @param iMulItemViewType {@link IMulItemViewType}
     */
    public void setiMulItemViewType(IMulItemViewType<T> iMulItemViewType) {
        this.iMulItemViewType = iMulItemViewType;
    }

    @Override
    public int getItemViewType(int position) {
        if (iMulItemViewType != null) {
            if (mDatas != null && mDatas.size() > 0) {
                return iMulItemViewType.getItemViewType(position, mDatas.get(position));
            } else {
                return 0;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = iMulItemViewType.getLayoutId(viewType);
        return super.getHolder(parent, layoutId, viewType);
    }
}
