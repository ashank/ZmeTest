package com.funhotel.hmvp.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.funhotel.hmvp.R;
import com.zme.zlibrary.data.http.NewEntityNew.ListEntity;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.IMulItemViewType;
import com.zme.zlibrary.widget.recycler.MulItemBaseAdapter;
import java.util.List;

/**
 * Description ：NewAdapter
 * Author：ZME
 * Create Time ：2018/4/26 23:14
 * Modify Time：2018/4/26 23:14
 * Version：1.0
 */
public class MulItemNewAdapter extends MulItemBaseAdapter<ListEntity> implements IMulItemViewType<ListEntity> {

    private Context mContext;
    private List<ListEntity> mDatas;
    private IMulItemViewType<ListEntity> iMulItemViewType;

    private static final int ONE = 0;
    private static final int TWO = 1;
    private static final int THREE = 2;

    public MulItemNewAdapter(Context mContext, List<ListEntity> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.iMulItemViewType = this;
        setiMulItemViewType(this.iMulItemViewType);
    }

    @Override
    public void binViewHolder(final BaseViewHolder viewHolder, ListEntity dataEntity, int position) {
        viewHolder.setText(R.id.item_tv_name, dataEntity.getTitle());
        viewHolder.setText(R.id.item_tv_newspaper, dataEntity.getSrc());
        Glide.with(mContext).load(dataEntity.getPic())
                .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb01));
    }

    @Override
    public int getItemViewType(int position, ListEntity dataEntity) {
        return ONE;
    }

    @Override
    public int getLayoutId(int viewType) {
        if (viewType == TWO) {
            return R.layout.item_new_list_two_img;
        }
        if (viewType == THREE) {
            return R.layout.item_new_list_three_img;
        }
        if (viewType == ONE) {
            return R.layout.item_new_list_one_img;
        }
        return R.layout.item_new_list_one_img;
    }

    @Override
    public List<ListEntity> getData() {
        return super.getData();
    }
}
