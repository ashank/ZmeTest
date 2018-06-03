package com.zme.zlibrary.widget.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.zme.zlibrary.widget.refresh.listener.IHeader;

/**
 * Description ：AbstractHeader
 * Author：ZME
 * Create Time ：2018/6/3 13:55
 * Modify Time：2018/6/3 13:55
 * Version：1.0
 */
public abstract class AbstractHeader extends RelativeLayout implements IHeader {

    public AbstractHeader(Context context) {
        super(context);
    }

    public AbstractHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
