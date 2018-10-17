package com.zme.zlibrary.thirdparty.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/**
 * Description ：QQShare
 * Author：ZME
 * Create Time ：2018/10/17 21:14
 * Modify Time：2018/10/17 21:14
 * Version：1.0
 */
public class QQShare {


    private Context mContext;
    private Tencent mTencent;

    private static final String scope = "all";


    public QQShare(Context mContext, String appId) {
        this.mContext = mContext;
        mTencent = Tencent.createInstance(appId, mContext);
    }


    /**
     * 在某些低端机上调用登录后，由于内存紧张导致APP被系统回收，登录成功后无法成功回传数据。
     * 需要在onActivityResult中处理回掉
     *
     * 并且要实现回掉才能接收到数据
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data, IUiListener listener) {
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE ) {
            Tencent.onActivityResultData(requestCode, resultCode, data, listener);
        }
    }


    /**
     * 图文分享,也就是默认分享  SHARE_TO_QQ_TYPE_DEFAULT
     *
     * SHARE_TO_QQ_TYPE_IMAGE
     *
     * Tencent.SHARE_TO_QQ_TYPE_AUDIO
     *
     * @param listener
     *
     * 注意：
     *
     * PARAM_TITLE、PARAM_IMAGE_URL、PARAM_SUMMARY不能全为空，最少必须有一个是有值的。
     */

    public void shareToQQ(ShareContent shareContent,IUiListener listener) {

        final Bundle params = new Bundle();

        //分享类型
        params.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE, com.tencent.connect.share
                .QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        //要分享的标题
        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        //要分享的摘要
        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");
        //目标连接地址
        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");
        //图片地址
        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        //分享的app名称
        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_APP_NAME, "测试应用222222");
        //分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
        //QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
        //QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮
        params.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_EXT_INT, com.tencent.connect.share.QQShare
                .SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);

        mTencent.shareToQQ((Activity) mContext, params, listener);
    }




    public void shareToQZone(ShareContent shareContent,IUiListener listener) {
        //分享类型
        final Bundle params = new Bundle();
        //类型
        params.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        //要分享的标题 //必填
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题");
        //要分享的摘要 //选填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "摘要");
        //目标连接地址 必填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "跳转URL");
        //图片地址数组
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, null);

        mTencent.shareToQzone((Activity) mContext, params, listener);
    }





}


