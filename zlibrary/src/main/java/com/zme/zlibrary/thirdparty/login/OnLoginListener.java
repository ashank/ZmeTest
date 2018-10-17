package com.zme.zlibrary.thirdparty.login;

/**
 * Description ：OnLoginListener
 * Author：ZME
 * Create Time ：2018/10/17 20:35
 * Modify Time：2018/10/17 20:35
 * Version：1.0
 */
public interface OnLoginListener {


    void onStart();

    void onSuccess(Object o);

    void onCancel();

    void onError(LoginErrorException e);

}
