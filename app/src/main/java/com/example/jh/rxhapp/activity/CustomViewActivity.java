package com.example.jh.rxhapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.build.Builder;

import java.util.HashMap;

public class CustomViewActivity extends BaseActivity implements View.OnClickListener {

    private Button mButton3;
    private Button mButton2;
    private Button mButton1;
    public int totalsize = 2;
    public int currentSize = 0;
    private HashMap<View, Boolean> mViewBooleanHashMap;
    private HashMap<Integer, Button> mIntegerButtonHashMap;

    @Override
    public int setMainView() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mIntegerButtonHashMap = new HashMap<>();
        mIntegerButtonHashMap.put(1, null);
        mIntegerButtonHashMap.put(2, null);
        mViewBooleanHashMap = new HashMap<>();
        mViewBooleanHashMap.put(mButton1, false);
        mViewBooleanHashMap.put(mButton2, false);
        mViewBooleanHashMap.put(mButton3, false);
        mButton3.post(new Runnable() {
            @Override
            public void run() {
                Log.d("mmmviewpost", "width=" + mButton3.getMeasuredWidth() + "/");
            }
        });
        ViewTreeObserver viewTreeObserver = mButton3.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mButton3.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Log.d("mmmviewTreeObserver", "width=" + mButton3.getMeasuredWidth() + "/");
            }
        });
        Log.d("mmmwith", "width=" + mButton3.getMeasuredWidth() + "/");
        initMeasure();
    }

    private void initMeasure() {
        int measureWidth = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int measureheight = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        mButton3.measure(measureWidth, measureheight);
        Log.d("mmmmeasure", "width=" + mButton3.getMeasuredWidth() + "/");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("mmmwithonFocusChanged", "width=" + mButton3.getMeasuredWidth() + "/");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                setButtonClick(mButton1);
                break;
            case R.id.button2:
                setButtonClick(mButton2);
                break;
            case R.id.button3:
                setButtonClick(mButton3);
                break;
            default:
        }

    }

    private void setButtonClick(Button button1) {
        Boolean boolean1 = mViewBooleanHashMap.get(button1);
        if (currentSize < totalsize || boolean1) {
            if (boolean1) {
                setbuttonNoClick(button1);
            } else {
                setButtonClick1(button1);
            }
        } else {
            moreButton(button1);
        }
    }

    private void setButtonClick1(Button button1) {
        button1.setBackgroundColor(Color.GREEN);
        button1.setText("选中了");
        currentSize++;
        mViewBooleanHashMap.put(button1, true);
        if (currentSize == 1) {
            mIntegerButtonHashMap.put(1, button1);
        } else if (currentSize == 2) {
            mIntegerButtonHashMap.put(2, button1);
        }
    }

    private void setbuttonNoClick(Button button1) {
        button1.setBackgroundColor(Color.RED);
        button1.setText("没选中");
        currentSize--;
        mViewBooleanHashMap.put(button1, false);
        if (currentSize == 1) {
            if (button1 == mIntegerButtonHashMap.get(1)) {
                mIntegerButtonHashMap.put(1, mIntegerButtonHashMap.get(2));
                mIntegerButtonHashMap.put(2, null);
            }

        } else if (currentSize == 0) {
            mIntegerButtonHashMap.put(1, null);
            mIntegerButtonHashMap.put(2, null);
        }
    }

    private void moreButton(Button button1) {
        findfristButton(button1);
        currentButton(button1);
    }

    private void currentButton(Button button1) {
        button1.setBackgroundColor(Color.GREEN);
        button1.setText("选中了");
        mViewBooleanHashMap.put(button1, true);
    }

    private void findfristButton(Button button1) {
        Button button = mIntegerButtonHashMap.get(1);
        button.setText("没选中");
        button.setBackgroundColor(Color.RED);
        mViewBooleanHashMap.put(button, false);
        mIntegerButtonHashMap.put(1, mIntegerButtonHashMap.get(2));
        mIntegerButtonHashMap.put(2, button1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
