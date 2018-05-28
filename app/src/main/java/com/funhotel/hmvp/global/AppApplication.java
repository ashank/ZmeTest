package com.funhotel.hmvp.global;


import com.funhotel.hmvp.gen.DaoMaster;
import com.funhotel.hmvp.gen.DaoSession;
import com.zme.zlibrary.base.BaseApplication;

/**
 * CLASS ： AppApplication
 * Author : zhiyahan
 * TIME : 2017/3/24 16:11
 */

public class AppApplication extends BaseApplication {


    private DaoSession daoSession;
    private static AppApplication appApplication;

    @Override
    public void onCreate() {
        appApplication = this;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(),
                "dadaynew.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        super.onCreate();
    }

    /**
     * 单例模式
     *
     * @return 返回一个AppApplication 实例
     */
    public static AppApplication newInstance() {
        return appApplication;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
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
