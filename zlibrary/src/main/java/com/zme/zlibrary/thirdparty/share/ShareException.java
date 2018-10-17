package com.zme.zlibrary.thirdparty.share;

/**
 * Description ：ShareException
 * Author：ZME
 * Create Time ：2018/10/17 21:36
 * Modify Time：2018/10/17 21:36
 * Version：1.0
 */
public class ShareException extends Exception {

    private int code;

    public ShareException(String message) {
        super(message);
    }

    public ShareException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
