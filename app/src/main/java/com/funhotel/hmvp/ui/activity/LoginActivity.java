package com.funhotel.hmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.gen.UserDao;
import com.zme.zlibrary.thirdparty.login.LoginErrorException;
import com.zme.zlibrary.thirdparty.login.LoginManager;
import com.zme.zlibrary.thirdparty.login.OnLoginListener;
import com.zme.zlibrary.thirdparty.share.OnShareListener;
import com.zme.zlibrary.thirdparty.share.ShareContent;
import com.zme.zlibrary.thirdparty.share.ShareException;
import com.zme.zlibrary.thirdparty.share.ShareManager;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录模块
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {

    private TextView btLogin;
    private TextView btGotoMain;
    private EditText etTel;
    private EditText etPas;
    private TextView tvRegisted;

    private UserDao userDao;

   private LoginManager manager;

   private ShareManager shareManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        manager = new LoginManager(LoginActivity.this, new OnLoginListener() {
            @Override
            public void onStart() {

                Toast.makeText(LoginActivity.this, "onstart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(Object o) {
                Toast.makeText(LoginActivity.this, "success"+o.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "oncancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(LoginErrorException e) {

                Toast.makeText(LoginActivity.this, "onerror", Toast.LENGTH_SHORT).show();

            }
        });


        shareManager = new ShareManager(this, new OnShareListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(LoginActivity.this, "分享成功" +o.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "取消分享", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(ShareException e) {
                Toast.makeText(LoginActivity.this, "分享失败", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * 初始化
     */
    private void init() {
        btGotoMain = findViewById(R.id.tv_free);
        btLogin = findViewById(R.id.tv_login);
        btLogin.setOnClickListener(this);
        btGotoMain.setOnClickListener(this);
        etTel = findViewById(R.id.et_mobile);
        etPas = findViewById(R.id.et_password);
        tvRegisted = findViewById(R.id.tv_registed);
        tvRegisted.setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Intent intent1 = new Intent();
        intent1.setClass(LoginActivity.this, HomeActivity.class);
        startActivity(intent1);
        finish();
        super.onNewIntent(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_registed:
                //注册
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_free:
                Intent intent1 = new Intent();
                intent1.setClass(LoginActivity.this, HomeActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_login:
//                if (TextUtils.isEmpty(etTel.getText().toString())) {
//                    Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                if (TextUtils.isEmpty(etPas.getText().toString())) {
//                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                DaoSession daoSession = AppApplication.newInstance().getDaoSession();
//                userDao = daoSession.getUserDao();
//
//                List<User> list = userDao.queryBuilder().where(Properties.TelePhone.eq(etTel.getText().toString()))
//                        .list();
//
//                if (list == null || list.size() == 0) {
//                    Toast.makeText(this, "请注册", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//
//                if (!list.get(0).getPassword().equals(etPas.getText().toString())) {
//                    Toast.makeText(this, "密码不正确", Toast.LENGTH_SHORT).show();
//                    break;
//                }

//                final User user = new User();
//                user.setTelePhone(etTel.getText().toString());
//                user.setPassword(etPas.getText().toString());
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        userDao.insert(user);
//
//                        Intent intent = new Intent();
//                        intent.setClass(LoginActivity.this, HomeActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }).start();

//                manager.login(LoginManager.Type.QQ);

                ShareContent content = new ShareContent();
                content.setAppName("12121");
                content.setContent("摘要");
                content.setImage("http://img1.imgtn.bdimg.com/it/u=3873040750,1105020127&fm=26&gp=0.jpg");
                content.setTitle("测试标题");
                content.setUrl("http://image.baidu.com");

                List<String> images = new ArrayList<>();
                images.add("http://img1.imgtn.bdimg.com/it/u=3873040750,1105020127&fm=26&gp=0.jpg");
                content.setImages(images);

                shareManager.share(ShareManager.Type.QQ_APP,content);

                break;
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        manager.onActivityResult(requestCode,resultCode,data);
        shareManager.onActivityResult(requestCode,resultCode,data);

    }
}
