package com.example.jh.rxhapp.activity;

import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_executor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //做一些事情
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(runnable);

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        executorService1.execute(runnable);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        //2s后执行
        scheduledExecutorService.schedule(runnable, 2000, TimeUnit.MILLISECONDS);
        //2s后执行每隔一秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(runnable, 2000, 1000, TimeUnit.MILLISECONDS);

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.execute(runnable);


        text1();


    }

    private void text1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 300, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            Log.d("mmm", "线程池中的线程数目" + executor.getPoolSize() + "队列中等待执行的数目" + executor.getQueue().size() + "已经执行完的数目" + executor.getCompletedTaskCount());
        }
    }

    class MyTask implements Runnable {

        private final int num;

        public MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            Log.d("mmm", "正在执行task'" + num);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("mmm", "task" + num + "执行完毕");
        }
    }
}
