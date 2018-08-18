package com.example.jh.rxhapp.test;

import android.util.Log;

/**
 * Created by xiaohui on 2018/7/31.
 */

public class MyThread7 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Log.d("mmm",this.getName()+"正在杀敌"+i);
        }
    }
}
