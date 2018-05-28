package com.funhotel.hmvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.model.entity.User;
import com.zme.zlibrary.utils.StatusBarUtil;
import com.zme.zlibrary.widget.recycler.BaseViewHolder;
import com.zme.zlibrary.widget.recycler.listener.OnItemClickListener;
import com.zme.zlibrary.widget.recycler.SuperBaseAdapter;
import java.util.LinkedList;
import java.util.List;

/**
 * Description ： GreenDao数据测试界面
 * Author：ZME
 * Create Time ：2018/4/11 12:40
 * Modify Time：2018/4/11 12:40
 * Version：1.0
 */
public class GreenDaoActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.rlview)
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private Layout ll;

    private List<User> list = new LinkedList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        StatusBarUtil.translucentBar(this, true);

        ButterKnife.bind(this);
        initView();
       /* list.add(new User("898908348098", "植亚汉", 23, 78, 23, 11));
        list.add(new User("898908348099", "植亚d ", 23, 78, 23, 11));*/
        setupRecyclerView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.rlview);
    }

    /**
     * 配置RecyclerView列表
     */
    private void setupRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //布局从头部还是底部开始布局显示，默认从头部
        mLayoutManager.setReverseLayout(false);
        //优化性能，设置ture 固定宽高，避免重新计算
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView
                .setAdapter(new SuperBaseAdapter<User>(this, list, R.layout.item_greendao_list) {
                    @Override
                    public void binViewHolder(BaseViewHolder viewHolder, User user, int position) {
                        /*viewHolder.setText(R.id.tv_name, user.getName());*/
                    }
                });

    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
