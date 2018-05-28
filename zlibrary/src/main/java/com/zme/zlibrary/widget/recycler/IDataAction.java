package com.zme.zlibrary.widget.recycler;

import java.util.List;

/**
 * Description ：适配器的数据动作管理
 * @param   <T>
 * Author：ZME
 * Create Time ：2018/5/21 23:07
 * Modify Time：2018/5/21 23:07
 * Version：1.0
 */
public interface IDataAction<T> {

    /**
     *  重置数组
     * @param list  数组
     */
    void resetData(List<T> list);

    /**
     * 添加数据
     * @param list 数据 see List
     */
    void addData(List<T> list);

    /**
     *  添加单个数据
     * @param t  数组的元素
     */
    void addData(T t);

    /**
     * 删除队列中的某一项数据
     * @param position 数据在队列的位置
     */
    void deleteData(int position);

    /**
     * 删除所有的数据
     */
    void deleteAllData();
}
