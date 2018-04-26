package com.funhotel.hmvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.model.entity.User;
import com.zme.zlibrary.widget.recycler.OnItemClickListner;
import com.zme.zlibrary.widget.recycler.SuperBaseAdapter;
import com.zme.zlibrary.widget.recycler.ViewHolder;
import java.util.LinkedList;
import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements OnItemClickListner {

  @BindView(R.id.rlview)
  private RecyclerView mRecyclerView;
  private LinearLayoutManager mLayoutManager;

  private List<User> list = new LinkedList<User>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_green_dao);
    ButterKnife.bind(this);
    initView();
    list.add(new User("898908348098" , "植亚汉", 23, 78, 23, 11));
    list.add(new User("898908348099" , "植亚d ", 23, 78, 23, 11));
    setupRecyclerView();
  }

  private void initView() {
    mRecyclerView = (RecyclerView) findViewById(R.id.rlview);
  }


  private void setupRecyclerView() {
    mLayoutManager = new LinearLayoutManager(this);
    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    //布局从头部还是底部开始布局显示，默认从头部
    mLayoutManager.setReverseLayout(false);
    GreenDaoListAdapter recyclerViewAdapter = new GreenDaoListAdapter(this,
        list, this);
    //优化性能，设置ture 固定宽高，避免重新计算
    mRecyclerView.setHasFixedSize(true);
//    mRecyclerView.setAdapter(recyclerViewAdapter);
    mRecyclerView.setAdapter(new SuperBaseAdapter<User>(this, list, R.layout.item_greendao_list) {
      @Override
      public void binViewHolder(ViewHolder viewHolder, User user, int position) {
        viewHolder.setText(R.id.tv_name, user.getName());
      }
    });

  }

  @Override
  public void onItemClick(View view, int postion) {



  }
}
