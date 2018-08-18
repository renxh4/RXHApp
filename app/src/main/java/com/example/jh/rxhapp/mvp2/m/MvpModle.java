package com.example.jh.rxhapp.mvp2.m;

import android.os.Handler;

/**
 * Created by xiaohui on 2018/8/7.
 */

public class MvpModle extends BaseModel<String> {
    @Override
    public void excute(final Callback<String> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(params[0]);
            }
        }, 2000);
    }
}

