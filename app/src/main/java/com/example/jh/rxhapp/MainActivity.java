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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Button toobarButton;
    private EditText appBarEdittext;
    private AppBarLayout app_bar;


    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initToobar();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        toobarButton = (Button) findViewById(R.id.toobar_button);
        toobarButton.setOnClickListener(this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        app_bar = (AppBarLayout) findViewById(R.id.appbar);
        appBarEdittext = (EditText) findViewById(R.id.appbar_edittext);
        //appBarEdittext.setFocusable(false);
        initAppbar(app_bar);
    }

    private void initAppbar(AppBarLayout app_bar) {
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        toobarButton.setVisibility(View.GONE);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                       // toobarButton.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                            toobarButton.setVisibility(View.GONE);
                        }

                    }
                }
            }
        });
    }

    //浸入式状态栏
    private void initToobar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toobar_button:
                this.startActivity(new Intent(this, ViewPagerActivity.class));
                break;
        }

    }
}
