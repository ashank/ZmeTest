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

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * Description ：蓝牙动作监听器
 * Author：ZME
 * Create Time ：2018/3/31 23:04
 * Modify Time：2018/3/31 23:04
 * Version：1.0
 */
public interface IactionBluetoothListener {

  public void actionFoundBluetoothDevice(BluetoothDevice bluetoothDevice);

  public void actionStateChanged(BluetoothDevice bluetoothDevice);

  public void actionBondedDeviceStateChanged(BluetoothDevice bluetoothDevice);

  public void actionNameStateChanged(BluetoothDevice bluetoothDevice);

  public void actionDiscoveryStart(BluetoothDevice bluetoothDevice);

  public void actionDiscoveryFinish(BluetoothDevice bluetoothDevice);

  public void actionLocalNameChanged(BluetoothDevice bluetoothDevice);

  public void actionOther(Context context, Intent intent);
}
