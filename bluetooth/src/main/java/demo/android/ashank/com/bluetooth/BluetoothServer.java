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
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import java.io.IOException;
import java.util.UUID;

/**
 * Description ：实现蓝牙服务端的连接
 * Author：ZME
 * Create Time ：2018/4/1 15:44
 * Modify Time：2018/4/1 15:44
 * Version：1.0
 */
public class BluetoothServer {

  private BluetoothServerSocket bluetoothServerSocket;
  private Context context;
  private BluetoothAdapter bluetoothAdapter;
  private BluetoothSocket socket;
  private boolean isStop = true;

  /**
   * 实现蓝牙连接 Server端的功能
   *
   * @param context {@link Context}
   * @param bluetoothAdapter {@link BluetoothAdapter}
   */
  public BluetoothServer(Context context, BluetoothAdapter bluetoothAdapter) {
    this.context = context;

    try {
      if (null == bluetoothAdapter) {
        return;
      }
      bluetoothServerSocket = bluetoothAdapter
          .listenUsingRfcommWithServiceRecord("", UUID.randomUUID());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 在监听socket client的连接
   */
  public void accept() {
    try {
      while (isStop) {
        socket = bluetoothServerSocket.accept();
        if (null != socket && null != iBlutoothServerSocketCallback) {
          iBlutoothServerSocketCallback.connectClientSucess(socket);
        }
        break;
      }
    } catch (IOException e) {
      e.printStackTrace();
      close();
    }
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
   * close server socket
   */
  public void close() {
    try {
      if (null != bluetoothServerSocket) {
        bluetoothServerSocket.close();
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }

  }


  private IBlutoothServerSocketCallback iBlutoothServerSocketCallback;

  public interface IBlutoothServerSocketCallback {

    void connectClientSucess(BluetoothSocket bluetoothSocket);

  }


}
