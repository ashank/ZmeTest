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

package demo.android.ashank.com.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zme.zlibrary.utils.LogUtils;
import com.zme.zlibrary.widget.recycler.listener.OnItemClickListener;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    OnRefreshListener, OnItemClickListener {

  @BindView(R.id.tv_name)
  private TextView tvName;
  @BindView(R.id.textView)
  private TextView tvAdress;

  private RecyclerView mRecyclerView;
  private LinearLayoutManager mLayoutManager;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private BluetoothAdapter bluetoothAdapter = null;
  private MainRecyclerViewAdapter recyclerViewAdapter;
  private List<BluetoothDevice> devices = new LinkedList<>();

  private BluetoohHelper bluetoohHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab =  findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer =  findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView =  findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    View view = navigationView.getHeaderView(0);
    tvAdress =  view.findViewById(R.id.textView);
    tvName =  view.findViewById(R.id.tv_name);
    mRecyclerView =  findViewById(R.id.rv_main_list);
    mSwipeRefreshLayout =  findViewById(R.id.refresh_layout);

    bluetoohHelper = BluetoohHelper.getInstance(this);
    bluetoohHelper.registerBluetoothReceiver(this, mBroadcastReceiver);
    setupRecyclerView();
    setupRefreshLayout();

    if (!bluetoohHelper.isSupportBluetooth()) {
      Toast.makeText(this, "当前设备不支持蓝牙", Toast.LENGTH_SHORT).show();
      return;
    }
    boolean open = bluetoohHelper.openBluetooth();
    if (open) {
      //本地设备信息
      showLocalBluetoothInfo();
//      devices = bluetoohHelper.getBondedDevicesList();
//      //已经连接的设备
//      if (null != devices && !devices.isEmpty()) {
//        LogUtils.e("devices size=" + devices.size());
//        recyclerViewAdapter.setList(devices);
//        recyclerViewAdapter.notifyDataSetChanged();
//      }

      //扫描设备
      bluetoohHelper.startDiscovery();
    }

  }

  @Override
  public void onBackPressed() {
    //按返回键的处理
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_bondelist) {
      // Handle the camera action
      devices = bluetoohHelper.getBondedDevicesList();
      recyclerViewAdapter.setList(devices);
      recyclerViewAdapter.notifyDataSetChanged();
    } else if (id == R.id.nav_scanlist) {
      if (bluetoohHelper.openBluetooth()) {
        bluetoohHelper.startDiscovery();
      }
    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
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
    mLayoutManager = new LinearLayoutManager(this);
    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    //布局从头部还是底部开始布局显示，默认从头部
    mLayoutManager.setReverseLayout(false);
    mRecyclerView.setLayoutManager(mLayoutManager);
    //分隔符
    /*mRecyclerView.addItemDecoration(new Line(this,Line.VERTICAL_LIST));*/
    recyclerViewAdapter = new MainRecyclerViewAdapter(this, devices, this);
    //优化性能，设置 true 固定宽高，避免重新计算
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setAdapter(recyclerViewAdapter);
  }


  private void showLocalBluetoothInfo() {
    LocalBluetoothDevice localBluetoothDevice = bluetoohHelper.getLocalDevice();
    if (null != localBluetoothDevice) {
      tvName.setText(localBluetoothDevice.getName());
      tvAdress.setText(localBluetoothDevice.getAddress());
    }
  }

  //接受蓝牙的广播
  private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      LogUtils.e("mBroadcastReceiver:");
      bluetoohHelper.actionBluetooth(context, intent, actionBluetooth);
    }
  };

  /**
   * 蓝牙的动作处理
   */
  private ActionBluetooth actionBluetooth = new ActionBluetooth();

  private class ActionBluetooth implements IactionBluetoothListener {

    @Override
    public void actionFoundBluetoothDevice(BluetoothDevice bluetoothDevice) {
      LogUtils.e("actionFoundBluetoothDevice==1===");
      if (null == bluetoothDevice || null == bluetoothDevice.getAddress()) {
        return;
      }
      LogUtils
          .e("actionFoundBluetoothDevice=====" + bluetoothDevice.getName() + "," + bluetoothDevice
              .getAddress());
      Toast.makeText(MainActivity.this, bluetoothDevice.getAddress(), Toast.LENGTH_SHORT).show();

      if (devices.indexOf(bluetoothDevice) == -1) {
        devices.add(bluetoothDevice);
      }else {
        LogUtils.e("数据存在重复----");
      }
      recyclerViewAdapter.setList(devices);
      recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void actionBondedDeviceStateChanged(BluetoothDevice bluetoothDevice) {
      if (null == bluetoothDevice || null == bluetoothDevice.getAddress()) {
        return;
      }
      LogUtils.e("actionBondedDeviceStateChanged=====" + bluetoothDevice.getName() + ","
          + bluetoothDevice.getAddress());
      if (BluetoothDevice.BOND_BONDED == bluetoothDevice.getBondState()) {
        //已经配对
        Toast.makeText(MainActivity.this, "已经配对", Toast.LENGTH_SHORT).show();
      }

    }

    @Override
    public void actionNameStateChanged(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void actionDiscoveryStart(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void actionDiscoveryFinish(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void actionLocalNameChanged(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void actionStateChanged(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void actionOther(Context context, Intent intent) {

    }
  }


  @Override
  public void onRefresh() {

  }

  @Override
  public void onItemClick(View view, int postion) {

  }

  @Override
  protected void onDestroy() {
    if (mBroadcastReceiver != null) {
      bluetoohHelper.unregisterBluetoothReceiver(this, mBroadcastReceiver);
    }
    //停止搜索
    bluetoohHelper.cancelDiscovery();
    super.onDestroy();
  }
}
