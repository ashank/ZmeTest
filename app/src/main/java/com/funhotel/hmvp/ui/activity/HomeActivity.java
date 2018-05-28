package com.funhotel.hmvp.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.funhotel.hmvp.R;
import com.funhotel.hmvp.ui.fragement.MainNewFragment;
import com.funhotel.hmvp.ui.fragement.MainNewFragment.OnFragmentInteractionListener;
import com.funhotel.hmvp.ui.fragement.MineFragment;
import com.funhotel.hmvp.ui.fragement.NewFragment;
import com.funhotel.hmvp.ui.fragement.RecommendNewListFragment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HomeActivity extends AppCompatActivity implements OnFragmentInteractionListener,
        NewFragment.OnFragmentInteractionListener,
        MineFragment.OnFragmentInteractionListener,
        RecommendNewListFragment.OnFragmentInteractionListener {

    private List<Fragment> fragments = new ArrayList<>();
    private int lastIndex = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastIndex != 0) {
                        switchFragment(lastIndex, 0);
                    }
                    lastIndex = 0;
                    return true;
                case R.id.navigation_dashboard:
                    if (lastIndex != 1) {
                        switchFragment(lastIndex, 1);
                    }
                    lastIndex = 1;
                    return true;
                case R.id.navigation_notifications:
                    if (lastIndex != 2) {
                        switchFragment(lastIndex, 2);
                    }
                    lastIndex = 2;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//    mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragment();
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上一个Tab的位置
     * @param showIndex 需要显示的Tab的位置
     */
    public void switchFragment(int lastIndex, int showIndex) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragments.get(lastIndex).isAdded()) {
            transaction.hide(fragments.get(lastIndex));
        }
        if (fragments.get(showIndex).isAdded()) {
            transaction.show(fragments.get(showIndex));
        } else {
            transaction.add(R.id.fragment, fragments.get(showIndex));
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 初始化Fragment的列表
     */
    private void initFragment() {
        Fragment fragment = new MainNewFragment();
        Fragment fragment1 = new RecommendNewListFragment();
        Fragment fragment2 = new MineFragment();
        fragments.add(fragment);
        fragments.add(fragment1);
        fragments.add(fragment2);
        switchFragment(lastIndex, 0);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
