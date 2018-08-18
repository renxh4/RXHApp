package com.example.jh.rxhapp.mvp2.m;

/**
 * Created by xiaohui on 2018/8/6.
 */

public interface Callback<T> {
    void onSuccess(T data);

    void onFailure();
}
