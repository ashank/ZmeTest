package com.zme.zlibrary.widget.refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.zme.zlibrary.R;
import com.zme.zlibrary.widget.refresh.listener.IHeader;

/**
 * Description ：HeaderView
 * Author：ZME
 * Create Time ：2018/6/2 23:00
 * Modify Time：2018/6/2 23:00
 * Version：1.0
 */
public class HeaderView extends AbstractHeader implements IHeader {

    private Context context;
    private TextView textView;

    public HeaderView(@NonNull Context context) {
        this(context,null);
    }

    public HeaderView(@NonNull Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HeaderView(@NonNull Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_header,this);
        textView=findViewById(R.id.tv);
    }

    @Override
    public void onRefreshNotTrigger(int scrollY) {
        textView.setText("下拉刷新...");

    }

    @Override
    public void onRefreshTrigger(int scrollY) {
        textView.setText("释放立即刷新...");
    }


    @Override
    public void onRefreshing(int scrollY) {
        textView.setText("正在刷新...");
    }

    @Override
    public void onRefreshingScrolling(int scrollY) {

    }

    @Override
    public void onRefreshFinish(int scrollY, boolean isRefreshSuccess) {

        textView.setText("刷新完成...");

    }
}
