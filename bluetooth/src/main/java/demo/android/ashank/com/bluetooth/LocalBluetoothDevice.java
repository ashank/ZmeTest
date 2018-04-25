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

/**
 * Description ：本地蓝牙设备的信息
 * Author：ZME
 * Create Time ：2018/3/31 19:48
 * Modify Time：2018/3/31 19:48
 * Version：1.0
 */
public class LocalBluetoothDevice {

  private String name;
  private String address;
  /**
   * STATE_OFF            //蓝牙已关闭
   * STATE_TURNING_ON     //蓝牙正在打开
   * STATE_ON             //蓝牙已打开
   * STATE_TURNING_OFF    //蓝牙正在关闭
   */
  private int state;

  /**
   * SCAN_MODE_NONE                       //即不能连接设备,也不能被发现
   * CAN_MODE_CONNECTABLE                //能连接远程设备,但不能被远程设备发现
   * SCAN_MODE_CONNECTABLE_DISCOVERABLE  //即连接设备,也能被发现.(通常情况下都是此模式.)
   */
  private int scanMode;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getScanMode() {
    return scanMode;
  }

  public void setScanMode(int scanMode) {
    this.scanMode = scanMode;
  }
}
