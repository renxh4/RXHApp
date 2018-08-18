package com.example.jh.rxhapp.test;

import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by xiaohui on 2018/8/2.
 */

public class GetThread extends Thread {
    private final BaoZiPu mBaoziPu;

    public GetThread(BaoZiPu baoZiPu) {
        this.mBaoziPu = baoZiPu;
    }

    @Override
    public void run() {
        while (true){
            String baoZi = mBaoziPu.getBaoZi();
            Log.d("mmm", "我得到了一个" + baoZi);
        }
    }
}
