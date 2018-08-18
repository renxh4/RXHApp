package com.example.jh.rxhapp.utils;

import android.util.Log;

/**
 * Created by xiaohui on 2018/7/17.
 */

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Log.d("mmm", this.getName() + "i=" + i);
        }
    }
}
