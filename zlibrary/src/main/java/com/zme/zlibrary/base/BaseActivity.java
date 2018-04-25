package com.zme.zlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Base
 */
public class BaseActivity extends AppCompatActivity {


  public void gotoActivity(Intent intent, Activity activity) {
    intent.setClass(this, activity.getClass());
  }
}
