package com.example.jh.rxhapp.mvp1;


import android.os.Handler;

/**
 * Created by xiaohui on 2018/8/6.
 */

public class MvpModel {
    /**
     * 模拟数据请求
     * @param callBack
     */
    public static void getData(final MvpCallBack callBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess("请求成功");
            }
        }, 2000);
    }
}
