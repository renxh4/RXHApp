package com.example.jh.rxhapp.weight;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by xiaohui on 2017/9/13.
 */

public class CustomButton extends Button {
    private int mLastX;
    private int mLastY;
    private int x;
    private int y;

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取现对于屏幕的xy坐标
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        Log.d("mmm", "rawX" + rawX + "rawY" + rawY);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = rawX;
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - mLastX;
                int offsetY = rawY - mLastY;
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                mLastX=rawX;
                mLastY=rawY;
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
        }
        return true;
    }
}
