/*
 *   Copyright (C) 2018  ZME
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.zme.zlibrary.widget.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.zme.zlibrary.widget.recycler.listener.OnItemClickListener;
import com.zme.zlibrary.widget.recycler.listener.OnItemLongClickListener;
import com.zme.zlibrary.widget.recycler.listener.OnItemTouchListener;
import java.util.List;

/**
 * Description ：万能Adapter的抽象类
 *
 * @param <T> 万能的范型
 *
 * Author：ZME
 * Create Time ：2018/4/22 22:38
 * Modify Time：2018/4/22 22:38
 * Version：1.0
 */

public abstract class SuperBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements IDataAction<T> {

    private Context mContext;
    private List<T> mDatas;
    private int layoutId;
    private BaseViewHolder holder;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnItemTouchListener onItemTouchListener;

    public SuperBaseAdapter(Context mContext, List<T> mDatas, int layoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder(parent, layoutId, viewType);
    }

    /**
     * 获取Holder
     *
     * @param parent ViewGroup
     * @param layoutId 布局的资源ID
     * @param viewType 列表布局的类型
     * @return 返回 BaseViewHolder 实例
     */
    protected BaseViewHolder getHolder(ViewGroup parent, int layoutId, int viewType) {
        holder = BaseViewHolder.get(mContext, layoutId, parent,
                onItemClickListener,
                onItemLongClickListener,
                onItemTouchListener);
        holder.setType(viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (null == mDatas || mDatas.isEmpty()) {
            return;
        }
        //绑定数据和视图
        binViewHolder(holder, mDatas.get(position), position);
    }

    /**
     * 绑定数据
     *
     * @param viewHolder {@link BaseViewHolder}
     * @param t 范型数据
     * @param position 列表的位置
     */
    public abstract void binViewHolder(BaseViewHolder viewHolder, T t, int position);


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickLietener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public void resetData(List<T> list) {
        if (this.mDatas != null) {
            this.mDatas.clear();
        }
        if (mDatas != null) {
            this.mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public void addData(List<T> list) {
        if (this.mDatas == null) {
            return;
        }
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void addData(T t) {
        this.mDatas.add(t);
        notifyDataSetChanged();
    }

    @Override
    public void deleteData(int position) {
        if (this.mDatas == null) {
            return;
        }
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void deleteAllData() {
        if (this.mDatas == null) {
            return;
        }
        mDatas.clear();
        notifyDataSetChanged();
    }

}
