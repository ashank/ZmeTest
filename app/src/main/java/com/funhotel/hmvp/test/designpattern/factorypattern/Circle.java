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

package com.funhotel.hmvp.test.designpattern.factorypattern;

import android.util.Log;

/**
 * Description ：Circle
 * Author：ZME
 * Create Time ：2018/3/28 16:25
 * Modify Time：2018/3/28 16:25
 * Version：1.0
 */
public class Circle implements Shape {

  private String tag = this.getClass().getName();

  @Override
  public void draw() {
    Log.d(tag, "画个圈圈诅咒你");
  }
}
