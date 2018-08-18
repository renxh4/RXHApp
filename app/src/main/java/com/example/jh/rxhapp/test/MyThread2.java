package com.example.jh.rxhapp.test;

import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by xiaohui on 2018/7/30.
 */

public class MyThread2 extends Thread {
    @Override
    public void run() {
        double sum = 0;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 20000000; j++) {
                sum += (Math.E + Math.PI) / j;
                if (j % 20000 == 0) {
                    Thread.yield();//让当前线程退回到"就绪状态"；
                }

            }
        }
        Log.d("mmm", this.getName() + "计算完毕");

    }
}
