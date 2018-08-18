package com.example.jh.rxhapp.mvp2.m;

/**
 * Created by xiaohui on 2018/8/7.
 */

public abstract class BaseModel<T> {
    //数据参数
    public String[] params;

    public BaseModel params(String... arg) {
        params = arg;
        return this;
    }
    //添加callback并执行数据请求
    //具体数据请求由子类实现
    public abstract void excute(Callback<T> callback);

}
