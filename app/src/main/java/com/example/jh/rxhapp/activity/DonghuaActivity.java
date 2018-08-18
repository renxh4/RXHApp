package com.example.jh.rxhapp.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.jh.rxhapp.R;

public class DonghuaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donghua);
        mButton = (Button) findViewById(R.id.button);
        //xml view动画
        /*Animation animation = AnimationUtils.loadAnimation(this, R.anim.set);
        mButton.startAnimation(animation);*/
        mButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //view动画 代码
//                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//                alphaAnimation.setDuration(1000);
//                mButton.startAnimation(alphaAnimation);
                //帧动画
                /*mButton.setBackgroundResource(R.drawable.zhen_jj);
                AnimationDrawable drawable = (AnimationDrawable) mButton.getBackground();
                drawable.start();*/
                //属性动画 代码
                //ObjectAnimator.ofFloat(mButton,"translationY",-mButton.getHeight()).start();
               /* ObjectAnimator backgroundColor = ObjectAnimator.ofInt(mButton, "backgroundColor", 0XFFFF8080, 0XFF8080FF);
                backgroundColor.setDuration(3000);
                backgroundColor.setEvaluator(new ArgbEvaluator());
                backgroundColor.setRepeatCount(ValueAnimator.INFINITE);
                backgroundColor.setRepeatMode(ValueAnimator.REVERSE);
                backgroundColor.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                    }
                });
                backgroundColor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                    }
                });
                backgroundColor.start();*/
                /*AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        ObjectAnimator.ofFloat(mButton, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(mButton, "rotationY", 0, 180),
                        ObjectAnimator.ofFloat(mButton, "rotation", 0, -90),
                        ObjectAnimator.ofFloat(mButton, "translationX", 0, 90),
                        ObjectAnimator.ofFloat(mButton, "translationY", 0, 90),
                        ObjectAnimator.ofFloat(mButton, "scaleX", 1, 1.5f),
                        ObjectAnimator.ofFloat(mButton, "scaleY", 1, 0.5f),
                        ObjectAnimator.ofFloat(mButton, "alpha", 1, 0.25f, 1)

                );
                animatorSet.setDuration(5000).start();*/
                //属性动画 xml
               /* AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(DonghuaActivity.this, R.animator.set);
                animator.setTarget(mButton);
                animator.setInterpolator(new LinearInterpolator());
                animator.start();*/
                //profremAnimator();
                profremAnimator1(mButton, mButton.getWidth(), 500);
                break;
            default:
        }
    }

    private void profremAnimator1(final View view, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            //持有一个整型估值器，方便下边调用
            IntEvaluator intEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前动画的进度， 1~100
                int animatedValue = (int) animation.getAnimatedValue();
                Log.d("mmm", "current value=" + animatedValue);

                //获取当前进度占整个动画的比例 浮点型 0~1
                float animatedFraction = animation.getAnimatedFraction();
                //直接调用估值器，通过比例计算宽度，赋值给button
                view.getLayoutParams().width = intEvaluator.evaluate(animatedFraction, start, end);
                view.requestLayout();
            }
        });
        valueAnimator.setDuration(5000).start();
    }

    private void profremAnimator() {
        ViewWrapper viewWrapper = new ViewWrapper(mButton);
        ObjectAnimator.ofInt(viewWrapper, "width", 0, 500).setDuration(5000).start();
    }

    private static class ViewWrapper {
        private View mTagert;

        public ViewWrapper(View view) {
            this.mTagert = view;
        }

        public int getWidth() {
            return mTagert.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTagert.getLayoutParams().width = width;
            mTagert.requestLayout();
        }
    }
}
