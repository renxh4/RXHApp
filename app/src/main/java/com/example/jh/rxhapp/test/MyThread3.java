package com.example.jh.rxhapp.test;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaohui on 2018/7/30.
 */

public class MyThread3 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf.format(date);
            Log.d("mmm", "每10是打印一次" + str);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
