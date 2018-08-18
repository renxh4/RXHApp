package com.example.jh.rxhapp.test;

import android.util.Log;

/**
 * Created by xiaohui on 2018/8/1.
 */

public class MyThread11 extends Thread {

    private final MyClass1 C1;
    private final MyClass2 C2;

    public MyThread11(MyClass1 myClass1, MyClass2 myClass2) {
        this.C1 = myClass1;
        this.C2 = myClass2;
    }

    @Override
    public void run() {
        synchronized (C1){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //醒来后
            Log.d("mmm","线程1醒来，准备访问C2.show2");
            C2.show2();
        }
    }
}
