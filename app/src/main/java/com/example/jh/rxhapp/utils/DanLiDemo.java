package com.example.jh.rxhapp.utils;

/**
 * Created by xiaohui on 2017/10/12.
 */

public class DanLiDemo {

    //饿汉单例模式
    private static DanLiDemo sDanLiDemo = new DanLiDemo();
    private static DanLiDemo sDanLiDemo1;
    private static DanLiDemo sDanLiDemo2 = null;

    private DanLiDemo() {
    }

    public static DanLiDemo getInstance() {
        return sDanLiDemo;
    }

    //懒汉单例模式

    public static synchronized DanLiDemo getInstances() {
        if (sDanLiDemo1 == null) {
            sDanLiDemo1 = new DanLiDemo();
        }
        return sDanLiDemo1;
    }

    //DCL实现单例

    public static DanLiDemo gettInstance1() {
        if (sDanLiDemo2 == null) {
            synchronized (DanLiDemo.class) {
                if (sDanLiDemo2 == null) {
                    sDanLiDemo2 = new DanLiDemo();
                }
            }
        }
        return sDanLiDemo2;
    }

    //静态内部单例模式

    public static DanLiDemo getInstance2() {
        return DanLiDemoViewHolder.danli;
    }

    private static class DanLiDemoViewHolder {
        private static final DanLiDemo danli = new DanLiDemo();
    }

}
