package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xiaohui on 2018/1/9.
 */
//探讨事件的分发
public class CustomeLin extends LinearLayout {


    public CustomeLin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeLin(Context context) {
        super(context);
    }

    public CustomeLin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //用来处理事件的分发。如果事件能够传递带此view，此方法一定会被调用，返回结果受当前view的onTouchEvent和
        //下级view的dispatchTouchEvent影响，表示是否消耗掉当前事件
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //上述方法内部调用，用来判断是否拦截某个事件，假如当前view拦截某个事件，那么同一个事件序列中，此方法不会
        //再调用，返回结果表示是否拦截事件
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //在dispatchTouchEvent方法中调用，用来处理点击事件，返回结果表示是否消耗事件，如果不消耗，在同一事件序列中
        //当前view无法再次接到事件
        return super.onTouchEvent(event);
    }
}
