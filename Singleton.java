
/*
 *  Copyright (C) 2018  ZME
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.funhotel.hmvp.test.designpattern;


import android.util.Log;

public class Singleton {

  private String tag = this.getClass().getName();
  private static volatile Singleton SINGLETON;
  private String toast;


  private Singleton(String toast) {
    this.toast = toast;
  }

  /**
   * @param toast 文本内容
   * @return 返回  Singleton 单例
   */
  public static Singleton getSINGLETON(String toast) {
    if (SINGLETON == null) {
      synchronized (Singleton.class) {
        if (SINGLETON == null) {
          SINGLETON = new Singleton(toast);
        }
      }
    }
    return SINGLETON;
  }

  public void  showToast() {
    Log.d(tag,toast);
  }




}
