package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.disklrucache.DiskLruCache;

import java.util.logging.Logger;


public class SwitchBar extends View {

    private Paint paint;
    private Paint textPain;
    private int rectTop;
    private int rectLeft;
    private int rectRight;
    private int rectBottom;
    private int rectHeight;
    private int rectWith;
    private int mBaseLineY;
    private RectF rect;
    private float mOverlayRadius;
    private float[] mCurrentPosition;
    private Path mClipPath;

    public SwitchBar(Context context) {
        super(context);
        init();
    }

    public SwitchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //图像画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置线条粗细
        paint.setStrokeWidth(10);
        //设置颜色
        paint.setColor(Color.BLACK);
        //设置空心
        paint.setStyle(Paint.Style.STROKE);
        //设置线冒的样式
        paint.setStrokeCap(Paint.Cap.ROUND);
        //设置抗锯齿
        paint.setAntiAlias(true);


        //文字画笔
        textPain = new TextPaint();
        textPain.setColor(Color.BLACK);
        textPain.setTextSize(48);
        //设置字体
        textPain.setTypeface(Typeface.SERIF);
        //设置加粗
        textPain.setFakeBoldText(true);
        textPain.setAntiAlias(true);
        //设置文字对其方式
        textPain.setTextAlign(Paint.Align.CENTER);
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取view的宽度
        rectTop = 100;
        rectLeft = 100;
        rectRight = 650;
        rectBottom = 300;
        rectHeight = 200;
        rectWith = 550;
        //圆的半径
        rect = new RectF(rectLeft, rectTop, rectRight, rectBottom);
        mClipPath = new Path();
        mClipPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        mClipPath.addRect(rect, Path.Direction.CCW);
        mClipPath.addCircle(rectWith / 4 * 3 + rectLeft, rectHeight / 2 + rectTop, rectWith/4, Path.Direction.CCW);
        //mClipPath.addCircle(mTotalRight - mTotalHeight / 2, mTotalTop + mTotalHeight / 2, mTotalHeight / 2 + 6, Path.Direction.CW);
        Paint.FontMetrics fontMetrics = textPain.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        mBaseLineY = (int) (rectHeight / 2 + rectTop - top / 2 - bottom / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas);
        drawRountRect(canvas);
        drawText(canvas);


    }

    private void drawArc(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
        int x = rectWith / 4 + rectLeft;
       // canvas.save();
        //canvas.clipPath(mClipPath);
        canvas.drawCircle(rectWith / 4 * 3 + rectLeft, rectBottom,
                rectWith * 0.4f, paint);
        //canvas.restore();
    }

    private void drawText(Canvas canvas) {
        canvas.drawText("1P", rectWith / 4 + rectLeft, mBaseLineY, textPain);
        canvas.drawText("2P", rectWith / 4 * 3 + rectLeft, mBaseLineY, textPain);
    }

    private void drawRountRect(Canvas canvas) {
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rect, 500, 500, paint);
    }

}
