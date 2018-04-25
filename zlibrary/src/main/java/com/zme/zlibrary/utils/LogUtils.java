package com.zme.zlibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Zhiyahan
 * @ClassName: ParseJson
 * @Description: TODO 打印Log信息的类
 * @date 2015年8月24日 上午10:55:13
 */
public class LogUtils {

  public static final boolean DEBUG = true; // 打印信息的开关，默认是打开的
  public static final String tag = "HuanLv"; //tag的标记
  private static Toast mToast;
  private static TextView tvToast;
  private static Handler mHandler = new Handler();
  private static int originStackIndex = 2;

  private static Runnable r = new Runnable() {
    @Override
    public void run() {
      if (null != mToast) {
        mToast.cancel();
      }
    }
  };

  private static Runnable sr = new Runnable() {
    @Override
    public void run() {
      if (null != mToast) {
        mToast.show();
      }
    }
  };

  /**
   * @return void
   * @Title: toast
   * @Description: TODO 利用Toast来弹出信息
   */
  public static void toast(Context context, String content) {
    Toast.makeText(context, content, Toast.LENGTH_LONG).show();
  }

  /**
   * @return void
   * @Title: customToast
   * @Description: TODO 利用Toast来弹出信息
   */
  public static void customToast(Context context, final String content) {
//		mHandler.removeCallbacks(r);
//		mHandler.removeCallbacks(sr);
//		mHandler.removeCallbacksAndMessages(null);
//		if (null!=mToast) {
//			if (null!=tvToast) {
//				mHandler.post(new Runnable() {
//					@Override
//					public void run() {
//						tvToast.setText(content);
//					}
//				});
//			}
//		}else {
//			if (null!=context) {
//				mToast = new Toast(context);
//				View view=LayoutInflater.from(context).inflate(R.layout.view_toast,null);
//				tvToast=(TextView)view.findViewById(R.id.item_tv_title);
//				tvToast.setText(content);
//				mToast.setView(view);
//				mToast.setDuration(Toast.LENGTH_SHORT);
//				mToast.setGravity(Gravity.CENTER, 0, 0);
//			}
//		}
//    mHandler.postDelayed(r, 2000);
//		mHandler.post(sr);
    Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
  }


  /**
   * @return void
   * @Title: v
   * @Description: TODO  Verbose
   */
  public static void v(String msg) {
    if (DEBUG) {
      if (msg != null) {
        Log.v(tag, msg);
      } else {
        Log.v(tag, "vmsg的值为null");
      }
    }
  }

  /**
   * @return void
   * @Title: d
   * @Description: TODO  Debug
   */
  public static void d(String msg) {
    if (DEBUG) {
      if (msg != null) {
        Log.d(tag, msg);
      } else {
        Log.d(tag, "dmsg的值为null");
      }
    }
  }

  /**
   * @return void
   * @Title: i
   * @Description: TODO  Info
   */
  public static void i(String msg) {
    if (DEBUG) {
      if (msg != null) {
        Log.i(tag, msg);
      } else {
        Log.i(tag, "imsg的值为null");
      }
    }
  }

  /**
   * @return void
   * @Title: w
   * @Description: TODO  Warn
   */
  public static void w(String msg) {
    if (DEBUG) {
      if (msg != null) {
        Log.w(tag, msg);
      } else {
        Log.w(tag, "wmsg的值为null");
      }
    }
  }

  /**
   * @return void
   * @Title: e
   * @Description: TODO  Error
   */
  public static void e(String msg) {
    if (DEBUG) {
      if (msg != null) {
        Log.e(tag, msg);
      } else {
        Log.e(tag, "msg的值为null");
      }
    }
  }

  /**
   * 获取当前方法所在的文件名
   *
   * @return 当前方法所在的文件名
   */
  public static String getFileName() {
    return Thread.currentThread().getStackTrace()[originStackIndex].getFileName();
  }

  /**
   * 获取当前方法所在的Class名
   *
   * @return 当前方法所在的Class名
   */
  public static String getClassName() {
    return Thread.currentThread().getStackTrace()[originStackIndex].getClassName();
  }

  /**
   * 获取当前方法名
   *
   * @return 当前方法名
   */
  public static String getMethodName() {
    return Thread.currentThread().getStackTrace()[originStackIndex].getMethodName();
  }

  /**
   * 获取当前代码执行处行数
   *
   * @return 当前代码执行处行数
   */
  public static int getLineNumber() {
    return Thread.currentThread().getStackTrace()[originStackIndex].getLineNumber();
  }
}
