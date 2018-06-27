package com.funhotel.hmvp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.model.entity.NewEntity;
import com.funhotel.hmvp.model.entity.NewEntity.ResultEntity.DataEntity;
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
public class MulItemNewAdapter extends MulItemBaseAdapter<DataEntity> implements IMulItemViewType<DataEntity> {

    private Context mContext;
    private List<DataEntity> mDatas;
    private IMulItemViewType<DataEntity> iMulItemViewType;

    private static final int ONE = 0;
    private static final int TWO = 1;
    private static final int THREE = 2;

    public MulItemNewAdapter(Context mContext, List<DataEntity> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.iMulItemViewType = this;
        setiMulItemViewType(this.iMulItemViewType);
    }

    @Override
    public void binViewHolder(final BaseViewHolder viewHolder, DataEntity dataEntity, int position) {
        if (viewHolder.getType() == ONE) {
            viewHolder.setText(R.id.item_tv_name, dataEntity.getTitle());
            viewHolder.setText(R.id.item_tv_newspaper, dataEntity.getAuthor_name());
            Glide.with(mContext).load(dataEntity.getThumbnail_pic_s())
                    .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb01));
        } else if (viewHolder.getType() == TWO) {
            viewHolder.setText(R.id.item_tv_name, dataEntity.getTitle());
            viewHolder.setText(R.id.item_tv_newspaper, dataEntity.getAuthor_name());
            Glide.with(mContext).load(dataEntity.getThumbnail_pic_s())
                    .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb01));
            Glide.with(mContext).load(dataEntity.getThumbnail_pic_s02())
                    .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb02));
        } else if (viewHolder.getType() == THREE) {
            viewHolder.setText(R.id.item_tv_name, dataEntity.getTitle());
            viewHolder.setText(R.id.item_tv_newspaper, dataEntity.getAuthor_name());
            Glide.with(mContext).load(dataEntity.getThumbnail_pic_s())
                    .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb01));
            Glide.with(mContext).load(dataEntity.getThumbnail_pic_s02())
                    .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb02));
            Glide.with(mContext).load(dataEntity.getThumbnail_pic_s03())
                    .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb03));
        }
    }

    @Override
    public int getItemViewType(int position, NewEntity.ResultEntity.DataEntity dataEntity) {
        int type = ONE;
        if (!TextUtils.isEmpty(dataEntity.getThumbnail_pic_s())
                && !TextUtils.isEmpty(dataEntity.getThumbnail_pic_s02())
                && !TextUtils.isEmpty(dataEntity.getThumbnail_pic_s03())) {
            type = THREE;
        } else if (!TextUtils.isEmpty(dataEntity.getThumbnail_pic_s())
                || !TextUtils.isEmpty(dataEntity.getThumbnail_pic_s02())
                || !TextUtils.isEmpty(dataEntity.getThumbnail_pic_s03())) {
            type = ONE;
        } else if ((!TextUtils.isEmpty(dataEntity.getThumbnail_pic_s()) && !TextUtils
                .isEmpty(dataEntity.getThumbnail_pic_s02()))
                || (!TextUtils.isEmpty(dataEntity.getThumbnail_pic_s()) && !TextUtils
                .isEmpty(dataEntity.getThumbnail_pic_s03()))
                || (!TextUtils.isEmpty(dataEntity.getThumbnail_pic_s02()) && !TextUtils
                .isEmpty(dataEntity.getThumbnail_pic_s03()))) {
            type = TWO;
        }
        return type;
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
}
