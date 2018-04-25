package com.zme.zlibrary.utils;

import android.content.Context;
import android.os.StrictMode;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * 作者：zhiyahan on 2018/3/10 10:48
 * <p>
 * 设备信息工具类,包括设备IP、MAC、
 */
public class DeviceUtils {

    private static TelephonyManager telephonyManager;

    /**
     * @param context
     * @return 返回 设备ID
     */
    public static String getDeviceId(Context context) {
        String deviceToken = "";
        String androidId;
        try {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (null == telephonyManager) {
                deviceToken = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } else {
                deviceToken = telephonyManager.getDeviceId();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return deviceToken;
    }

    /**
     * 获取设备MAC地址，兼容6.0及以下系统；
     * @return MAC地址
     */
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
            ex.printStackTrace();
        }
        return "mac is null";
    }


    /**
     * 启动StrictMode模式
     * -------------------
     * StrictMode类可以用于捕捉发生在应用程序主线程中耗时的磁盘、网络访问或函数调用，
     * 可以帮助开发者使其改进程序，使主线程处理UI和动画在磁盘读写和网络操作时变得更平滑，
     * 避免主线程被阻塞，导致ANR窗口的发生。
     * 放在Application中程序启动就可以监控一切了
     * ----------------------
     */
    public static void setStrictMode() {
        // TODO Auto-generated method stub
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

}
