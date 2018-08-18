package com.example.jh.rxhapp.mvp2.p;

import com.example.jh.rxhapp.mvp2.m.Callback;
import com.example.jh.rxhapp.mvp2.m.Model;
import com.example.jh.rxhapp.mvp2.m.ModleManager;
import com.example.jh.rxhapp.mvp2.m.MvpModle;
import com.example.jh.rxhapp.mvp2.m.Token;
import com.example.jh.rxhapp.mvp2.v.MvpView;

/**
 * Created by xiaohui on 2018/8/6.
 */

public class MvpPersenter extends BasePresenter<MvpView> {

    public void getData() {
        getView().showLoading();
        ModleManager.request(Token.sMvpModleClass)
                .params("更改之后")
                .excute(new Callback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (isViewAttch()) {
                            getView().dissMissLoading();
                            MvpView view = getView();
                            view.showData(data);
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });


    }
}
