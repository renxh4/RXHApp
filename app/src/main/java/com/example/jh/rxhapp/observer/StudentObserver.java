package com.example.jh.rxhapp.observer;

import android.util.Log;

/**
 * Created by xiaohui on 2017/10/17.
 */

//集体观察者
public class StudentObserver implements Observer {

    private final Subject mSubject;
    private String mName;
    private String mWork;

    public StudentObserver(String name, Subject subject) {
        this.mName = name;
        this.mSubject = subject;
        this.mSubject.registerObserver(this);

    }


    @Override
    public void updata(String work) {
        Log.d("mmmobserver", mName + "今天作业是" + work);
    }


}
