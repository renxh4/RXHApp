package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by xiaohui on 2017/9/15.
 */

public class CustomLin extends ScrollView {

    private float fristy;
    private float fristx;
    private float lastx;
    private float lasty;

    public CustomLin(Context context) {
        super(context);
    }

    public CustomLin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d("mmm", "onInterceptTouchEvent");

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
               // Log.d("mmm", "ACTION_DOWN");
                fristx = ev.getX();
                fristy = ev.getY();
                int abs = (int) Math.abs(lastx - fristx);

                break;

            case MotionEvent.ACTION_MOVE:
               // Log.d("mmm", "ACTION_MOVE");
                lastx = ev.getX();
                lasty = ev.getY();

                break;

            case MotionEvent.ACTION_UP:
               // Log.d("mmm", "ACTION_UP");

                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                 Log.d("mmmonTouchEvent", "ACTION_DOWN");

                break;

            case MotionEvent.ACTION_MOVE:
                 Log.d("mmmonTouchEvent", "ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                   Log.d("mmmonTouchEvent", "ACTION_UP");
                break;

        }

        return super.onTouchEvent(ev);
    }
}
