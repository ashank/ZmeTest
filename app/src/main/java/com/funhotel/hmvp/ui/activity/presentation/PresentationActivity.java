package com.funhotel.hmvp.ui.activity.presentation;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

import com.funhotel.hmvp.R;


/*
 双屏显示
 **/

public class PresentationActivity extends AppCompatActivity {

    DifferentDislay mPresentation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayManager mDisplayManager;// 屏幕管理类
        mDisplayManager = (DisplayManager) this
                .getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();

        if (mPresentation == null) {
            // displays[1]是副屏
            mPresentation = new DifferentDislay(this, displays[displays.length - 1]);
            mPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
            mPresentation.show();
        }
    }
}
