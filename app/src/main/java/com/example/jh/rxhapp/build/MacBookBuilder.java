package com.example.jh.rxhapp.build;

/**
 * Created by xiaohui on 2017/10/13.
 */

public class MacBookBuilder extends Builder {
    private ComPuter mComPuter = new MacBook();

    @Override
    public Builder buildBoard(String board) {
        mComPuter.setBoard(board);
        return this;
    }

    @Override
    public Builder buildDisplay(String display) {
        mComPuter.setDisplay(display);
        return this;
    }

    @Override
    public Builder buildOS() {
        mComPuter.setOS();
        return this;
    }

    @Override
    public ComPuter create() {
        return mComPuter;
    }
}
