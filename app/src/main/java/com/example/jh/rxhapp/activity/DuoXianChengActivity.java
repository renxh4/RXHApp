package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.test.BaoZiPu;
import com.example.jh.rxhapp.test.GetThread;
import com.example.jh.rxhapp.test.MyClass1;
import com.example.jh.rxhapp.test.MyClass2;
import com.example.jh.rxhapp.test.MyThread11;
import com.example.jh.rxhapp.test.MyThread2;
import com.example.jh.rxhapp.test.MyThread22;
import com.example.jh.rxhapp.test.MyThread3;
import com.example.jh.rxhapp.test.MyThread4;
import com.example.jh.rxhapp.test.MyThread6;
import com.example.jh.rxhapp.test.MyThread7;
import com.example.jh.rxhapp.test.MyTread8;
import com.example.jh.rxhapp.test.SetThread;
import com.example.jh.rxhapp.utils.MyThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class DuoXianChengActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_xian_cheng);
        //text1();
        //text2();
        //text3();
        //text4();
        //  text5();
        //text6();
        // text7();
        // text8();
        //text9();
        //text10();
        // text11();
        text12();
    }

    private void text12() {
        BaoZiPu baoZiPu = new BaoZiPu();

        GetThread getThread = new GetThread(baoZiPu);
        SetThread setThread = new SetThread(baoZiPu);

        getThread.start();
        setThread.start();
    }

    private void text11() {
        ReentrantLock lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                //做一些处理
            } catch (Exception e) {

            } finally {
                lock.unlock();//释放锁
            }
        } else {
            //如果获取不到锁怎么办
        }
    }

    private void text10() {
        ReentrantLock lock = new ReentrantLock();
        //获取锁
        lock.lock();
        try {
            //处理任务
        } catch (Exception e) {

        } finally {
            //释放锁
            lock.unlock();
        }

    }

    private void text9() {
        MyClass2 myClass2 = new MyClass2();
        MyClass1 myClass1 = new MyClass1();

        MyThread11 myThread11 = new MyThread11(myClass1, myClass2);
        MyThread22 myThread22 = new MyThread22(myClass1, myClass2);

        myThread11.start();
        myThread22.start();
    }

    private void text8() {
        MyTread8 myTread8 = new MyTread8();
        myTread8.start();

        Log.d("mmm", "主线程等待3秒");
        try {
            Thread.sleep(1000 * 3);
            Log.d("mmm", "主线程醒来，干掉开出的线程");
            myTread8.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 守护线程，在android中好象有点问题
     */
    private void text7() {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ClildThread());
                childThread.setDaemon(true);
                childThread.start();
                Log.d("mmm", "I'm main thread...");
            }
        });
        mainThread.start();
    }


    private void text6() {
        MyThread6 myThread1 = new MyThread6();
        MyThread6 myThread2 = new MyThread6();

        myThread1.setName("刘德华");
        myThread2.setName("邓丽君");

        myThread1.start();
        myThread2.start();

    }

    private void text5() {
        MyThread4 myThread1 = new MyThread4();
        MyThread4 myThread2 = new MyThread4();

        myThread1.setName("王力宏");
        myThread2.setName("刘德华");


        try {
            myThread1.start();
            myThread1.join();
            myThread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void text4() {
        new MyThread3().start();
    }

    //线程优先级
    private void text3() {
        MyThread2 myThread1 = new MyThread2();
        MyThread2 myThread2 = new MyThread2();
        MyThread2 myThread3 = new MyThread2();
        MyThread2 myThread4 = new MyThread2();
        MyThread2 myThread5 = new MyThread2();

        myThread1.setName("t1");
        myThread2.setName("t2");
        myThread3.setName("t3");
        myThread4.setName("t4");
        myThread5.setName("t5");

        myThread1.setPriority(10);
        myThread2.setPriority(1);
        myThread3.setPriority(1);
        myThread4.setPriority(1);
        myThread5.setPriority(1);

        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();

    }

    private void text2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //do something
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 設置線程名稱
     */
    public void text1() {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread threa3 = new MyThread();
        //设置线程名称
        thread1.setName("张学友");
        thread2.setName("张曼玉");
        threa3.setName("李玉刚");
        threa3.start();
        thread1.start();
        thread2.start();
        for (int k = 0; k < 100; k++) {
            //获取当前线程的对象
            Log.d("mmm", Thread.currentThread().getName() + "k=" + k);
        }
    }


    class ClildThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                Log.d("mmm", "I'm child thread..");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
