package com.zme.zlibrary.widget.refresh;

/**
 * Description ：拉动的时候的状态
 * Author：ZME
 * Create Time ：2018/6/3 14:37
 * Modify Time：2018/6/3 14:37
 * Version：1.0
 */
public enum PullStatus {

    DEFAULT,//默认状态，拉动之前的状态
    REFRESH_TRIGGER,//下拉中，达到触发刷新状态
    REFRESH_NOT_TRIGGER,//下拉中，未达到触发状态
    REFRESH_SCROLLING,//放手后，开始刷新前，回到刷新的位置中
    REFRESHING,//正在刷新中
    REFRESH_FINISH,//刷新完成后，回到默认状态中
}
