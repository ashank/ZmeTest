package com.zme.zlibrary.widget.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.zme.zlibrary.widget.refresh.listener.IFooter;

/**
 * Description ：AbstractHeader
 * Author：ZME
 * Create Time ：2018/6/3 13:55
 * Modify Time：2018/6/3 13:55
 * Version：1.0
 */
public abstract class AbstractFooter extends RelativeLayout implements IFooter {

    public AbstractFooter(Context context) {
        super(context);
    }

    public AbstractFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
