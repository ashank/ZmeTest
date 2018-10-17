package com.zme.zlibrary.thirdparty.login;

import android.content.Context;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Description ：WeixinLogin
 * Author：ZME
 * Create Time ：2018/10/17 20:14
 * Modify Time：2018/10/17 20:14
 * Version：1.0
 */
public class WeixinLogin {

    IWXAPI wxAPI;

    private String appId;

    public WeixinLogin(Context context) {
        if (wxAPI == null) {
            wxAPI = WXAPIFactory.createWXAPI(context, appId, true);
            wxAPI.registerApp(appId);
        }
    }


    //检查微信是否安装
    public boolean isWXAppInstalled() {
        return wxAPI.isWXAppInstalled();
    }

    /**
     * 微信登陆(三个步骤)
     * 1.微信授权登陆,获取到结果响应在WXEntryActivity中处理
     * 2.根据授权登陆code 获取该用户token
     * 3.根据token获取用户资料
     */
    public void login() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = String.valueOf(System.currentTimeMillis());
        wxAPI.sendReq(req);
    }

    /**
     * 获取微信访问token （openid等数据）
     */
    public void getAccessToken(String code) {
        //没有登陆的情况用第三方登陆

    }

    /**
     * 通过token 获取微信用户信息
     */
    private void getWeiXinUserInfo(String obj) {

    }

}
