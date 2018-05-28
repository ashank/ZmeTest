package com.funhotel.hmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.gen.DaoSession;
import com.funhotel.hmvp.gen.UserDao;
import com.funhotel.hmvp.gen.UserDao.Properties;
import com.funhotel.hmvp.global.AppApplication;
import com.funhotel.hmvp.model.entity.User;
import java.util.List;

/**
 * 注册
 */
public class RegistActivity extends AppCompatActivity implements OnClickListener {

    private EditText etTel;
    private EditText etPas;
    private TextView tvNext;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etPas = findViewById(R.id.et_password);
        etTel = findViewById(R.id.et_cellphone);
        tvNext = findViewById(R.id.tv_next);
        tvNext.setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.iv_back) {
            finish();
            return;
        }

        if (TextUtils.isEmpty(etTel.getText().toString())) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPas.getText().toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        DaoSession daoSession = AppApplication.newInstance().getDaoSession();
        userDao = daoSession.getUserDao();

        List<User> list = userDao.queryBuilder().where(Properties.TelePhone.eq(etTel.getText().toString()))
                .list();

        if (list == null || list.size() == 0) {
            //注册
            final User user = new User();
            user.setTelePhone(etTel.getText().toString());
            user.setPassword(etPas.getText().toString());
            userDao.insert(user);

            Intent intent = new Intent();
            intent.setClass(RegistActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("TEL", etTel.getText().toString());
            intent.putExtra("PAS", etPas.getText().toString());
            startActivity(intent);
            finish();
            return;
        }

        Toast.makeText(this, "你已经注册了", Toast.LENGTH_SHORT).show();
    }
}
