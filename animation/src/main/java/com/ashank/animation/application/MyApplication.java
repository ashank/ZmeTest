package com.ashank.animation.application;

import com.alipay.euler.andfix.patch.PatchManager;
//import com.antfortune.freeline.FreelineCore;
import com.zme.zlibrary.base.BaseApplication;

/**
 * CLASS ： MyApplication
 * Author : zhiyahan
 * TIME : 2017/6/25 21:35
 */

public class MyApplication extends BaseApplication {

    PatchManager patchManager;
    private static final String APATCH_PATH = "/out.apatch";

    @Override
    public void onCreate() {
        super.onCreate();
//        FreelineCore.init(this);

        //热修复
//      String appversion= getgetPackageManager().getPackageInfo(getPackageName(),0).versionName;
//        patchManager = new PatchManager(this);
//        patchManager.init("1.0");//current version
//        patchManager.loadPatch();

        // add patch at runtime /storage/emulated/0/out.apatch
//        try {
//            // .apatch file path
//            String patchFileString = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + APATCH_PATH;
//            patchManager.addPatch(patchFileString);
//            Log.d("Huanlv", "apatch:" + patchFileString + " added.");
//        } catch (IOException e) {
//            Log.e("Huanlv", "Huanlv", e);
//        }
    }
}
