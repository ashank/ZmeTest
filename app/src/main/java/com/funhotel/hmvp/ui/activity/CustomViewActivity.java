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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.funhotel.hmvp.R;
import com.zme.zlibrary.widget.DragLayout;
import com.zme.zlibrary.widget.refresh.RefreshLayout;
import com.zme.zlibrary.widget.refresh.listener.OnRefreshListener;

public class CustomViewActivity extends AppCompatActivity {

  private DragLayout dradImageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom_view);

    final RefreshLayout refreshLayout=(RefreshLayout)findViewById(R.id.refresh_layout);
    refreshLayout.onAutoRefresh();
    refreshLayout.setOnRefreshListener(new OnRefreshListener() {
      @Override
      public void onRefresh() {
        //刷新完成
        Log.e("TAG", "onRefresh: 开始刷新" );
       /* refreshLayout.postDelayed(new Runnable() {
          @Override
          public void run() {
            refreshLayout.onRefreshFinish();
          }
        },3000);*/
      }
    });













  }
}
