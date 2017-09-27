package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xiaohui on 2017/9/14.
 */

public class CustomViewpager extends ViewGroup {

    private Scroller scroller;
    private int scaledPagingTouchSlop;
    private int leftBorder;
    private int rightBorder;
    private float rawdDownX;
    private float lastMove;
    private float rawMoveX;

    public CustomViewpager(Context context) {
        super(context);
        initView(context);
    }


    public CustomViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomViewpager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        scroller = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        //获取最小滑动距离
        scaledPagingTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //测量子布局
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);

        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                //为每一个view进行布局
                childAt.layout(i * childAt.getMeasuredWidth(), 0, (i + 1) * childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            }
        }

        //获取左右边界的值
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //当第一次记录点击的位置
                rawdDownX = ev.getRawX();
                lastMove = rawdDownX;
                break;
            case MotionEvent.ACTION_MOVE:
                rawMoveX = ev.getRawX();
                float delay = Math.abs(rawMoveX - rawdDownX);
                lastMove = rawMoveX;
                //计算出移动的距离大于最小移动距离就拦截事件
                if (delay > scaledPagingTouchSlop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                rawMoveX = event.getRawX();
                int move = (int) (lastMove - rawMoveX);
                if (getScrollX() + move < leftBorder) {
                    //getScrollX()可以获取到当前view的左边缘和当前view内容的左边缘距离，
                    // 如果当前view的左边缘在view内容的左边缘右边时值为正数，反之则为负数
                    //如果超出左边边界就回到左边边界
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + move + getWidth() > rightBorder) {
                    //如果超出右边边界，就回到右边边界
                    int width = getWidth();
                    scrollTo(rightBorder - width, 0);
                    Log.d("mmm", "with" + width + "leftBorder" + leftBorder + "rightBorder" + rightBorder + "相减" + (rightBorder - width));
                    return true;
                }
                scrollBy(move, 0);
                lastMove = rawMoveX;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                //首先先判断应该滚到那个布局
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                //判断滚到该布局还需要多少距离
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                scroller.startScroll(getScrollX(), 0, dx, 0);
                Log.d("mmm1",getScrollX()+"dx"+dx);
                invalidate();
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }

    //当view初始化完成后调用
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus){
            //获取宽高
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
        }
    }
}
