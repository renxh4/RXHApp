package com.example.jh.rxhapp.proxy;

/**
 * Created by xiaohui on 2018/8/15.
 */

public class ProxySubject implements Subject {

    private RealSubjiect mRealSubject;

    public ProxySubject() {
        if (mRealSubject == null) {
            mRealSubject = new RealSubjiect();
        }
    }

    @Override
    public void request() {
        //  to do something
        mRealSubject.request();
        //  to do something
    }
}
