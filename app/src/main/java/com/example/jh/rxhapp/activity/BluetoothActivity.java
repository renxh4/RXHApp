package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;

public class BluetoothActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_bluetooth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("蓝牙");
    }
}
