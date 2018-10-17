package com.zme.zlibrary.thirdparty.share;

/**
 * Description ：OnLoginListener
 * Author：ZME
 * Create Time ：2018/10/17 20:35
 * Modify Time：2018/10/17 20:35
 * Version：1.0
 */
public interface OnShareListener {


    void onSuccess(Object o);

    void onCancel();

    void onError(ShareException e);

}
