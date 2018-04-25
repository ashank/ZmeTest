package com.zme.zlibrary.commonactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * 检查权限的工具类
 */
public class PermissionsChecker {
    private final Context mContext;
    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    public String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };

    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    public PermissionsChecker(Context mContext, String[] PERMISSIONS) {
        this.mContext = mContext.getApplicationContext();
        this.PERMISSIONS = PERMISSIONS;
    }

    public void setPERMISSIONS(String[] PERMISSIONS) {
        this.PERMISSIONS = PERMISSIONS;
    }

    // 判断权限集合
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }


    /**
     * 处理权限动作，该方法在onActivityResult方法中调用
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public  void  dealWithPermission(int requestCode, int resultCode, Intent data,PermissionCallBack permissionCallBack){
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
           if (permissionCallBack!=null)
                permissionCallBack.noPermission();
           else
               ((Activity)mContext).finish();

        }else {
            //接受权限或者有权限，程序可以运行
            if (permissionCallBack!=null){
                permissionCallBack.havaPermission();
            }
        }
    }

    public  interface  PermissionCallBack{
        void havaPermission();
        void noPermission();
    }
}
