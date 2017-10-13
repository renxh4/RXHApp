package com.example.jh.rxhapp.build;

/**
 * Created by xiaohui on 2017/10/13.
 */

public class Director {

    private Builder mBuilder = null;

    public Director(Builder builder) {
        mBuilder = builder;
    }

    //构建对象

    public void construct(String board, String display) {
        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.buildOS();
    }
}
