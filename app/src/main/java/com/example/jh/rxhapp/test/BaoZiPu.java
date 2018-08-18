package com.example.jh.rxhapp.test;

import java.util.ArrayList;

/**
 * Created by xiaohui on 2018/8/2.
 */

public class BaoZiPu {
    ArrayList<String> list = new ArrayList<>();


    public synchronized void setBaoZi(String baoZi) {
        list.add(baoZi);
        //唤醒所有线程
        notifyAll();
    }

    public synchronized String getBaoZi() {
        if (list.size() <= 0) {//如果没有包子
            try {
                //让当前线程等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //取一个包子
        String s = list.get(0);
        //移除一个包子
        list.remove(0);
        return s;
    }
}
