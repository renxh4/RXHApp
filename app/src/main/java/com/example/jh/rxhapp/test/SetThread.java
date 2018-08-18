package com.example.jh.rxhapp.test;

/**
 * Created by xiaohui on 2018/8/2.
 */

public class SetThread extends Thread {
    private final BaoZiPu mBaoZiPu;

    public SetThread(BaoZiPu baoZiPu) {
        this.mBaoZiPu = baoZiPu;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //每3秒制造一个包子
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mBaoZiPu.setBaoZi("包子");
        }
    }
}
