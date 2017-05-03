package com.example.jh.rxhapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jh.rxhapp.activity.BaseActivity;
import com.example.jh.rxhapp.activity.ViewPagerActivity;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        addMainView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }
}
