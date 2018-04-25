package com.zme.zlibrary.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * **********************************************************
 *
 * @author zhiyahan
 * @version V1.0.0
 * @ClassName: SharedPreferencesManager
 * @Description: 保存数据到haredPreferences管理类
 * @date 2018-1-12 下午3:42:25 *********************************************************
 */
@SuppressLint("CommitPrefEdits")
public class SharedPreferencesManager {

  private SharedPreferences sp;

  private SharedPreferences.Editor editor;

  public static final long DEFUALT_LONG_VALUES = 1L;

  private static final int DEFUALT_INTEGER_VALUES = 0;

  private static final String PRES_NAME = "HUANLV";

  public static final long VERSION_INTERNAL = 86400000L;

  private volatile static SharedPreferencesManager sharedPreferencesManager;


  public SharedPreferencesManager(Context context, String shareName) {
    sp = context.getSharedPreferences(shareName, Context.MODE_PRIVATE);
    editor = sp.edit();
  }

  /**
   * 单例
   *
   * @param shareName 要保存数据的文件名字
   * @return 返回 SharedPreferencesManager的实例
   */
  public static SharedPreferencesManager getInstance(Context context, String shareName) {
    if (sharedPreferencesManager == null) {
      synchronized (SharedPreferencesManager.class) {
        if (sharedPreferencesManager == null) {
          sharedPreferencesManager = new SharedPreferencesManager(context.getApplicationContext(),
              shareName);
        }
      }
    }
    return sharedPreferencesManager;
  }

  /**
   * @param @param key
   * @param @param value
   * @return void
   * @Title: putStringValue
   * @Description: 以键值对的形式保存字符串
   * @author zhiyahan
   * @date 2018-1-8 下午4:12:04
   * @History: author time version desc
   */
  public void putStringValue(String key, String value) {
    editor = sp.edit();
    editor.putString(key, value);
    editor.commit();
  }

  /**
   * @param @param key
   * @param @param value
   * @return void
   * @Title: putBooleanValue
   * @Description: 以键值对的形式保存boolean
   * @author zhiyahan
   * @date 2018-1-8 下午4:12:04
   * @History: author time version desc
   */
  public void putBooleanValue(String key, Boolean value) {
    editor = sp.edit();
    editor.putBoolean(key, value);
    editor.commit();
  }

  /**
   * @param @param key
   * @param @param value
   * @return void
   * @Title: putIntegerValue
   * @Description: 以键值对的形式保存Integer
   * @author zhiyahan
   * @date 2018-1-8 下午4:15:48
   * @History: author time version desc
   */
  public void putIntegerValue(String key, Integer value) {
    editor = sp.edit();
    if (value == null) {
      value = DEFUALT_INTEGER_VALUES;
    }
    editor.putInt(key, value);
    editor.commit();
  }

  /**
   * @param @param key
   * @param @param value
   * @return void
   * @Title: putLongValue
   * @Description: 以键值对的形式保存Long
   * @author zhiyahan
   * @date 2018-1-8 下午4:15:48
   * @History: author time version desc
   */
  public void putLongValue(String key, Long value) {
    editor = sp.edit();
    if (value == null) {
      value = DEFUALT_LONG_VALUES;
    }
    editor.putLong(key, value);
    editor.commit();
  }

  /**
   * @param @param key
   * @return Boolean
   * @Title: getBooleanValue
   * @Description: 获取Boolean数据
   * @author zhiyahan
   * @date 2013-1-9 上午9:38:35
   * @History: author time version desc
   */
  public Boolean getBooleanValue(String key) {
    return sp.getBoolean(key, false);
  }

  /**
   * @param @param key
   * @return String
   * @Title: getStringValue
   * @Description: 获取String数据
   * @author zhiyahan
   * @date 2013-1-9 上午9:39:10
   * @History: author time version desc
   */
  public String getStringValue(String key) {
    return sp.getString(key, "");
  }

  /**
   * @param @param key
   * @return Integer
   * @Title: getIntegerValue
   * @Description: 获取Integer数据
   * @author zhiyahan
   * @date 2013-1-9 上午9:39:24
   * @History: author time version desc
   */
  public int getIntegerValue(String key) {
    return sp.getInt(key, DEFUALT_INTEGER_VALUES);
  }

  /**
   * @param @param key
   * @return Long
   * @Title: getLongValue
   * @Description: 获取Long数据
   * @author zhiyahan
   * @date 2013-1-9 上午9:40:28
   * @History: author time version desc
   */
  public long getLongValue(String key) {
    return sp.getLong(key, DEFUALT_LONG_VALUES);
  }

  /**
   * 是否含有某个Key
   */
  public boolean isContainsKey(String key) {
    return sp.contains(key);
  }


  public void clearPreference() {
    editor = sp.edit();
    editor.clear();
    editor.commit();
  }
}
