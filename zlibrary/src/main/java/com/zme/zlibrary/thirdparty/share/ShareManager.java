package com.zme.zlibrary.thirdparty.share;

import android.content.Context;
import android.content.Intent;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Description ：ShareManager
 * Author：ZME
 * Create Time ：2018/10/17 21:36
 * Modify Time：2018/10/17 21:36
 * Version：1.0
 */
public class ShareManager {


    public enum  Type{

        /**
         * QQ好友
         */
        QQ_APP,
        /**
         * QQ空间
         */
        QQ_ZONE,
        /**
         * 微博好友
         */
        WEIBO_APP,
        /**
         * 微博动态
         */
        WEIBO_DYNAMIC,
        /**
         * 微信好友
         */
        WEIXIN_APP,
        /**
         * 朋友圈
         */
        WEIXIN_MOMENTS,

    }


    private Context mContext;

    private Type type;
    private QQShare qqShare;
    private OnShareListener onShareListener;



    public ShareManager(Context mContext, OnShareListener onShareListener) {
        this.mContext = mContext;
        this.onShareListener = onShareListener;

    }


    public void share(Type type,ShareContent shareContent){
        this.type = type;
        if (type == Type.QQ_APP){
            if (qqShare == null){
                qqShare = new QQShare(mContext,"222222");
            }
            qqShare.shareToQQ(shareContent,new MyIUiListener(onShareListener));

        }else if (type == Type.QQ_ZONE){

            if (qqShare == null){
                qqShare = new QQShare(mContext,"222222");
            }
            qqShare.shareToQZone(shareContent,new MyIUiListener(onShareListener));

        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (qqShare == null){
            return;
        }

        qqShare.onActivityResult(requestCode, resultCode, data, new MyIUiListener(onShareListener));
    }


    private class MyIUiListener implements IUiListener{

        private OnShareListener onShareListener;

        public MyIUiListener(OnShareListener onShareListener) {
            this.onShareListener = onShareListener;
        }

        @Override
        public void onComplete(Object o) {

            if (onShareListener != null){
                onShareListener.onSuccess(o);
            }

        }

        @Override
        public void onError(UiError uiError) {

            if (onShareListener != null){
                onShareListener.onError(new ShareException(
                        uiError.errorMessage,
                        uiError.errorCode));
            }
        }

        @Override
        public void onCancel() {
            if (onShareListener != null){
                onShareListener.onCancel();
            }

        }
    }

}
