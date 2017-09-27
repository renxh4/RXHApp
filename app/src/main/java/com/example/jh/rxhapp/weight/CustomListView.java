package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by xiaohui on 2017/9/15.
 */

public class CustomListView extends RecyclerView {
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("mmmonInterceptTouc2", "ACTION_DOWN");

                break;

            case MotionEvent.ACTION_MOVE:
                Log.d("mmmonInterceptTouc2", "ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d("mmmonInterceptTouc2", "ACTION_UP");
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
