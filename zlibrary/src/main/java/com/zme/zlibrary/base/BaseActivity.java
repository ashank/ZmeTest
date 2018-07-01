package com.zme.zlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Base
 */
public class BaseActivity extends AppCompatActivity {

  private static final String TAG = BaseActivity.class.getSimpleName();

  public void gotoActivity(Intent intent, Activity activity) {
    intent.setClass(this, activity.getClass());
  }


  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);
    Log.e(TAG, "onTrimMemory: " +level);
  }

}
