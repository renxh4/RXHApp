package com.example.jh.rxhapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.service.MyService;

public class TestServiceActivity extends BaseActivity {


    //测试handler
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(TestServiceActivity.this, msg.what + "/", Toast.LENGTH_SHORT).show();
                textView.setText(msg.what + "/" + Thread.currentThread());
            }
            return false;
        }

    });
    private TextView textView;
    private TextView textView1;
    private VelocityTracker obtain;
    private int mPointerId;
    private int mMaxVelocity;
    private Runnable runnable;

    @Override
    public int setMainView() {
        return R.layout.activity_test_service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendHandler();
        initView();
        newThread();
    }

    private void sendHandler() {
        Message msg = new Message();
        msg.what = 1;
        handler.sendMessageDelayed(msg, 1000);
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("post" + Thread.currentThread());
            }
        };
    }

    private void newThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("mmm", Thread.currentThread() + "/");
                handler.post(runnable);
            }
        }).start();
    }

    private void initView() {
        mMaxVelocity = ViewConfiguration.get(this).getMaximumFlingVelocity();
        textView = (TextView) findViewById(R.id.text_test_service);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        ScrollView linearLayout = (ScrollView) findViewById(R.id.text_service_Lin);
        textView1 = (TextView) findViewById(R.id.text_service_text);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("mmmm", "onTouch");
                if (obtain == null) {
                    obtain = VelocityTracker.obtain();
                }
                obtain.addMovement(event);
                int action = event.getAction();
                Log.d("mmmm", action + "/");
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("mmmm", "ACTION_DOWN");
                        //求第一个触点的id， 此时可能有多个触点，但至少一个
                        mPointerId = event.getPointerId(0);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("mmmm", "ACTION_MOVE");


                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("mmmm", "ACTION_UP");
                        obtain.computeCurrentVelocity(1000, mMaxVelocity);
                        final float velocityX = obtain.getXVelocity(mPointerId);
                        final float velocityY = obtain.getYVelocity(mPointerId);
                        textView1.setText("速度x" + velocityX + "速度y" + velocityY);
                        break;

                }
                return false;
            }
        });

    }
}
