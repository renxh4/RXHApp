package com.example.jh.rxhapp.utils;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.net.URL;

/**
 * Created by xiaohui on 2017/9/22.
 */

public class AsyncTaskUtils extends AsyncTask<String,Integer,URL> {

    //子线程
    @Override
    protected URL doInBackground(String... params) {
        return null;
    }
    //构造方法
    public AsyncTaskUtils() {
        super();
    }

    //运行之前
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //运行完成之后
    @Override
    protected void onPostExecute(URL url) {
        super.onPostExecute(url);
    }
    //进度
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    //取消的回调
    @Override
    protected void onCancelled(URL url) {
        super.onCancelled(url);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
