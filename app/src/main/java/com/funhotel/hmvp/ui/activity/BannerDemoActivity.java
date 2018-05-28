package com.funhotel.hmvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.funhotel.hmvp.R;
import com.zme.zlibrary.widget.banner.RecyclerViewBannerBase;
import com.zme.zlibrary.widget.banner.RecyclerViewBannerNew;
import java.util.ArrayList;
import java.util.List;

public class BannerDemoActivity extends AppCompatActivity {

    //3D版本，普通版本用NormalRecyclerViewBanner
    RecyclerViewBannerNew banner, banner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_demo);
        banner = findViewById(R.id.banner);
        banner2 = findViewById(R.id.banner2);
        List<String> list = new ArrayList<>();
        list.add("http://img3.imgtn.bdimg.com/it/u=3059607941,393199546&fm=27&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=302701032,2300144492&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=92471855,800758943&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=2188342990,2008061052&fm=27&gp=0.jpg");
        banner.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BannerDemoActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner2.initBannerImageView(list);

    }

}
