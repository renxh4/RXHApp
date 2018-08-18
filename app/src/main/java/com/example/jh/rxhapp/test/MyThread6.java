package com.example.jh.rxhapp.test;

import android.util.Log;

/**
 * Created by xiaohui on 2018/7/31.
 */

public class MyThread6 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Log.d("mmm", this.getName() + "i=" + i);
            if (this.getName().equals("刘德华")) {
                Thread.yield();//退回到就绪状态
            }
        }
    }
}
