
/*
 *   Copyright (C) 2018  ZME
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.funhotel.hmvp.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.adapter.ViewPaperAdapter;
import com.funhotel.hmvp.model.entity.NewType;
import com.funhotel.hmvp.ui.fragement.NewFragment;
import com.funhotel.hmvp.ui.fragement.NewFragment.OnFragmentInteractionListener;
import com.zme.zlibrary.utils.LogUtils;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;
import java.util.ArrayList;
import java.util.List;

public class RxJavaOkhttpActivity extends AppCompatActivity implements
        OnFragmentInteractionListener {

    private static final String TAG = "RxJavaOKHttpActivity";
    private Disposable dis;
    private List<NewType> list = new ArrayList<>();
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rx_java_okhttp);

        list = initNewTypeList();
        initView();
        setupToobar();
        setupViewPager();

        //HttpManager httpManager = HttpManager.getInstance(HttpConstant.BASE_URL);
//    httpManager.getCalendar("2018-03-27", new CalendarResourceSubscriber());

    }

    private class CalendarResourceSubscriber extends ResourceSubscriber<Calendar> {

        @Override
        public void onNext(Calendar calendar) {

            LogUtils.e("onNext===" + calendar.toString());
        }

        @Override
        public void onError(Throwable t) {
            LogUtils.e("onError===" + t.toString());
        }

        @Override
        public void onComplete() {
            LogUtils.e("onComplete===");
        }

        @Override
        protected void onStart() {
            LogUtils.e("onStart===");
            super.onStart();
        }

    }

    private void initView() {
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    /**
     * 初始化Toolbar
     */
    private void setupToobar() {
        ImageView img = toolbar.findViewById(R.id.iv_left);
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setupViewPager() {

        List<NewFragment> fragmentList = new ArrayList<>();
        int lenght = list.size();
        for (int i = 0; i < lenght; i++) {
            fragmentList.add(NewFragment.newInstance(list.get(i).getCode()));
        }
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ViewPaperAdapter viewPagerAdapter = new ViewPaperAdapter(getSupportFragmentManager(), fragmentList,
                list, null);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    private List<NewType> initNewTypeList() {

        List<NewType> list = new ArrayList<>();
        NewType newType1 = new NewType("top", "头条");
        NewType newType2 = new NewType("shehui", "社会");
        NewType newType3 = new NewType("guonei", "国内");
        NewType newType4 = new NewType("guoji", "国际");
        NewType newType5 = new NewType("yule", "娱乐");
        NewType newType6 = new NewType("tiyu", "体育");
        NewType newType7 = new NewType("junshi", "军事");
        NewType newType8 = new NewType("keji", "科技");
        NewType newType9 = new NewType("caijing", "财经");
        NewType newType10 = new NewType("shishang", "时尚");
        list.add(newType1);
        list.add(newType2);
        list.add(newType3);
        list.add(newType4);
        list.add(newType5);
        list.add(newType6);
        list.add(newType7);
        list.add(newType8);
        list.add(newType9);
        list.add(newType10);
        return list;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
