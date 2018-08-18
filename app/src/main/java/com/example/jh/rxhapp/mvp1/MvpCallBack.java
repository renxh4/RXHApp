package com.example.jh.rxhapp.mvp1;

/**
 * Created by xiaohui on 2018/8/6.
 */

public interface MvpCallBack {
    /**
     * 定义请求数据的状态
     * @param data
     */
    void onSuccess(String data);

    void onFailure();
}
