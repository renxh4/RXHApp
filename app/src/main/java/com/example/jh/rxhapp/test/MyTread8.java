package com.example.jh.rxhapp.test;

import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by xiaohui on 2018/7/31.
 */

public class MyTread8 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Log.d("mmm", "i=" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.d("mmm", "外部似乎要停掉我，拜拜");
                return;
            }
        }
    }
}
