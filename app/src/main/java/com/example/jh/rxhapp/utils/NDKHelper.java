package com.example.jh.rxhapp.utils;

/**
 * Created by xiaohui on 2017/10/10.
 */

public class NDKHelper {
    static {
        System.loadLibrary("palmread-lib");
    }

    public static native String GetStringFromC(String str);
}
