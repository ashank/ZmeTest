package com.zme.zlibrary.widget.refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.zme.zlibrary.R;
import com.zme.zlibrary.widget.refresh.listener.IFooter;

/**
 * Description ：HeaderView
 * Author：ZME
 * Create Time ：2018/6/2 23:00
 * Modify Time：2018/6/2 23:00
 * Version：1.0
 */
public class FooterView extends AbstractFooter implements IFooter {

    private Context context;

    public FooterView(@NonNull Context context) {
        this(context,null);
    }

    public FooterView(@NonNull Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FooterView(@NonNull Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_header,this);
    }

    @Override
    public IFooter onLoading() {
        return this;
    }
}
