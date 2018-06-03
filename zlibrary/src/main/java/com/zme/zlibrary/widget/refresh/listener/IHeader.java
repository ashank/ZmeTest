package com.zme.zlibrary.widget.refresh.listener;

/**
 * Description ：头部视图接口
 * Author：ZME
 * Create Time ：2018/6/2 21:18
 * Modify Time：2018/6/2 21:18
 * Version：1.0
 */
public interface IHeader {

    enum PullStatus {
        DEFAULT,//默认状态，拉动之前的状态
        REFRESH_TRIGGER,//下拉中，达到触发刷新状态
        REFRESH_NOT_TRIGGER,//下拉中，未达到触发状态
        REFRESHING_SCROLLING,//刷新中，用户的下拉状态
        REFRESHING,//正在刷新中
        REFRESH_FINISH,//刷新完成后，回到默认状态中
    }

    /**
     * 刷新前(下拉中，但未达到触发刷新的高度
     */

    void onRefreshNotTrigger(int scrollY);

    /**
     * 松开刷新(下拉中，到达有效刷新的距离)
     */
    void onRefreshTrigger(int scrollY);

    /**
     * 正在刷新中
     */
    void onRefreshing(int scrollY);

    /**
     * 正在刷新中时，用户在操作下拉滚动的状态
     */
    void onRefreshingScrolling(int scrollY);

    /**
     * 刷新完成后
     */
    void onRefreshFinish(int scrollY, boolean isRefreshSuccess);


}
