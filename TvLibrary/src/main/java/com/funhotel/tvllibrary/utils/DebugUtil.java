package com.funhotel.tvllibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ClassName：DebugUtil
 * @Description： 打印Log信息的类
 */
public class DebugUtil {

    /**
     * 打印信息的开关
     */
    public static final boolean DEBUG = true;
    private static final String tag = "HuanLv";
    private static Toast mToast;
    private static TextView tvToast;
    private static Handler mHandler = new Handler();

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
     * @param context
     * @param content
     * @return void
     * @Title: toast
     * @Description: TODO 利用Toast来弹出信息
     */
    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    /**
     * @param context
     * @param content
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
//        mHandler.postDelayed(r, 2000);
//		mHandler.post(sr);
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }


    /**
     * @param msg
     * @return void
     * @throws
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
     * @param msg
     * @return void
     * @throws
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
     * @param msg
     * @return void
     * @throws
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
     * @param msg
     * @return void
     * @throws
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
     * @param msg
     * @return void
     * @throws
     * @Title: e
     * @Description: TODO  Error
     */
    public static void e(String msg) {
        if (DEBUG) {
            if (msg != null) {
                Log.e(tag, msg);
            } else {
                Log.e(tag, "emsg的值为null");
            }
        }
    }
}
