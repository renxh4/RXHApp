package com.example.jh.rxhapp.activity;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;

public class ThreadLocalActivity extends BaseActivity {

    private ThreadLocal<Boolean> mThreadLocal;

    @Override
    public int setMainView() {
        return R.layout.activity_thread_local;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initThread();
        setToobarTitle("ThreadLocal");
    }

    private void initThread() {
        mThreadLocal = new ThreadLocal<>();
        mThreadLocal.set(true);
        Log.d("mmm","当前线程"+Thread.currentThread()+"ThreadLocal存储"+ mThreadLocal.get());
        new Thread("thread1"){
            @Override
            public void run() {
                super.run();
                mThreadLocal.set(false);
                Log.d("mmm","当前线程"+Thread.currentThread()+"ThreadLocal存储"+ mThreadLocal.get());
            }
        }.start();
        new Thread("thread2"){
            @Override
            public void run() {
                super.run();
                Log.d("mmm","当前线程"+Thread.currentThread()+"ThreadLocal存储"+ mThreadLocal.get());
            }
        }.start();
    }
}
