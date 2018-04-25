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

package com.funhotel.hmvp.test.thread;

/**
 * Description ：Goods
 * Author：ZME
 * Create Time ：2018/3/29 00:47
 * Modify Time：2018/3/29 00:47
 * Version：1.0
 */
public class Goods {

  private String pinpai;
  private String name;

  private int num;

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getPinpai() {
    return pinpai;
  }

  public void setPinpai(String pinpai) {
    this.pinpai = pinpai;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
