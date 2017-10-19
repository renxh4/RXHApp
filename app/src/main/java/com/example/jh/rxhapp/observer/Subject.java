package com.example.jh.rxhapp.observer;

/**
 * Created by xiaohui on 2017/10/17.
 */
//主题（被观察者）
public interface Subject {

    public void registerObserver(Observer observer);

    public void romoveObserver(Observer observer);

    public void nofityObserver();
}
