package com.example.jh.rxhapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jh.rxhapp.activity.BaseActivity;
import com.example.jh.rxhapp.adapter.MainRecycleAdapter;
import com.example.jh.rxhapp.utils.PermissionUtils;
import com.tencent.rtmp.TXLivePusher;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initSdk();
    }

    private void initSdk() {
        int[] sdkver = TXLivePusher.getSDKVersion();
        if (sdkver != null && sdkver.length >= 3) {
            Log.d("rtmpsdk","rtmp sdk version is:" + sdkver[0] + "." + sdkver[1] + "." + sdkver[2]);
        }
    }

    private void initView() {
        addMainView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycle_view);
        mRecyclerView.setAdapter(new MainRecycleAdapter(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PermissionUtils.checkPermission(this, PermissionUtils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }
}
