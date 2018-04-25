package com.funhotel.hmvp;

import junit.framework.TestCase;

/**
 * 作者：zhiyahan on 2018/3/26 17:01
 */
public class CalculaterTest extends TestCase {

  Calculater calculater = new Calculater();

  @org.junit.Test
  public void testAdd() throws Exception {
    int a = 1;
    int b = 2;
    int result = calculater.add(a, b);
    assertEquals(result, 4); // 验证result==3，如果不正确，测试不通过
  }

}