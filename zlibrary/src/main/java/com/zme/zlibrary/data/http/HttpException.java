package com.zme.zlibrary.data.http;

/**
 * Created by zhiyahan on 2017/3/28.
 * http自定义异常
 */

public class HttpException extends Exception {

    private int code;
    private String message;

    /**
     * 构造器
     * @param code  错误代码
     * @param message  错误消息
     */
    public HttpException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
