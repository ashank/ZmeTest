package com.funhotel.greendao.entity;

import com.funhotel.mvp.common.BaseModel;

import java.util.List;

/**
 * CLASS ： EpgBean
 * Author : zhiyahan
 * TIME : 2017/6/5 12:06
 */

public class EpgBean extends BaseModel<List<EpgData>>{

    @Override
    public int getResult() {
        return super.getResult();
    }

    @Override
    public void setResult(int result) {
        super.setResult(result);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    @Override
    public List<EpgData> getData() {
        return super.getData();
    }

    @Override
    public void setData(List<EpgData> data) {
        super.setData(data);
    }
}
