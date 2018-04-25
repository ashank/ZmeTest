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

package com.funhotel.hmvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.test.designpattern.Singleton;
import com.funhotel.hmvp.test.designpattern.factorypattern.Circle;
import com.funhotel.hmvp.test.designpattern.factorypattern.Shape;
import com.funhotel.hmvp.test.designpattern.factorypattern.ShapeFactory;
import com.funhotel.hmvp.test.designpattern.factorypattern.Square;

/**
 * Description ：设计模式测试页面
 * Author：ZME
 * Create Time ：2018-03-28 15 ：55
 * Modify Time：2018-03-28 15 ：55
 * Version：1.0
 */
public class DesignpatternActivity extends AppCompatActivity {

  private Singleton singleton;
  private Shape shape;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_designpattern);

    Singleton singleton1 = Singleton.getSINGLETON("我是单例模式");
    singleton1.showToast();
    Singleton singleton2 = Singleton.getSINGLETON("我想改变值试试");
    singleton2.showToast();

    ShapeFactory shapeFactory=new ShapeFactory();
    shape=new Circle();
    shapeFactory.productShape(shape);
    shape=new Square();
    shapeFactory.productShape(shape);






  }
}
