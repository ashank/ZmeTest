//package com.zme.zlibrary.wxapi;
//
//import static com.tencent.mm.sdk.constants.ConstantsAPI.COMMAND_SENDAUTH;
//import static com.tencent.mm.sdk.constants.ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.util.ArrayMap;
//import android.text.TextUtils;
//import android.util.Log;
//import com.example.myfrist.umenglibrary.analyticsconfig.UmengUtil;
//import com.example.myfrist.umenglibrary.analyticsconfig.UmengonEventUtil;
//import com.example.utils.DebugUtil;
//import com.funhotel.travel.R;
//import com.funhotel.travel.activity.MainActivity;
//import com.funhotel.travel.activity.sign.UserEditActivity;
//import com.funhotel.travel.application.FunhotelApplication;
//import com.funhotel.travel.application.PublicConstant;
//import com.funhotel.travel.application.URLConstant;
//import com.funhotel.travel.base.BaseActivity;
//import com.funhotel.travel.db.LoginInfoDBManager;
//import com.funhotel.travel.db.TableKey;
//import com.funhotel.travel.db.UserInfoDBManger;
//import com.funhotel.travel.dialog.ProgressDialogOptions;
//import com.funhotel.travel.dialog.SimpleDialog;
//import com.funhotel.travel.http.HttpConnect;
//import com.funhotel.travel.http.HttpConnetManager;
//import com.funhotel.travel.http.IConnectListener;
//import com.funhotel.travel.http.ParseJson;
//import com.funhotel.travel.model.LoginInfoModel;
//import com.funhotel.travel.model.UserInfoModel;
//import com.funhotel.travel.model.UserTagsListViewModel;
//import com.funhotel.travel.utils.AppInfo;
//import com.funhotel.travel.utils.DeviceUtil;
//import com.funhotel.travel.utils.MD5Util;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.modelmsg.SendAuth;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.sdk.modelbase.BaseReq;
//import com.tencent.mm.sdk.openapi.WXAPIFactory;
//import com.umeng.socialize.UMAuthListener;
//import com.umeng.socialize.UMShareAPI;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import java.net.URLEncoder;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * @Title: WXEntryActivity
// * @Description: 微信分享以及登录处理
// * @author: LinWeiDong
// * @data: 2016/1/18 16:57
// */
//public class WXEntryActivity extend Activity implements IWXAPIEventHandler {
//
//    private IWXAPI iwxapi;
//    public static BaseResp resp = null;
//    private Context mContext = WXEntryActivity.this;
//    private UMShareAPI mUmShareAPI;
//    SendAuth.Resp newResp;
//    private String get_access_token;
//    // 获取第一步的code后，请求以下链接获取access_token
//    public String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
//
//    //获取用户个人信息
//    public String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
//    private HttpConnetManager httpConnetManager;
//    private SimpleDialog simpleDialog;
//    private LoginInfoModel loginInfoModel = new LoginInfoModel();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        iwxapi = WXAPIFactory.createWXAPI(this, PublicConstant.getWxAppId());
//        iwxapi = WXAPIFactory.createWXAPI(this, PublicConstant.getWxAppId(), false);
////        DebugUtil.i("WXEntryActivity WxAppId =" + PublicConstant.getWxAppId());
//        loginInfoModel = LoginInfoDBManager.getLoginInfo(FunhotelApplication.newInstance().getSQLiteDatabase());
//        //注册API
//        iwxapi.handleIntent(getIntent(), this);
//    }
//
//    // 微信发送请求到第三方应用时，会回调到该方法
//    @Override
//    public void onReq(BaseReq req) {
//        finish();
//    }
//
//    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
//    @Override
//    public void onResp(BaseResp resp) {
//        DebugUtil.i("WXEntryActivity onResp  =" + resp.toString());
//        if (resp != null) {
//            if (resp.getType() == COMMAND_SENDAUTH) {//第三方登录
//                newResp = (SendAuth.Resp) resp;
//                Log.d("flag", "微信登录");
//            } else if (resp.getType() == COMMAND_SENDMESSAGE_TO_WX) {//微信分享
//                WXEntryActivity.resp = resp;
//                Log.d("flag", "微信分享");
//            }
//        }
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                DebugUtil.i("发送成功");
//                if (resp.getType() == COMMAND_SENDAUTH) {
//
//                    mUmShareAPI.getPlatformInfo((Activity) mContext, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
//                        @Override
//                        public void onComplete(SHARE_MEDIA share_media, int action, Map<String, String> map) {
//                            DebugUtil.i("action = " + action);
//                            DebugUtil.i("WXEntryActivity newResp.code  =" + newResp.code);
//                            get_access_token = getCodeRequest(newResp.code);
//                            DebugUtil.i("WXEntryActivity get_access_token  =" + get_access_token);
//                            WXGetAccessToken();
//                            PublicConstant.setIsEntryWX(true);
//                        }
//
//                        @Override
//                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//                            DebugUtil.e("onError");
//                            PublicConstant.setIsWxLoginShowProgressDialog(false);
//                            PublicConstant.setIsEntryWX(true);
//                        }
//
//                        @Override
//                        public void onCancel(SHARE_MEDIA share_media, int i) {
//                            DebugUtil.i("onCancel");
//                            PublicConstant.setIsWxLoginShowProgressDialog(false);
//                            PublicConstant.setIsEntryWX(true);
//                        }
//                    });
//                } else {
//                    DebugUtil.customToast(mContext, "微信分享成功");
//                    DebugUtil.i("微信分享成功");
//                }
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                DebugUtil.i("发送取消");
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                DebugUtil.e("发送被拒绝");
//                if (resp.getType() == COMMAND_SENDAUTH) {
//                    PublicConstant.setIsWxLoginShowProgressDialog(false);
//                    DebugUtil.customToast(mContext, "微信登录失败");
//                    loginInfoModel.setIsFirstlogin(true);
//                    LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                } else {
//                    DebugUtil.customToast(mContext, "微信分享失败");
//                }
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_SENT_FAILED:
//                DebugUtil.e("发送错误");
//                if (resp.getType() == COMMAND_SENDAUTH) {
//                    PublicConstant.setIsWxLoginShowProgressDialog(false);
//                    DebugUtil.customToast(mContext, "微信登录失败");
//                    loginInfoModel.setIsFirstlogin(true);
//                    LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                } else {
//                    DebugUtil.customToast(mContext, "微信分享失败");
//                }
//                finish();
//                break;
//            default:
////                PublicConstant.setIsWxLoginShowProgressDialog(false);
//                DebugUtil.i("发送返回");
//                finish();
//                break;
//        }
//    }
//
//    /**
//     * 获取access_token等等的信息(微信)
//     */
//    private void WXGetAccessToken() {
//        HttpConnect mHttpConnect = new HttpConnect(mContext, get_access_token, "");
//        mHttpConnect.setHttpType(HttpConnect.POST);
//        mHttpConnect.setIConnectListener(new IConnectListener() {
//            @Override
//            public void notNet(int code) {
//                DebugUtil.customToast(mContext, "网络连接失败");
//                loginInfoModel.setIsFirstlogin(true);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//            }
//
//            @Override
//            public void getDataSuccess(int code, String result) {
//                String access_token = "";
//                String openid = "";
//                try {
//                    JSONObject json1 = new JSONObject(result);
//                    access_token = (String) json1.get("access_token");
//                    openid = (String) json1.get("openid");
//                    DebugUtil.i("WXGetAccessToken() access_token = " + access_token);
//                    DebugUtil.i("WXGetAccessToken() openid = " + openid);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                String get_user_info_url = getUserInfo(access_token, openid);
//                DebugUtil.i("WXGetAccessToken() get_user_info_url = " + get_user_info_url);
//                WXGetUserInfo(get_user_info_url);
//
//            }
//
//            @Override
//            public void getDataFail(int code, String result) {
//                DebugUtil.customToast(mContext, "微信登录失败");
//                loginInfoModel.setIsFirstlogin(true);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//            }
//        });
//        mHttpConnect.start();
//    }
//
//    /**
//     * 获取微信用户个人信息
//     *
//     * @param get_user_info_url 调用URL
//     */
//    private void WXGetUserInfo(String get_user_info_url) {
//
//        HttpConnect mHttpConnect = new HttpConnect(mContext, get_user_info_url, "");
//        mHttpConnect.setHttpType(HttpConnect.GET);
//        mHttpConnect.setIConnectListener(new IConnectListener() {
//            @Override
//            public void notNet(int code) {
//                DebugUtil.customToast(mContext, "网络连接失败");
//                loginInfoModel.setIsFirstlogin(true);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//            }
//
//            @Override
//            public void getDataSuccess(int code, String result) {
//                try {
//                    JSONObject json1 = new JSONObject(result);
//                    //调用三方登录接口
//                    toThreeLoginThread(json1);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void getDataFail(int code, String result) {
//                DebugUtil.customToast(mContext, "微信登录失败");
//                loginInfoModel.setIsFirstlogin(true);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//            }
//        });
//        mHttpConnect.start();
//
//    }
//
//    /**
//     * 调用三方登录接口
//     *
//     * @param json1
//     */
//    private void toThreeLoginThread(JSONObject json1) throws JSONException {
//        final String thirLoginAcater = String.valueOf(json1.get("headimgurl"));
//        ArrayMap<String, String> postDataMap = new ArrayMap<String, String>();
//        postDataMap.put("OpenId", String.valueOf(json1.get("openid")));
////        postDataMap.put("NickName", PublicUtil.EncoderText(String.valueOf(json1.get("nickname"))));
//        postDataMap.put("NickName", String.valueOf(json1.get("nickname")));
//        postDataMap.put("HeadImgUrl", thirLoginAcater);
//        postDataMap.put("Sex", String.valueOf(json1.get("sex")));
//        String city = String.valueOf(json1.get("city"));//城市
//        if (TextUtils.isEmpty(city)) {
//            city = "";
//        }
//        postDataMap.put("City", city);
//        String province = (String) json1.get("province");//省
//        if (TextUtils.isEmpty(province)) {
//            province = "";
//        }
//        postDataMap.put("Province", province);
//        String country = (String) json1.get("country");//国家
//        if (TextUtils.isEmpty(country)) {
//            country = "";
//        }
//        postDataMap.put("Country", country);
//        postDataMap.put("DeviceToken", FunhotelApplication.getDeviceToken());
//        postDataMap.put("DeviceType", DeviceUtil.getDeviceType());
//        postDataMap.put("Platform", "1");
//        postDataMap.put("Sign", createSign(String.valueOf(json1.get("openid"))));
//        //v2API新添字段值
//        postDataMap.put("Channel", DeviceUtil.getChannelName(mContext));
//        postDataMap.put("Brand", DeviceUtil.getDeviceBrand());
//        postDataMap.put("MobileType", DeviceUtil.getMobileType());
//        postDataMap.put("LoginProvince", DeviceUtil.getProvince());
//        postDataMap.put("LoginCity", DeviceUtil.getCity());
//        postDataMap.put("LoginRegion", DeviceUtil.getDistrict());//可不填
//        IConnectListener mConnectListener = new IConnectListener() {
//            @Override
//            public void notNet(int code) {
//                DebugUtil.customToast(mContext, "网络连接失败");
//                loginInfoModel.setIsFirstlogin(true);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//            }
//
//            @Override
//            public void getDataSuccess(int code, String result) {
//                preserveUserInfo(mContext, result, thirLoginAcater);
//            }
//
//            @Override
//            public void getDataFail(int code, String result) {
//                DebugUtil.e(">>>>>>>getDataFail>>>>>" + code + "\r\n" + "erro>>>>>>" + result);
//                DebugUtil.customToast(mContext, "登录失败");
//                loginInfoModel.setIsFirstlogin(true);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//            }
//        };
//
//        ArrayMap<String, String> headers = new ArrayMap<String, String>();
//        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//        headers.put("token", "");
//        headers.put("version", String.valueOf(new AppInfo(mContext).getCurrentVersionName()));
//        headers.put("uid", "");
//        httpConnetManager.setIsSetHeader(true);
//        httpConnetManager.setHeaders(headers);
//        httpConnetManager.setType(HttpConnetManager.HttpType.POST);
//        httpConnetManager.connectHttp(URLConstant.THIRDLOGIN_V2_URL, postDataMap, mConnectListener);
//    }
//
//    /**
//     * 保存登录获取的个人信息
//     *
//     * @param context
//     * @param info
//     */
//    private void preserveUserInfo(Context context, String info, String thirLoginAcater) {
//        try {
//            ParseJson parseJson = new ParseJson();
//            UserInfoModel userInfoModel = parseJson.stringToObject(info, UserInfoModel.class);
//            if (1000 != userInfoModel.getResultCode()) {
//                PublicConstant.setIsWxLoginShowProgressDialog(false);
//                DebugUtil.customToast(context, userInfoModel.getMessage());
//                loginInfoModel.setIsFirstlogin(true);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//            } else {
//                PublicConstant.setIsWxLoginShowProgressDialog(true);
//                PublicConstant.setIsFreeEnterEffective(true);
//                loginInfoModel.setIsFirstlogin(false);
//                LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                userInfoModel.getData().setPassword("");
//                userInfoModel.getData().setIsThirdLogin(true);
//                userInfoModel.getData().setThriLoginAvatar(thirLoginAcater);
//                userInfoModel.getData().setProvider(PublicConstant.WEIXIN);
//                //友盟三方账号登录统计
//                UmengUtil.onProfileSignIn(PublicConstant.WEIXIN, userInfoModel.getData().getUserID());
//                //友盟自定义计算事件统计
//                UmengonEventUtil.onEvent(context, UmengonEventUtil.LOGIN_EVENT_ID, UmengonEventUtil.LOGIN_KEY, UmengonEventUtil.LOGIN_WX_VALUE);
//                //登录成功后存储用户信息到本地数据库
//                UserInfoDBManger.addUserInfo(FunhotelApplication.newInstance().getSQLiteDatabase(), userInfoModel.getData());
//                String NickName = UserInfoDBManger.getStringValueByKey(TableKey.NICKNAME);
//                String Avatar = UserInfoDBManger.getStringValueByKey(TableKey.AVATAR);
//                String Birthday = UserInfoDBManger.getStringValueByKey(TableKey.BIRTHDAY);
//                UserTagsListViewModel.TagListEntity tagList = userInfoModel.getData().getTagList();
//                int personalTagCount = 0;
//                if (null != tagList && null != tagList.getGroups() && tagList.getGroups().size() > 0) {
//                    for (int i = 0; i < tagList.getGroups().size(); i++) {
//                        if (TextUtils.equals("personal", tagList.getGroups().get(i).getGroupCode())) {
//                            personalTagCount = tagList.getGroups().get(i).getTags().size();
//                        }
//                    }
//                }
//                if (TextUtils.isEmpty(NickName) || TextUtils.isEmpty(Avatar) || TextUtils.isEmpty(Birthday) || personalTagCount != 3) {
//                    ProgressDialogOptions.dimissProgressDialog();
//                    loginInfoModel.setIsFirstlogin(true);
//                    LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                    String ToastContent = mContext.getString(R.string.load_toast2);
//                    if (personalTagCount != 3 && !TextUtils.isEmpty(NickName) && !TextUtils.isEmpty(Avatar) && !TextUtils.isEmpty(Birthday)) {
//                        ToastContent = mContext.getString(R.string.load_toast3);
//                    }
//                    // 对话框
//                    simpleDialog = new SimpleDialog(mContext, new SimpleDialog.ClickListener() {
//                        @Override
//                        public void yesClick(int item) {
//                            // TODO Auto-generated method stub
//                            simpleDialog.dismiss();
//                        }
//
//                        @Override
//                        public void noClick() {
//                            // TODO Auto-generated method stub
//                            simpleDialog.dismiss();
//                            //跳转到填写资料界面
//                            Intent intent = new Intent(mContext, UserEditActivity.class);
//                            mContext.startActivity(intent);
//                        }
//                    }, "", ToastContent, mContext.getString(R.string.no),
//                            mContext.getString(R.string.yes));
//                    simpleDialog.setCanceledOnTouchOutside(false);
//                    simpleDialog.show();
//                } else {
//                    loginInfoModel.setIsFirstlogin(false);
//                    loginInfoModel.setIsFirstenterFree(false);
//                    LoginInfoDBManager.addLoginInfo(loginInfoModel);
//                    if (PublicConstant.isFreeEnterEffective() && PublicConstant.isFreeEnter()) {
//                        Intent intent2 = new Intent(PublicConstant.FREE_UPMY_ACTION);
//                        context.sendBroadcast(intent2);
//                        //开始xmpp
//                        if (!TextUtils.isEmpty(UserInfoDBManger.getStringValueByKey(TableKey.ACCOUNT))) {
//                            ((BaseActivity) context).startXMPPService(UserInfoDBManger.getStringValueByKey(TableKey.ACCOUNT), "12345678");
//                        }
//                        Intent intent3 = new Intent(PublicConstant.UPDATE_FINISHLOGIN_ACTION);
//                        context.sendBroadcast(intent3);
//                        ((Activity) context).finish();
//
//                    } else {
//                        //跳转到主界面
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        Intent intent3 = new Intent(PublicConstant.UPDATE_FINISHLOGIN_ACTION);
//                        context.sendBroadcast(intent3);
//                        context.startActivity(intent);
//                        ((Activity) context).finish();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取用户个人信息的URL（微信）
//     *
//     * @param access_token 获取access_token时给的
//     * @param openid       获取access_token时给的
//     * @return URL
//     */
//    public String getUserInfo(String access_token, String openid) {
//        String result = null;
//        DebugUtil.i("getUserInfo() access_token = " + access_token);
//        DebugUtil.i("getUserInfo() openid = " + openid);
//        GetUserInfo = GetUserInfo.replace("ACCESS_TOKEN",
//                urlEnodeUTF8(access_token));
//        GetUserInfo = GetUserInfo.replace("OPENID",
//                urlEnodeUTF8(openid));
//        result = GetUserInfo;
//        return result;
//    }
//
//    /**
//     * 获取access_token的URL（微信）
//     *
//     * @param code 授权时，微信回调给的
//     * @return URL
//     */
//    public String getCodeRequest(String code) {
//        String result = null;
//        GetCodeRequest = GetCodeRequest.replace("APPID",
//                urlEnodeUTF8(PublicConstant.getWxAppId()));
//        GetCodeRequest = GetCodeRequest.replace("SECRET",
//                urlEnodeUTF8(PublicConstant.getWxAppSecret()));
//        GetCodeRequest = GetCodeRequest.replace("CODE", urlEnodeUTF8(code));
//        result = GetCodeRequest;
//        return result;
//    }
//
//    public String urlEnodeUTF8(String str) {
//        String result = str;
//        try {
//            result = URLEncoder.encode(str, "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        iwxapi.handleIntent(intent, this);
//        finish();
//    }
//
//    /**
//     * 对OpenId + Key 进行签名
//     *
//     * @param openid
//     * @return
//     */
//    private String createSign(String openid) {
//        SortedMap<Object, Object> parameters = new TreeMap<>();
//        parameters.put("OpenId", openid);
//        return MD5Util.createSign(parameters, "");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (null != simpleDialog) {
//            simpleDialog.cancel();
//            simpleDialog = null;
//        }
//    }
//
//
//}
