package com.example.jh.rxhapp.weight;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by xiaohui on 2017/8/28.
 */

public class hello extends View {
    private static final String TAG = "SwitchBar";
    private static final long DEFAULT_DURATION = 5000;
    private Paint mPaint;
    //主要画笔
    private TextPaint mTextPaint;
    // 文字画
    private RectF mRectF;
    //圆角矩阵
    private float mOverlayRadius;
    //覆盖物半径
    private Path mClipPath;
    //裁剪区域
    private float[] mCurrentPosition = new float[2];
    //遮盖物的坐标点
    boolean misLeft = true;
    //tab选中位置
    private boolean isAnimation;
    //是否正在切换条目中
    private float mTotalleft;
    //view的left
    private float mTotalTop;
    //view的top
    private float mTotalRight;
    //view的right
    private float mTotalBottom;
    //view的bottom
    private float mTotalHeight;
    //bottom－top
    private int mBaseLineY;
    //文字剧中线条
    private String[] mText = {"1P", "2P"};
    //tab 文字内容
    private OnClickListener mOnClickListener;
    private int colorRed = Color.rgb(0xff, 0x21, 0x10);
    private int colorPurple = Color.rgb(0x88, 0x88, 0xff);

    public hello(Context context) {
        this(context, null);
    }

    public hello(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public hello(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* * * 设置tab文字 * @param text 文字内容 * * */
    public void setText(String[] text) {
        mText = text;
        invalidate();
    }

    /* * * 设置tab文字的size * @param size 文字大小 * * */
    public void setTestSize(int size) {
        mTextPaint.setTextSize(size);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        mBaseLineY = (int) (getHeight() / 2 - top / 2 - bottom / 2);
        invalidate();
    }

    /* * * 切换条目 动画默认500ms * @param isLeft true为左边的条目 * * */
    public void switchButton(boolean isLeft) {
        switchB(isLeft, DEFAULT_DURATION);
    }

    public void switchButton(boolean isLeft, long duration) {
        switchB(isLeft, duration);
    }

    /* * * 添加tab切换监听 * * */
    public void setOnClickListener(@Nullable OnClickListener listener) {
        mOnClickListener = listener;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(48);
        mTextPaint.setTypeface(Typeface.SERIF);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width / 3;
        mTotalHeight = height - 10;
        mTotalleft = 5;
        mTotalTop = 5;
        mTotalRight = width - 5;
        mTotalBottom = height - 5;
        //圆角矩形
        mRectF = new RectF(mTotalleft, mTotalTop, mTotalRight, mTotalBottom);
        //path的形状
        RectF f = new RectF(mTotalleft + mTotalHeight / 2, mTotalTop - 5, mTotalRight - mTotalHeight / 2, mTotalBottom + 5);
        //圆的半径
        mOverlayRadius = (mTotalRight - mTotalleft) * 0.36F;
        mClipPath = new Path();
        mClipPath.setFillType(Path.FillType.WINDING);
        mClipPath.addRect(f, Path.Direction.CW);
        mClipPath.addCircle(mTotalleft + mTotalHeight / 2, mTotalTop + mTotalHeight / 2, mTotalHeight / 2 + 6, Path.Direction.CW);
        mClipPath.addCircle(mTotalRight - mTotalHeight / 2, mTotalTop + mTotalHeight / 2, mTotalHeight / 2 + 6, Path.Direction.CW);
        mCurrentPosition = new float[2];
        mCurrentPosition[0] = mTotalleft + mTotalHeight / 2 + 30;
        mCurrentPosition[1] = mTotalBottom;
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        mBaseLineY = (int) (height / 2 - top / 2 - bottom / 2);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(event.getX() > getWidth() / 2 ? 1 : 0, mText[event.getX() > getWidth() / 2 ? 1 : 0]);
            }
            switchButton(event.getX() < getWidth() / 2);
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStroke(canvas);
        drawOverlay(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText(mText[0], getWidth() / 4, mBaseLineY, mTextPaint);
        canvas.drawText(mText[1], getWidth() / 4 * 3, mBaseLineY, mTextPaint);
    }

    private void drawOverlay(Canvas canvas) {
        mPaint.setColor(mCurrentPosition[0] > getWidth() / 2 ? colorPurple : colorRed);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1);
        canvas.save();
        canvas.clipPath(mClipPath);
        canvas.drawCircle(mCurrentPosition[0], mCurrentPosition[1],
                mOverlayRadius, mPaint);
        canvas.restore();
    }

    private void drawStroke(Canvas canvas) {
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(mRectF, 1000, 1000, mPaint);
    }

    private void switchB(boolean isLeft, long duration) {
        if (misLeft == isLeft || isAnimation) return;
        Path overlayPath = new Path();
        RectF rectF = new RectF(mTotalleft + mTotalHeight / 2 + 30, mTotalBottom - mOverlayRadius, mTotalRight - mTotalHeight / 2 - 30, mTotalBottom + mOverlayRadius);
        if (isLeft) {
            overlayPath.addArc(rectF, 0, 180);//右到左
        } else {
            overlayPath.addArc(rectF, 180, -180);//左到右
            PathMeasure pathMeasure = new PathMeasure(overlayPath, false);
            startPathAnim(pathMeasure, duration);
        }
    }

    private void startPathAnim(final PathMeasure pathMeasure, long duration) {
        // 0 － getLength()
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());

        valueAnimator.setDuration(duration);
        // 减速插值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                //获取当前点坐标封装到mCurrentPosition
                pathMeasure.getPosTan(value, mCurrentPosition, null);
                postInvalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                misLeft = !misLeft;
                isAnimation = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        valueAnimator.start();
    }

    public interface OnClickListener {
        void onClick(int position, String text);
    }
}


