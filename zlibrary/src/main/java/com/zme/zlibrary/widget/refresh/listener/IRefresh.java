package com.zme.zlibrary.widget.refresh.listener;

/**
 * Description ：IRefresh
 * Author：ZME
 * Create Time ：2018/6/3 17:01
 * Modify Time：2018/6/3 17:01
 * Version：1.0
 */
public interface IRefresh {


    void onRefreshFinish();

    void onAutoRefresh();

    void startRefresh();

    void setEnableRefresh();


}
