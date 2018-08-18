package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiaohui on 2018/7/9.
 */

public class BigImageView extends View {

    private BitmapRegionDecoder mDecoder;
    private int mImageWidth;
    private int mImageHeight;
    //图片绘制的区域
    private Rect mRect = new Rect();
    private static final BitmapFactory.Options options = new BitmapFactory.Options();

    static {
        options.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    public BigImageView(Context context) {
        super(context);
        init();
    }

    public BigImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BigImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    /**
     * 自定义view的入口，设置图片流
     *
     * @param path 图片路径
     */
    public void setFilePath(String path) {
        try {
            //初始化BitmapRegionDecoder
            mDecoder = BitmapRegionDecoder.newInstance(path, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            //便是只加载图片属性，不加载bitmap进入内存
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            //图片的宽高
            mImageWidth = options.outWidth;
            mImageHeight = options.outHeight;
            Log.d("mmm", "图片宽=" + mImageWidth + "图片高=" + mImageHeight);

            requestLayout();
            invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取本view的宽高
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();


        //默认显示图片左上方
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mRect.left + measuredWidth;
        mRect.bottom = mRect.top + measuredHeight;
    }

    //第一次按下的位置
    private float mDownX;
    private float mDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                //移动的距离
                int xDistance = (int) (moveX - mDownX);
                int yDistance = (int) (moveY - mDownY);
                Log.d("mmm", "mDownX=" + mDownX + "mDownY=" + mDownY);
                Log.d("mmm", "movex=" + moveX + "movey=" + moveY);
                Log.d("mmm", "xDistance=" + xDistance + "yDistance=" + yDistance);
                Log.d("mmm", "mImageWidth=" + mImageWidth + "mImageHeight=" + mImageHeight);
                Log.d("mmm", "getWidth=" + getWidth() + "getHeight=" + getHeight());
                if (mImageWidth > getWidth()) {
                    mRect.offset(-xDistance, 0);
                    checkWidth();
                    //刷新页面
                    invalidate();
                    Log.d("mmm", "刷新宽度");
                }
                if (mImageHeight > getHeight()) {
                    mRect.offset(0, -yDistance);
                    checkHeight();
                    invalidate();
                    Log.d("mmm", "刷新高度");
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = mDecoder.decodeRegion(mRect, options);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    /**
     * 确保图不划出屏幕
     */
    private void checkWidth() {


        Rect rect = mRect;
        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        if (rect.right > imageWidth) {
            rect.right = imageWidth;
            rect.left = imageWidth - getWidth();
        }

        if (rect.left < 0) {
            rect.left = 0;
            rect.right = getWidth();
        }
    }

    /**
     * 确保图不划出屏幕
     */
    private void checkHeight() {

        Rect rect = mRect;
        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        if (rect.bottom > imageHeight) {
            rect.bottom = imageHeight;
            rect.top = imageHeight - getHeight();
        }

        if (rect.top < 0) {
            rect.top = 0;
            rect.bottom = getHeight();
        }
    }
}
