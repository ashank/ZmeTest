package com.funhotel.hmvp.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.funhotel.hmvp.R;
import com.zme.zlibrary.base.BaseActivity;
import com.zme.zlibrary.widget.AdvancedWebView;

/**
 * 页面浏览显示
 *
 * @author dell
 */
@SuppressWarnings("deprecation")
public class AdvanceWebActivity extends BaseActivity implements AdvancedWebView.Listener {

    private static AdvancedWebView mWebView;

    private FrameLayout customViewContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private View mCustomView;

    private String url;
    private String title;
    private Toolbar toolbar;
    private TextView tvBack, tvTitle, tvShare;

    private static RelativeLayout load, error;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        url = getIntent().getStringExtra("URL");
        title = getIntent().getStringExtra("TITLE");
        //初始化
        initView();

        tvTitle.setText(title);
        tvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoBack()) {  //表示按返回键 时的操作
                    mWebView.goBack();
                } else {
                    finish();
                }
            }
        });

        tvShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWebView.setListener(AdvanceWebActivity.this, this);
        if (null != url) {
            error.setVisibility(View.GONE);
            load.setVisibility(View.VISIBLE);
            mWebView.loadUrl(url);
            mWebView.loadUrl("javascript:loadImgFun()");
        } else {
            load.setVisibility(View.GONE);
            error.setVisibility(View.VISIBLE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //在Android系统4.3.1~3.0版本,系统webview默认添加了searchBoxJavaBridge_接口,如果未移除该接口可能导致低版本Android系统远程命令执行漏洞，
            // 当Android的SDK版本>=11 <17时，调用removeJavascriptInterface方法移除“searchBoxJavaBridge_”接口
            try {
                mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            } catch (Throwable tr) {
                tr.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        mWebView.resumeTimers();
    }

    @Override
    public void onPause() {
        mWebView.onPause();
        mWebView.pauseTimers();
        load.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            if (null != mWebView.getParent()) {
                ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            }
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mWebView.onBackPressed();
        super.onBackPressed();
    }

    /**
     * 初始化View
     */
    private void initView() {
        // TODO Auto-generated method stub
        // customViewContainer = (FrameLayout)
        tvBack = findViewById(R.id.tv_back);
        tvTitle = findViewById(R.id.tv_title);
        tvShare = findViewById(R.id.tv_more);
        load = findViewById(R.id.load);
        error =  findViewById(R.id.error);
        mWebView = findViewById(R.id.pull_refresh_webview);
        error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                load.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                mWebView.loadUrl(url);
            }
        });
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void onDownloadRequested(String url, String userAgent, String contentDisposition, String mimetype,
            long contentLength) {

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        load.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }


    @Override
    public void onPageFinished(String url) {
        mWebView.getSettings().setBlockNetworkImage(false);
        load.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        mWebView.stopLoading();
        mWebView.clearView();
        load.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }


    @Override
    public void onExternalPageRequest(final String url) {
        if (url.startsWith("http:") || url.startsWith("https:")) {
            mWebView.loadUrl(url);
        } else {
            try {
                // Otherwise allow the OS to handle things like tel, mailto, etc.
                if (url.contains("tel")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return true;
        }
        if (mWebView.canGoBack()) {  //表示按返回键 时的操作
            mWebView.goBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
