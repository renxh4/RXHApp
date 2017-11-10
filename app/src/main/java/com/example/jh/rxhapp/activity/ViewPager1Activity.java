package com.example.jh.rxhapp.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.weight.ScrollingMessageViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPager1Activity extends BaseActivity {

    private ScrollingMessageViewPager mMessageViewPager;
    private LinearLayout mIndicator;
    private ArrayList<Integer> mIntegers;
    private List<View> mIndicatorList = new ArrayList<>();

    @Override
    public int setMainView() {
        return R.layout.activity_view_pager1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("无限轮播ViewPager");
        mIntegers = new ArrayList<>();
        mIntegers.add(R.drawable.images1);
        mIntegers.add(R.drawable.images2);
        mIntegers.add(R.drawable.images3);
        mMessageViewPager = (ScrollingMessageViewPager) findViewById(R.id.wuxian_viewpager);
        mIndicator = (LinearLayout) findViewById(R.id.intercater);
        initIndicator();
        mMessageViewPager.startViewPager(this, mIntegers, mIndicatorList);
    }

    private void initIndicator() {
        mIndicator.removeAllViews();
        mIndicatorList.clear();
        for (int i = 0; i < mIntegers.size(); i++) {
            View view = View.inflate(this, R.layout.scrolling_message_indicator_view, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.index);
            if (i == 0) {
                imageView.setImageResource(R.drawable.red_two);
            } else {
                imageView.setImageResource(R.drawable.white_two);
            }
            mIndicator.addView(view);
            mIndicatorList.add(view);
        }
    }
}
