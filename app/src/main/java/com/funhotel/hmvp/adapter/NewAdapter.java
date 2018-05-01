package com.funhotel.hmvp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.model.entity.NewEntity;
import com.funhotel.hmvp.model.entity.NewEntity.ResultEntity.DataEntity;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.IMulItemViewType;
import com.zme.zlibrary.widget.recycler.SuperBaseAdapter;
import java.util.List;

/**
 * Description ：NewAdapter
 * Author：ZME
 * Create Time ：2018/4/26 23:14
 * Modify Time：2018/4/26 23:14
 * Version：1.0
 */
public class NewAdapter extends SuperBaseAdapter<NewEntity.ResultEntity.DataEntity> implements
    IMulItemViewType {

  private Context mContext;
  private List<DataEntity> mDatas;
  private IMulItemViewType<DataEntity> iMulItemViewType;
  public NewAdapter(Context mContext, List<DataEntity> mDatas) {
    super(mContext, mDatas, iMulItemViewType);
    this.mContext=mContext;
    this.mDatas=mDatas;
  }

  @Override
  public int getItemViewType(int position, Object o) {
    return 0;
  }

  @Override
  public int getViewTypeCount() {
    return 0;
  }

  @Override
  public int getLayoutId(int viewType) {
    return 0;
  }

  @Override
  public void binViewHolder(BaseViewHolder viewHolder, DataEntity dataEntity, int position) {

    viewHolder.setText(R.id.item_tv_name,dataEntity.getTitle());
    viewHolder.setText(R.id.item_tv_newspaper,dataEntity.getAuthor_name());
    Glide.with(mContext).load(dataEntity.getThumbnail_pic_s())
        .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb01));

    if (TextUtils.isEmpty(dataEntity.getThumbnail_pic_s02())) {
      viewHolder.setVisible(R.id.iv_thumb02,View.INVISIBLE);
    } else {
      Glide.with(mContext).load(dataEntity.getThumbnail_pic_s02())
          .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb02));
    }

    if (TextUtils.isEmpty(dataEntity.getThumbnail_pic_s02())) {
      viewHolder.setVisible(R.id.iv_thumb03,View.INVISIBLE);
    } else {
      Glide.with(mContext).load(dataEntity.getThumbnail_pic_s02())
          .centerCrop().into((ImageView) viewHolder.getView(R.id.iv_thumb03));
    }
  }

  /**
   * 重制数据
   * @param mDatas 数据
   */
  public void resetList(List<DataEntity> mDatas) {

    if (this.mDatas!=null){
      this.mDatas.clear();
    }

    if (mDatas!=null){
      this.mDatas.addAll(mDatas);
    }
  }

}
