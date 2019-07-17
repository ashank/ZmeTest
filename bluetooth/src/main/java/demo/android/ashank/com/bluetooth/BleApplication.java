package demo.android.ashank.com.bluetooth;

import android.content.Context;
import android.support.multidex.MultiDex;
import com.zme.zlibrary.base.BaseApplication;

/**
 * CLASS ： AppApplication
 * Author : zhiyahan
 * TIME : 2017/3/24 16:11
 */

public class BleApplication extends BaseApplication {


    private static BleApplication appApplication;

    @Override
    public void onCreate() {
        appApplication = this;
        super.onCreate();
    }

    /**
     * 单例模式
     *
     * @return 返回一个AppApplication 实例
     */
    public static BleApplication newInstance() {
        return appApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }
}
