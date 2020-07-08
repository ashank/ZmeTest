package com.zme.zlibrary.base;

/**
 * Description ：BaseListView
 * Author：ZME
 * Create Time ：2018/6/30 21:56
 * Modify Time：2018/6/30 21:56
 * Version：1.0
 */
public interface BaseListView extends BaseView {

    /**
     * 正在加载中的视图接口
     *
     * 用于http正在加载过程中，视图的动作
     * @param pageIndex  页数
     * @param type  类型，是刷新还是加载更多；
     */
    void  onLoadingView(int pageIndex,int type);

    /**
     * 列表加载成功的后的视图处理接口，用于加载成功后的UI处理
     * @param t Object
     * @param pageIndex  页数
     * @param type  类型，是刷新还是加载更多；
     */
    void  onLoadDataSuccess(Object t,int pageIndex,int type);


    /**
     * 列表加载失败的视图处理接口，用于加载失败后的UI处理
     * @param t  Object
     * @param pageIndex  页数
     * @param type  类型，刷新还是加载更多
     */
    void onLoadDataFailure(Object t,int pageIndex,int type);

}
