package com.zme.glide.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zme.glide.ImageLoader;
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

        ImageLoader.with(this)
                .load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2854956166,1658664264&fm=26&gp=0.jpg")
                .thumbnail(0.1f)
                .requestListener(new RequestListener() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target,
                            boolean isFirstResource) {
                        Log.e(TAG, "onLoadFailed: " );
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource,
                            boolean isFirstResource) {
                        Log.e(TAG, "onResourceReady: " );
                        return false;
                    }
                })
                .placeholder(R.drawable.lb_ic_pause).into(mBt3);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt3:
                ImageLoader.with(this)
                        .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2174909441,2495215020&fm=26&gp=0.jpg")
                        .override(410, 410)
                        .placeholder(R.drawable.lb_ic_pause).into(mBt3);

                break;
            case R.id.bt2:
                ImageLoader.with(this)
                        .load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4099762989,3252607071&fm=26&gp=0.jpg")
                        .override(210, 210)
                        .placeholder(R.drawable.lb_ic_pause).into(mBt3);
                break;
            case R.id.bt1:
                ImageLoader.with(this)
                        .load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3357786243,3135716437&fm=26&gp=0.jpg")
                        .placeholder(R.drawable.lb_ic_pause).into(mBt3);
                break;
            case R.id.bt_logout:
                ImageLoader.with(this)
                        .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1&gp=0.jpg")
                        .placeholder(R.drawable.lb_ic_pause).error(R.drawable.lb_ic_cc).into(mBt3);
                break;
            case R.id.bt_detail_share:
                ImageLoader.with(this)
                        .load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2786741331,312930537&fm=26&gp=0.jpg")
                        .placeholder(R.drawable.lb_ic_pause).into(mBt3);
                break;
            case R.id.bt_share:
                ImageLoader.with(this)
                        .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2299113196,3410006479&fm=26&gp=0.jpg")
                        .placeholder(R.drawable.lb_ic_pause).into(mBt3);
                break;
            default:
        }
    }
}
