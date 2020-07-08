package com.zme.zlibrary.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存数据到haredPreferences管理类
 *
 * @author zhiyahan
 * @version V1.0.0
 * @date 2019-7-12 下午3:42:25
 */
@SuppressLint("CommitPrefEdits")
public class SharedPreferencesManager {

  private SharedPreferences sp;

  private SharedPreferences.Editor editor;

  public static final long DEFAULT_LONG_VALUES = 1L;

  private static final int DEFAULT_INTEGER_VALUES = 0;

  private static final float DEFAULT_FLOAT_VALUES = 0.0f;

  public static final long VERSION_INTERNAL = 86400000L;

  private volatile static SharedPreferencesManager sharedPreferencesManager;

  private SharedPreferencesManager(Context context, String shareName) {
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
   * 以键值对的形式保存字符串
   * @param  key 键名
   * @param  value  键值
   */
  public void putStringValue(String key, String value) {
    editor = sp.edit();
    editor.putString(key, value);
    editor.apply();
  }

  /**
   * 以键值对的形式保存boolean
   * @param  key 键名
   * @param  value  键值
   */
  public void putBooleanValue(String key, Boolean value) {
    editor = sp.edit();
    editor.putBoolean(key, value);
    editor.apply();
  }

  /**
   * 以键值对的形式保存Integer
   * @param  key 键名
   * @param  value  键值
   * */
  public void putIntegerValue(String key, Integer value) {
    editor = sp.edit();
    if (value == null) {
      value = DEFAULT_INTEGER_VALUES;
    }
    editor.putInt(key, value);
    editor.apply();
  }

  /**
   * 以键值对的形式保存Long
   * @param  key 键名
   * @param  value  键值
   */
  public void putLongValue(String key, Long value) {
    editor = sp.edit();
    if (value == null) {
      value = DEFAULT_LONG_VALUES;
    }
    editor.putLong(key, value);
    editor.apply();
  }



  /**
   * 以键值对的形式保存Float
   * @param  key 键名
   * @param  value  键值
   */
  public void putFloatValue(String key, float value) {
    editor = sp.edit();
    if (value == 0) {
      value = DEFAULT_FLOAT_VALUES;
    }
    editor.putFloat(key, value);
    editor.apply();
  }



  /**
   * @param key 键值名
   * @return Boolean
   */
  public Boolean getBooleanValue(String key) {
    return sp.getBoolean(key, false);
  }

  /**
   * 获取String数据
   * @param  key 键名
   * @return String
   */
  public String getStringValue(String key) {
    return sp.getString(key, "");
  }

  /**
   * 获取Integer数据
   * @param  key 键名
   * @return Integer
   */
  public int getIntegerValue(String key) {
    return sp.getInt(key, DEFAULT_INTEGER_VALUES);
  }

  /**
   * 获取Long数据
   * @param  key 键名
   * @return Long
   */
  public long getLongValue(String key) {
    return sp.getLong(key, DEFAULT_LONG_VALUES);
  }

  /**
   * 获取Float数据
   * @param  key 键名
   * @return Long
   */
  public float getFloatValue(String key) {
    return sp.getFloat(key, DEFAULT_FLOAT_VALUES);
  }

  /**
   * 是否含有某个Key
   */
  public boolean isContainsKey(String key) {
    return sp.contains(key);
  }


  /**
   * 清除整个sp文件的缓存
   */
  public void clearPreference() {
    editor = sp.edit();
    editor.clear();
    editor.apply();
  }
}
