package com.example.jh.rxhapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.db.DbUtils;
import com.example.jh.rxhapp.fragment.Tab1Fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DataBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initDataBase();
    }

    private void initDataBase() {
        DbUtils instants = DbUtils.getInstants(this);
        instants.insert("renxiaohui",18);
        instants.insert("meng",19);
        instants.qurey();
    }


}
