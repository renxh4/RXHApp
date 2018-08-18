package com.example.jh.rxhapp.mvp2.v;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;

public class Base1Activity extends AppCompatActivity implements BaseView {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base1);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("正在加载数据");
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dissMissLoading() {
        mProgressDialog.dismiss();
    }
}
