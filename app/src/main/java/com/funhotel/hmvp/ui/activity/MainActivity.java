
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

package com.funhotel.hmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.adapter.MainListAdapter;
import com.funhotel.hmvp.test.jni.JniDemo;
import com.funhotel.hmvp.ui.activity.presentation.PresentationActivity;
import com.zme.zlibrary.base.BaseActivity;
import com.zme.zlibrary.data.http.HttpRequestListener;
import com.zme.zlibrary.data.http.HttpServiceImp;
import com.zme.zlibrary.data.http.NewEntityNew;
import com.zme.zlibrary.data.http.NewType;
import com.zme.zlibrary.widget.recycler.listener.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements OnItemClickListener,
    SwipeRefreshLayout.OnRefreshListener ,HttpRequestListener{

  private final static String TAG = "MainActivity";
  private RecyclerView mRecyclerView;
  private StaggeredGridLayoutManager mLayoutManager;
  private List<String> list = new ArrayList<>();
  private static SwipeRefreshLayout mSwipeRefreshLayout;

  private static Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case 0:
          mSwipeRefreshLayout.setRefreshing(false);
          break;
      }
      super.handleMessage(msg);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
    setupRefreshLayout();
    list = getList();
    setupRecyclerView();

    //网络调用
      HttpServiceImp
              .with()
              .addHeaders(null)
              .init()
              .setHttpRequestListener(this)
              .getNewList("头条", "20", "0");

      HttpServiceImp
              .with()
              .init()
              .setHttpRequestListener(this)
              .getNewType();
  }

    @Override
    public void onRequestSuccess(Object o, int pageIndex) {
        if (o instanceof NewEntityNew) {
            Log.e(TAG, "onRequestSuccess: " + ((NewEntityNew) o).getNum());
        }else if (o instanceof List ){
            Log.e(TAG, "onRequestSuccess: 2");
            List<String > list = (List<String>)o;
            if (list.isEmpty()){
              return;
            }
          for (String string: list) {
            Log.e(TAG, "onRequestSuccess: 2" +string);
          }
        } else {
            Log.e(TAG, "onRequestSuccess: 3");
        }
    }

    @Override
    public void onRequestFail(Object o, int pageIndex) {
      if (o instanceof NewEntityNew) {
        Log.e(TAG, "onRequestFail: 1" );
      }else if (o instanceof NewType){
        Log.e(TAG, "onRequestFail: 2" );
      } else {
        Log.e(TAG, "onRequestFail: 3" );
      }

    }

    @Override
    public void onHttpFail(Throwable t, String message, int pageIndex) {
        Log.e(TAG, "onHttpFail: ");

    }

  @Override
  public void onResume() {
    super.onResume();

    /*Goods goods = new Goods();
    //生产者生产商品
    Producter p = new Producter();
    p.setGoods(goods);

    //消费者取走商品
    Customer c = new Customer();
    c.setGoods(goods);

    new Thread(p).start();
    new Thread(c).start();*/

    //JNIdemo
    JniDemo demo=new JniDemo();
    Toast.makeText(this, "demo==="+demo.getString()+"==="+demo.calendar(2,1), Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  private void initView() {
    mRecyclerView = (RecyclerView) findViewById(R.id.rlview);
    mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
  }

  private List<String> getList() {
    List<String> list = new ArrayList<>();
    list.add("pretenation");
    list.add("retrofit+rxjava");
    list.add("Material Design");
    list.add("grenDao");
    list.add("Permission");
    list.add("DesignPattern");
    list.add("RxJava");
    list.add("CustomView");
    list.add("Banner");
    for (int i = 0; i < 100; i++) {
      list.add("test" + i);
    }
    return list;
  }

  private void setupRefreshLayout() {
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout
        .setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
            getResources().getColor(android.R.color.holo_green_light),
            getResources().getColor(android.R.color.holo_orange_light),
            getResources().getColor(android.R.color.holo_red_light));
  }

  private void setupRecyclerView() {
    /*mLayoutManager=new LinearLayoutManager(this);
    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    //布局从头部还是底部开始布局显示，默认从头部
    mLayoutManager.setReverseLayout(false);*/
    //瀑布流
    mLayoutManager = new StaggeredGridLayoutManager(2,
        StaggeredGridLayoutManager.VERTICAL);
    //不设置的话，图片闪烁错位，有可能有整列错位的情况。
    mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
    mRecyclerView.setLayoutManager(mLayoutManager);
    //分隔符
    /*mRecyclerView.addItemDecoration(new Line(this,Line.VERTICAL_LIST));*/
    //优化性能，设置ture 固定宽高，避免重新计算
    //mRecyclerView.setHasFixedSize(true);
//   mRecyclerView.setAdapter(recyclerViewAdapter);
    MainListAdapter adapter=new MainListAdapter(this,list);
    adapter.setOnItemClickListener(this);
    mRecyclerView.setAdapter(adapter);

    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        //这行主要解决了当加载更多数据时，底部需要重绘，否则布局可能衔接不上。
        mLayoutManager.invalidateSpanAssignments();
      }
    });
  }

  @Override
  public void onItemClick(View view, int postion) {
    Toast.makeText(this, list.get(postion), Toast.LENGTH_SHORT).show();
    Intent intent = new Intent();
    switch (postion) {
      case 0:
        intent.setClass(MainActivity.this, PresentationActivity.class);
        break;
      case 1:
        intent.setClass(MainActivity.this, RxJavaOkhttpActivity.class);
        break;
      case 2:
        intent.setClass(MainActivity.this, GreenDaoActivity.class);
        break;
      case 3:
        intent.setClass(MainActivity.this, GreenDaoActivity.class);
        break;
      case 4:
        intent.setClass(MainActivity.this, PermissionDemoActivity.class);
        break;
      case 5:
        intent.setClass(MainActivity.this, DesignpatternActivity.class);
        break;
      case 6:
        intent.setClass(MainActivity.this, RxJavaDemoActivity.class);
        break;
      case 7:
        intent.setClass(MainActivity.this, CustomViewActivity.class);
        break;
      case 8:
        intent.setClass(MainActivity.this, BannerDemoActivity.class);
        break;
      default:
        intent.setClass(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        break;
    }
    startActivity(intent);

  }

  @Override
  public void onRefresh() {
    Toast.makeText(this, "刷新中", Toast.LENGTH_SHORT).show();
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        mHandler.sendEmptyMessage(0);
      }
    }, 3000);
  }

}
