package com.example.jh.rxhapp.observer;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by xiaohui on 2017/10/17.
 */

public class TeacherSubject implements Subject {

    private final ArrayList<Observer> mObservers;
    private String mWork;

    public TeacherSubject() {
        mObservers = new ArrayList<>();
    }


    @Override
    public void registerObserver(Observer observer) {

        mObservers.add(observer);

    }

    @Override
    public void romoveObserver(Observer observer) {

        if (mObservers.indexOf(observer)>=0){
            mObservers.remove(observer);
        }
    }

    @Override
    public void nofityObserver() {

        for (Observer o:mObservers) {
            o.updata(mWork);
        }
    }

    public void setHomework(String work){
        this.mWork=work;
        this.nofityObserver();
        Log.d("mmmsubject","今天作业是"+work);
    }
}
