package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;

public class CustomViewActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
