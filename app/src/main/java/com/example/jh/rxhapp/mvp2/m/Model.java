package com.example.jh.rxhapp.mvp2.m;

        import android.os.Handler;


/**
 * Created by xiaohui on 2018/8/6.
 */

public class Model {

    public static void getData(final Callback<String> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess("请求成功");
            }
        }, 2000);
    }
}
