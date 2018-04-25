package com.funhotel.hmvp.ui.activity;

import android.Manifest.permission;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.funhotel.hmvp.R;
import com.zme.zlibrary.commonactivity.PermissionsActivity;
import com.zme.zlibrary.commonactivity.PermissionsChecker;

/**
 * 权限页面的获取
 */
public class PermissionDemoActivity extends AppCompatActivity {

  private static final int REQUEST_CODE = 0; // 请求码

  // 所需的全部权限
  static final String[] PERMISSIONS = new String[]{
      permission.INTERNET,
      permission.WRITE_EXTERNAL_STORAGE,
      permission.READ_EXTERNAL_STORAGE
  };

  @BindView(R.id.main_t_toolbar)
  Toolbar mTToolbar;
  @BindView(R.id.main_t_toolbar)
  Toolbar mainTToolbar;

  private PermissionsChecker mPermissionsChecker; // 权限检测器

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(mTToolbar);

    mPermissionsChecker = new PermissionsChecker(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // 缺少权限时, 进入权限配置页面
    if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
      PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    mPermissionsChecker.dealWithPermission(requestCode, resultCode, data,
        new PermissionsChecker.PermissionCallBack() {
          @Override
          public void havaPermission() {
            Toast.makeText(PermissionDemoActivity.this, "获得权限", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void noPermission() {
            finish();
          }
        });

  }
}
