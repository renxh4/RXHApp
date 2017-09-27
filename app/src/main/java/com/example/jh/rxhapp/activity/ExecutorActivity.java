package com.example.jh.rxhapp.activity;

import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;

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
        scheduledExecutorService.schedule(runnable,2000, TimeUnit.MILLISECONDS);
        //2s后执行每隔一秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(runnable,2000,1000,TimeUnit.MILLISECONDS);

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.execute(runnable);


    }
}
