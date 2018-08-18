package com.example.jh.rxhapp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiaohui on 2018/8/15.
 */

public class ProxyHandler implements InvocationHandler {

    private RealSubjiect mRealSubject;

    public ProxyHandler() {
        if (mRealSubject == null) {
            mRealSubject = new RealSubjiect();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //before
        Object result = method.invoke(mRealSubject, args);
        //after
        return result;
    }
}
