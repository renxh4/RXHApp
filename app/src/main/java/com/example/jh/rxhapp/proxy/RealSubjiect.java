package com.example.jh.rxhapp.proxy;

import android.util.Log;

/**
 * Created by xiaohui on 2018/8/15.
 */

public class RealSubjiect implements Subject {
    @Override
    public void request() {
        Log.d("mmm","调用了真实对象");
    }
}
