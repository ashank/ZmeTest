package com.zme.zlibrary.data.http;

import java.util.List;

/**
 * Description ：NewType
 * Author：ZME
 * Create Time ：2018/7/8 00:00
 * Modify Time：2018/7/8 00:00
 * Version：1.0
 */
public class NewType {

    /**
     * status : 0
     * msg : ok
     * result : ["头条","新闻","财经","体育","娱乐","军事","教育","科技","NBA","股票","星座","女性","健康","育儿"]
     */
    private List<String> result;

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
