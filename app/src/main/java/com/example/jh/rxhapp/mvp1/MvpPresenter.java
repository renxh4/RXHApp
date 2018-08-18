package com.example.jh.rxhapp.mvp1;

/**
 * Created by xiaohui on 2018/8/6.
 */

public class MvpPresenter {

    private final MvpView mView;

    public MvpPresenter(MvpView view) {
        this.mView = view;
    }

    public void getDtata() {
        mView.showLoading();
        MvpModel.getData(new MvpCallBack() {
            @Override
            public void onSuccess(String data) {
                mView.dissMissLoading();
                mView.showData(data);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
