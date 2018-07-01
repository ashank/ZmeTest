package com.zme.zlibrary.base;

/**
 * Description ：AbstractPresenter
 * Author：ZME
 * Create Time ：2018/6/30 22:10
 * Modify Time：2018/6/30 22:10
 * Version：1.0
 */
public abstract class AbstractListPresenter implements BasePresenter {

    protected int pageIndex;

    private BaseListView mBaseView;

    public AbstractListPresenter(BaseListView mBaseView) {
        this.mBaseView = mBaseView;
    }

    public  void  onRefresh(){
        pageIndex=0;
    }


    public  void onLoadMore(){
        pageIndex++;
    }


    public int getPageIndex() {
        return pageIndex;
    }
}
