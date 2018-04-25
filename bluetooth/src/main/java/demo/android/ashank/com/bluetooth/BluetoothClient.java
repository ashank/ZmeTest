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
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.util.UUID;

/**
 * Description ：实现蓝牙客户端的连接
 * Author：ZME
 * Create Time ：2018/4/1 15:43
 * Modify Time：2018/4/1 15:43
 * Version：1.0
 */
public class BluetoothClient {

  private BluetoothSocket bluetoothSocket;
  private BluetoothDevice bluetoothDevice;
  private BluetoothAdapter bluetoothAdapter;

  public BluetoothClient(BluetoothDevice bluetoothDevice, BluetoothAdapter bluetoothAdapter) {
    this.bluetoothDevice = bluetoothDevice;
    this.bluetoothAdapter = bluetoothAdapter;
  }


  /**
   * 连接蓝牙服务端
   */
  public boolean connect(UUID uuid) {
    try {
      bluetoothSocket = bluetoothDevice
          .createInsecureRfcommSocketToServiceRecord(uuid);
      if (null == bluetoothSocket) {
        return false;
      }
      if (!bluetoothSocket.isConnected()) {
        bluetoothSocket.connect();
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      close();
    }
    return false;
  }

  /**
   * 发送数据
   */
  public void sendMessage() {

  }

  /**
   * 接收数据
   */
  public void receiveMessage() {

  }

  /**
   * close client socket
   */
  public void close() {
    try {
      if (null != bluetoothSocket) {
        bluetoothSocket.close();
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }

  }
}