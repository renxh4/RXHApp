package com.example.jh.rxhapp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import com.example.jh.rxhapp.Constants;
import com.mob.tools.utils.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaohui on 2017/8/30.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String FILE_NAME = "LOG";
    private static CrashHandler crashHandler = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
    private Context mApplicationContext;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    public void init(Context context) {
        //获取默认的异常处理器
        mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mApplicationContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        toSDCard(e);
        e.printStackTrace();
        //如果系统自己有异常处理器，交给系统，否则自己处理
        if (mDefaultUncaughtExceptionHandler != null) {
            mDefaultUncaughtExceptionHandler.uncaughtException(t, e);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    private void toSDCard(Throwable e) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //没有sd卡
            return;
        }

        File file = new File(Constants.FILE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        long l = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l));

        File file1 = new File(Constants.FILE_DIR + FILE_NAME + time + ".trace");

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file1)));
            printWriter.println(time);
            printPhoneInfo(printWriter);
            printWriter.println();
            e.printStackTrace(printWriter);
            printWriter.close();
        } catch (IOException ex) {
            e.printStackTrace();
        }
    }

    private void printPhoneInfo(PrintWriter printWriter) {
        PackageManager packageManager = mApplicationContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mApplicationContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            printWriter.print("Version Code :");
            printWriter.print(packageInfo.versionName);
            printWriter.print("-");
            printWriter.println(packageInfo.versionCode);

            //android 版本号

            printWriter.print("OS Version :");
            printWriter.print(Build.VERSION.RELEASE);
            printWriter.print("-");
            printWriter.println(Build.VERSION.SDK_INT);

            //手机制造商

            printWriter.print("制造商");
            printWriter.println(Build.MANUFACTURER);

            //手机型号
            printWriter.print("手机型号");
            printWriter.println(Build.MODEL);

            //CPU架构

            printWriter.print("CPU架构");
            printWriter.print(Build.CPU_ABI);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
