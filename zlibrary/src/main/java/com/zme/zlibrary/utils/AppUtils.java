package com.zme.zlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zhiyahan on 2018/3/10 19:36
 * APP 相关的工具类
 */
public class AppUtils {

    private static final String DEFAULT_Url = "http://www.baidu.com";

    /**
     * @param context Context
     * @return 返回apk的版本名
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = getPackageInfo(context).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * @param context Context
     * @return 返回apk的版本号
     */
    public static int getVersionCode(Context context) {
        int verCode = 0;
        try {
            verCode = getPackageInfo(context).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verCode;
    }

    /**
     * @return 返回包名
     * @Title: getAppPackageName
     * @Description: TODO  获取APP包名
     */
    public static String getAppPackageName(Context context) {
        String packageName = "";
        try {
            packageName = getPackageInfo(context).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }

    /**
     * @param context Context
     * @return  PackageInfo
     * @throws PackageManager.NameNotFoundException
     */
    private static PackageInfo getPackageInfo(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packageInfo;
    }

    /**
     * 重启APP
     *
     * @param mContext
     */
    public static void resetProgram(Context mContext) {
        Intent i = ((ContextWrapper) mContext)
                .getBaseContext()
                .getPackageManager()
                .getLaunchIntentForPackage(((ContextWrapper) mContext).getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(i);
        ((Activity) mContext).finish();
        System.exit(0);
    }


    /**
     * 跳转到应用商城
     * @param context
     */
    public static void jupeToAppStore(Context context) {
        try {
            Intent intent = getIntent(context);
            boolean b = judge(context, intent);
            if (b) {
                context.startActivity(intent);
            } else {
                //若无应用市场，则跳转到某个应用市场
                Uri uri = Uri.parse(DEFAULT_Url);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        } catch (Exception e) {

        }
    }
    /**
     * @param paramContext
     * @return 获得市场的Intent
     */
    private static Intent getIntent(Context paramContext) {
        StringBuilder localStringBuilder = new StringBuilder().append("market://details?id=");
        String str = paramContext.getPackageName();
        localStringBuilder.append(str);
        Uri localUri = Uri.parse(localStringBuilder.toString());
        return new Intent("android.intent.action.VIEW", localUri);
    }

    private static boolean judge(Context paramContext, Intent paramIntent) {
        List<ResolveInfo> localList = paramContext.getPackageManager().queryIntentActivities(paramIntent,
                PackageManager.GET_META_DATA);
        if ((localList != null) && (localList.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param context
     * @param packageName
     * @return List<ResolveInfo>  返回apk的信息
     * @Title: findActivitiesForPackage
     * @Description: TODO  获得未安装的apk的各种信息
     */
    public List<ResolveInfo> findActivitiesForPackage(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mainIntent.setPackage(packageName);
        final List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0);
        return apps != null ? apps : new ArrayList<ResolveInfo>();
    }


}
