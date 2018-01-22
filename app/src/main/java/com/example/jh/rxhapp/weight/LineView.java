package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xiaohui on 2017/11/17.
 */

public class LineView extends View {

    private Paint mPaint;
    private int mStarX = 100;
    private int mStartY = 100;
    private Path mPath;
    private Paint mPaint2;
    private int mDefultWidthSize = 500;
    private int mDefultHeightSize = 30;

    public LineView(Context context) {
        super(context);
        initView();
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        int a = 1;
        double c= 1.272;

        //初始化点画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(30);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //path 的画笔
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint2.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint2.setStrokeWidth(10);
        mPaint2.setStrokeJoin(Paint.Join.ROUND);// 边框宽度 - 10

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        //Log.d("mmmcustomview", getTop() + "/" + measuredHeight + "/" + getPaddingTop());
        mStartY=measuredHeight/2;
        mStarX=0;
        //初始化path
        mPath = new Path();
        mPath.moveTo(mStarX, mStartY);
        mPath.lineTo(mStarX + 200, mStartY);
        mPath.lineTo(mStarX + 400, mStartY);
        mPath.rLineTo(-20, -20);
        mPath.moveTo(mStarX + 400, mStartY);
        mPath.rLineTo(-20, 20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint2);
        canvas.drawPoint(mStarX + 200, mStartY, mPaint);
        canvas.drawPoint(mStarX, mStartY, mPaint);
    }


    private int measureWidth(int measureSpec) {

        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);

        int specSize = MeasureSpec.getSize(measureSpec);


        if (specMode == MeasureSpec.EXACTLY) {

            // We were told how big to be

            result = specSize;

        } else {

            // Measure the text

            result = mDefultWidthSize;

            if (specMode == MeasureSpec.AT_MOST) {

                // Respect AT_MOST value if that was what is called for by

                // measureSpec

                result = Math.min(result, specSize);

            }

        }


        return result;

    }

    private int measureHeight(int measureSpec) {

        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);

        int specSize = MeasureSpec.getSize(measureSpec);


        if (specMode == MeasureSpec.EXACTLY) {

            // We were told how big to be

            result = specSize;

        } else {

            // Measure the text (beware: ascent is a negative number)

            result = mDefultHeightSize;

            if (specMode == MeasureSpec.AT_MOST) {

                // Respect AT_MOST value if that was what is called for by

                // measureSpec

                result = Math.min(result, specSize);

            }

        }

        return result;

    }
}
