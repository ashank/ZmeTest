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
 * Description ：消费者
 * Author：ZME
 * Create Time ：2018/3/29 00:45
 * Modify Time：2018/3/29 00:45
 * Version：1.0
 */

public class Customer implements Runnable {

  private Goods goods;
  private int i=0;
  public Goods getGoods() {
    return goods;
  }

  public void setGoods(Goods goods) {
    this.goods = goods;
  }

  @Override
  public void run() {
    while (true) {
      synchronized (goods) {
        if (i <= 2) {
          //如果商品生产的数量小于0,则开始等待.只有有货才能购物嘛
          try {
            goods.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        goods.setNum((goods.getNum() - 1));
        System.out.println("取走了" + goods.getPinpai() + goods.getName());
        goods.notify();//取走之后通知生产商继续生产商品(唤醒在对象锁等待池中的线程继续执行)
        i--;
      }
    }
  }

}
