package com.zme.glide.demo;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

/**
 * FileName    : AppApplication
 *
 * @author : Zhiyahan
 * Date        : 2019/6/25 15:48
 * Description : 文件作用
 */
public class AppApplication extends MultiDexApplication {

    private static AppApplication appApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = this;
    }

    /**
     * 单例模式
     *
     * @return 返回一个AppApplication 实例
     */
    public static AppApplication newInstance() {
        return appApplication;
    }
}
