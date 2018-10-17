package com.zme.zlibrary.thirdparty.login;

import android.content.Context;
import android.content.Intent;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Description ：LoginManager
 * Author：ZME
 * Create Time ：2018/10/17 20:14
 * Modify Time：2018/10/17 20:14
 * Version：1.0
 */
public class LoginManager {

    public enum Type{
        QQ,
        WEIXIN,
        WEIBO
    }

    private Context mContext;
    private OnLoginListener onLoginListener;

    private QQLogin qqLogin;
    private Type type;


    public LoginManager(Context mContext,OnLoginListener listener) {
        this.mContext = mContext;
        this.onLoginListener = listener;
    }



    public void login(Type type){

        this.type = type;

        if (type == Type.QQ){
            qqLogin = new QQLogin(mContext,"222222");
            qqLogin.login(new MyIUiListener(type,onLoginListener));
        }

    }


    public void  logout(){

        if (type == Type.QQ){
            if (qqLogin == null){
                return;
            }
            qqLogin.logout();
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (qqLogin == null){

            return;
        }

        qqLogin.onActivityResult(requestCode, resultCode, data, new MyIUiListener(type,onLoginListener));
    }



    private class MyIUiListener implements IUiListener{

        private OnLoginListener onLoginListener;

        private Type type;


        public MyIUiListener(Type type ,OnLoginListener onLoginListener) {
            this.onLoginListener = onLoginListener;
            this.type = type;
        }

        @Override
        public void onComplete(Object o) {

            if (type == Type.QQ){
                qqLogin.getUserInfo(o,new MyIUiListener(type,onLoginListener));
            } else {
                if (onLoginListener != null){
                    onLoginListener.onSuccess(o);
                }
            }
        }

        @Override
        public void onError(UiError uiError) {

            if (onLoginListener != null){
                onLoginListener.onError(new LoginErrorException(
                        uiError.errorMessage,
                        uiError.errorCode,
                        uiError.errorDetail));
            }
        }

        @Override
        public void onCancel() {
            if (onLoginListener != null){
                onLoginListener.onCancel();
            }

        }


    }








}
