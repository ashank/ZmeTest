package com.zme.zlibrary.thirdparty.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Description ：QQLogin
 * Author：ZME
 * Create Time ：2018/10/17 20:09
 * Modify Time：2018/10/17 20:09
 * Version：1.0
 */
public class QQLogin {

    private Context mContext;
    private Tencent mTencent;

    private static final String scope = "all";


    public QQLogin(Context context, String appId) {
        this.mContext = context;
        mTencent = Tencent.createInstance(appId, context);
    }


    public void login(IUiListener listener) {
        if (!mTencent.isSessionValid()) {
            mTencent.login((Activity) mContext, scope, listener);
        }
    }


    public void logout() {
        if (mTencent != null) {
            mTencent.logout(mContext);
        }
    }

    /**
     * 在某些低端机上调用登录后，由于内存紧张导致APP被系统回收，登录成功后无法成功回传数据。
     * 需要在onActivityResult中处理回掉
     *
     * 并且要实现回掉才能接收到数据
     * @param requestCode
     * @param resultCode
     * @param data
     * @param listener
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data,IUiListener listener) {
        if(requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, listener);
        }
    }



    /**
     * 获取用户信息，初次授权获取到openid，然后利用openid 去获取用户的信息
     *
     *
     * @param response
     * @param listener
     */
    public void getUserInfo(Object response,IUiListener listener) {

        JSONObject obj = (JSONObject) response;
        try {
            String openID = obj.getString("openid");
            String accessToken = obj.getString("access_token");
            String expires = obj.getString("expires_in");
            mTencent.setOpenId(openID);
            mTencent.setAccessToken(accessToken, expires);
            QQToken qqToken = mTencent.getQQToken();
            UserInfo mUserInfo = new UserInfo(mContext, qqToken);
            mUserInfo.getUserInfo(listener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
