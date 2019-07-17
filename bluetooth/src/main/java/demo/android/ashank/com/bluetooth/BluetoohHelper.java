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

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.zme.zlibrary.utils.DeviceUtils;
import com.zme.zlibrary.utils.LogUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Description ：蓝牙功能实现的帮助类，通过本类可以实现打开、关闭蓝牙、是否支持蓝牙，获取已经连接的蓝牙数据、扫描蓝牙等功能
 * Author：ZME
 * Create Time ：2018/3/31 19:58
 * Modify Time：2018/3/31 19:58
 * Version：1.0
 */
public class BluetoohHelper {

  private static volatile BluetoohHelper HELPER;
  private BluetoothManager bluetoothManager;
  private BluetoothAdapter bluetoothAdapter;

  /**
   * 获取 BluetoohHelper 实例
   *
   * @param context {@link Context}
   * @return BluetoohHelper
   */
  public static BluetoohHelper getInstance(Context context) {
    if (null == HELPER) {
      synchronized (BluetoohHelper.class) {
        if (null == HELPER) {
          HELPER = new BluetoohHelper(context);
        }
      }
    }
    return HELPER;
  }

  private BluetoohHelper(Context context) {

    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      //api<=17
      bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      //api>17
      bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
      bluetoothAdapter = bluetoothManager.getAdapter();
    }
  }


  public BluetoothAdapter getBluetoothAdapter() {
    return bluetoothAdapter;
  }


  public boolean isSupportBluetooth() {
    if (null == bluetoothAdapter) {
      return false;
    }
    return true;
  }


  public boolean openBluetooth() {
    if (null == bluetoothAdapter) {
      throw new NullPointerException("BluetoothAdapter is null");
    }

    return isSupportBluetooth() && (bluetoothAdapter.isEnabled() || bluetoothAdapter.enable());
  }


  public boolean closeBluetooth() {
    if (null == bluetoothAdapter) {
      throw new NullPointerException("BluetoothAdapter is null");
    }
    return isSupportBluetooth() && (!bluetoothAdapter.isEnabled() || bluetoothAdapter.disable());
  }

  /**
   * 开始扫描蓝牙，若蓝牙已经在扫描中，则不会重启扫描动作；
   *
   * @return 扫描开始，则返回true ，否则，返回false
   */

  public boolean startDiscovery() {
    if (null == bluetoothAdapter) {
      throw new NullPointerException("BluetoothAdapter is null");
    }
    return !bluetoothAdapter.isDiscovering() && bluetoothAdapter.startDiscovery();
  }

  /**
   * 取消扫描蓝牙，若蓝牙没有在扫描中，没有必要取消扫描蓝牙的动作；
   *
   * @return 取消扫描开始，则返回true ，否则，返回false
   */

  public boolean cancelDiscovery() {
    if (null == bluetoothAdapter) {
      throw new NullPointerException("BluetoothAdapter is null");
    }
    return bluetoothAdapter.isDiscovering() && bluetoothAdapter.cancelDiscovery();
  }

  /**
   * 返回本机蓝牙设备的信息
   *
   * <p>
   * 获取设备的mac地址时，由于6.0开始，安卓系统对用户提供更加严格的数据保护，
   * 无法使用旧的方式获取mac地址，这里可以在stackoverflow上关注相关问题
   * <p/>
   *
   * @return {@link LocalBluetoothDevice}
   */

  public LocalBluetoothDevice getLocalDevice() {
    if (null == bluetoothAdapter) {
      throw new NullPointerException("BluetoothAdapter is null");
    }
    LocalBluetoothDevice device = new LocalBluetoothDevice();
    device.setName(bluetoothAdapter.getName());
    device.setAddress(DeviceUtils.getMacAddr());
    device.setState(bluetoothAdapter.getState());
    device.setScanMode(bluetoothAdapter.getScanMode());

    return device;
  }

  /**
   * 转化设备的状态
   * @param status 设备连接的状态
   * @return 返回设备的中文状态
   */
  public String bluetoothStatus(int status) {
    String a = "未知状态";
    switch (status) {
      case BluetoothDevice.BOND_BONDING:
        a = "连接中";
        break;
      case BluetoothDevice.BOND_BONDED:
        a = "连接完成";
        break;
      case BluetoothDevice.BOND_NONE:
        a = "未连接/取消连接";
        break;
    }
    return a;
  }

  /**
   * 设置本地蓝牙为可见，方便对方手机发现自己的蓝牙，以便对接
   *
   * @param context {@link Context}
   * @param visibleTime 可见时间，默认120s，最长是300s
   */

  public void setUpBluetoothVisible(Context context, int visibleTime) {
    if (null == bluetoothAdapter) {
      throw new NullPointerException("BluetoothAdapter is null");
    }
    if (bluetoothAdapter.isEnabled()) {
      if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, visibleTime);
        context.startActivity(discoverableIntent);
      }
    }

  }

  /**
   * 蓝牙连接后，获取已经连接过的设备
   *
   * @return 返回已经连接的蓝牙设备集合
   */

  public Set<BluetoothDevice> getBondedDevices() {
    if (null == bluetoothAdapter) {
      throw new NullPointerException("BluetoothAdapter is null");
    }
    return bluetoothAdapter.getBondedDevices();
  }

  /**
   * 蓝牙连接后，获取已经连接过的设备
   *
   * @return 返回LinkedList集合
   */
  public List<BluetoothDevice> getBondedDevicesList() {
    List<BluetoothDevice> bluetoothDeviceList = new LinkedList<>();
    Set<BluetoothDevice> deviceSet = getBondedDevices();
    if (null == deviceSet || deviceSet.isEmpty()) {
      return null;
    }
    for (BluetoothDevice bluetoothDevice : deviceSet) {
      bluetoothDeviceList.add(bluetoothDevice);
    }
    return bluetoothDeviceList;
  }


  /**
   * 处理蓝牙对应的动作
   * <p>
   * 在新版本中，扫描蓝牙的结果使用广播的形式返回，在处理的过程中，根据广播的动作来处理对应的数据的功能
   * </p>
   */
  public void actionBluetooth(Context context, Intent intent,
      IactionBluetoothListener iactionBluetoothListener) {
    String action = intent.getAction();
    BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    LogUtils.e("actionBluetooth=====" + action);
    if (BluetoothDevice.ACTION_FOUND.equals(action)) {
      //扫描到设备
      iactionBluetoothListener.actionFoundBluetoothDevice(scanDevice);
    } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
      //设备的状态发生改变
      iactionBluetoothListener.actionStateChanged(scanDevice);
    } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
      //已经连接的设备的状态发生改变
      iactionBluetoothListener.actionBondedDeviceStateChanged(scanDevice);
    } else if (BluetoothDevice.ACTION_NAME_CHANGED.equals(action)) {
      //名字发生改变
      iactionBluetoothListener.actionNameStateChanged(scanDevice);
    } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
      //开始扫描
      iactionBluetoothListener.actionDiscoveryStart(scanDevice);
    } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
      //结束扫描
      iactionBluetoothListener.actionDiscoveryFinish(scanDevice);
    } else if (BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED.equals(action)) {
      //设备名字发生改变
      iactionBluetoothListener.actionLocalNameChanged(scanDevice);
    }else  if (BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)){
      //扫描模式发生改变
     /* int SCAN_MODE_NONE = 20;//这个模式不能被发现也不能连接
      int SCAN_MODE_CONNECTABLE = 21;//这个模式不能被扫描到，但是可以连接
      int SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23;//这个模式可以被发现，也能被连接*/

    } else {
      iactionBluetoothListener.actionOther(context, intent);
    }
  }

  /**
   * 注册蓝牙动作广播
   */
  public void registerBluetoothReceiver(Context context, BroadcastReceiver broadcastReceiver) {
    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
    filter.addAction(BluetoothDevice.ACTION_FOUND);
    filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
    context.registerReceiver(broadcastReceiver, filter);
  }


  /**
   * 解除蓝牙广播
   *
   * @param context {@link Context}
   * @param broadcastReceiver {@link BroadcastReceiver}
   */
  public void unregisterBluetoothReceiver(Context context, BroadcastReceiver broadcastReceiver) {
    context.unregisterReceiver(broadcastReceiver);
  }


  /**
   * 弹出一个对话框,提示用户是否允许打开蓝牙
   *
   * @param context {@link Context}
   */
  public void showOpenBlueToothDialog(Context context) {
    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    context.startActivity(enableBtIntent);
  }

}
