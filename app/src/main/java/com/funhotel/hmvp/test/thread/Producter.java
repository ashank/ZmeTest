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

import java.util.LinkedList;
import java.util.List;

/**
 * Description ：生产者
 * Author：ZME
 * Create Time ：2018/3/29 00:46
 * Modify Time：2018/3/29 00:46
 * Version：1.0
 */
public class Producter implements Runnable {


  private Goods goods;
  private  int i=0;

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

        if (goods.getNum() > 2) {
          try {
            goods.wait();//商品数量已经大于0啦,消费者要取货咯,自己就开始等待咯
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        goods.setPinpai("哇哈哈");
        goods.setName("矿泉水");
        goods.setNum((goods.getNum() + 1));
        System.out.println("生产了" + goods.getPinpai() + goods.getName());
        i++;
        goods.notify();//商品不够啦,自己生产完,然后通知消费者取货咯
      }
    }
  }

}
