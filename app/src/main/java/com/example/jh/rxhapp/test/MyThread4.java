package com.example.jh.rxhapp.test;

import android.util.Log;

/**
 * Created by xiaohui on 2018/7/31.
 */

public class MyThread4 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            Log.d("mmm",this.getName()+"再吃第"+i+"包子");
        }
    }
}
