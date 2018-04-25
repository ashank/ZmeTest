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

package com.funhotel.hmvp.test.classtest;

import android.app.Fragment;

/**
 * Description ：MyUI
 * Author：ZME
 * Create Time ：2018/4/22 22:35
 * Modify Time：2018/4/22 22:35
 * Version：1.0
 */
public class MyUI<T> extends Fragment{


  My<String> ui=new My<>();


  public MyUI() {
  }

  public void setYU(T t){
    System.out.print(t);
  }

}