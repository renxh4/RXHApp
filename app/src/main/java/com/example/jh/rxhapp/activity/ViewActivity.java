package com.example.jh.rxhapp.activity;

import android.animation.ValueAnimator;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.jh.rxhapp.R;

public class ViewActivity extends BaseActivity {

    private Button button;
    private float mLastX;
    public float mLastY;
    private Button button1;
    public int startX = 0;
    public int delayX = -100;
    private LinearLayout linearLayout;

    @Override
    public int setMainView() {
        return R.layout.activity_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void animite() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                linearLayout.scrollTo(startX + (int) (animatedFraction * delayX), 0);
            }
        });
        valueAnimator.start();
    }

    private void initView() {
        button = (Button) findViewById(R.id.view_button);
        button1 = (Button) findViewById(R.id.view_button1);
        linearLayout = (LinearLayout) findViewById(R.id.activity_view);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animite();
            }
        });
    }
}
