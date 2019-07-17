package com.zme.glide.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.zme.zlibrary.utils.AndroidDimensUtil;

/**
 * FileName    : DemoActivity
 *
 * @author : Zhiyahan Date        : 2018/10/17 9:37 Description : demo
 */
public class DemoActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "DemoActivity";
    private ImageView mBt3;
    private Button mBt2;
    private Button mBt1;
    private Button mBtLogout;
    private Button mBtShare;
    private Button mBtDetailShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {

        mBt3 = findViewById(R.id.bt3);
        mBt2 = findViewById(R.id.bt2);
        mBt1 = findViewById(R.id.bt1);
        mBtLogout = findViewById(R.id.bt_logout);
        mBtShare = findViewById(R.id.bt_share);
        mBtDetailShare = findViewById(R.id.bt_detail_share);
    }

    private void initData() {
        mBt3.setOnClickListener(this);
        mBt2.setOnClickListener(this);
        mBt1.setOnClickListener(this);
        mBtLogout.setOnClickListener(this);
        mBtShare.setOnClickListener(this);
        mBtDetailShare.setOnClickListener(this);

        Glide.with(this).load(R.drawable.lb_ic_pause).into(mBt3);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt3:
                break;
            case R.id.bt2:
                break;
            case R.id.bt1:
                break;
            case R.id.bt_logout:
                break;
            case R.id.bt_detail_share:
                break;
            case R.id.bt_share:
                break;
            default:
        }
    }
}
