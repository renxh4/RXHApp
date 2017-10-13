package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.utils.DanLiDemo;

public class InstanstActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_instanst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("单例模式");
        DanLiDemo instance = DanLiDemo.getInstance();
    }


}
