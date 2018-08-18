package com.example.jh.rxhapp.mvp2.p;

import com.example.jh.rxhapp.mvp2.v.BaseView;

/**
 * Created by xiaohui on 2018/8/6.
 */

public class BasePresenter<V extends BaseView> {

    private V mView;

    public void attchView(V view) {
        this.mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public V getView() {
        return mView;
    }

    public boolean isViewAttch() {
        return mView != null;
    }
}
