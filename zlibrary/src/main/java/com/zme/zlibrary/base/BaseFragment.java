package com.zme.zlibrary.base;

import android.support.v4.app.Fragment;

/**
 * Description ：BaseFragment
 * Author：ZME
 * Create Time ：2018/7/1 16:53
 * Modify Time：2018/7/1 16:53
 * Version：1.0
 */
public abstract  class BaseFragment extends Fragment {
    protected boolean isVisible;//标记当前Fragment是否被用户可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){//判断用户是否可见当前Fragment
            isVisible=true;
            onVisible();
        }else{
            isVisible=false;
            onInvisible();
        }
    }

    private void onVisible(){
        onLayout();//只有在用户可见的情况下我们才去加载数据
    }

    private void onInvisible(){
        //用户不可见的情况下不去加载任何的数据

    }
    protected abstract void onLayout();//LazyLoad


}
