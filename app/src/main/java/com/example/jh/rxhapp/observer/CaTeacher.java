package com.example.jh.rxhapp.observer;

/**
 * Created by xiaohui on 2017/10/19.
 */

public class CaTeacher {

    private CaStudent mCallback;

    //注册事件
    public void register(CaStudent callBack) {
        this.mCallback = callBack;
    }

    public void setWork(String work) {
        mCallback.onCallBack(work);
    }
}
