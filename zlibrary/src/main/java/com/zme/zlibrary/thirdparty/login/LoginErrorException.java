package com.zme.zlibrary.thirdparty.login;

/**
 * Description ：LoginErrorException
 * Author：ZME
 * Create Time ：2018/10/17 20:36
 * Modify Time：2018/10/17 20:36
 * Version：1.0
 */
public class LoginErrorException extends Exception {


    private int code;
    private String detailMassage;


    public LoginErrorException(String message, int code, String detailMassage) {
        super(message);
        this.code = code;
        this.detailMassage = detailMassage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetailMassage() {
        return detailMassage;
    }

    public void setDetailMassage(String detailMassage) {
        this.detailMassage = detailMassage;
    }
}
