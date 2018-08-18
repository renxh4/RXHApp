package com.example.jh.rxhapp.mvp1;

/**
 * Created by xiaohui on 2018/8/6.
 */

public interface MvpView {
    /**
     * 定义ui处理的接口，为Presenter提供操作ui的方法
     */
    void showLoading();

    void dissMissLoading();

    void showData(String msg);
}
