package com.example.jh.rxhapp.build;

/**
 * Created by xiaohui on 2017/10/13.
 */

public abstract class ComPuter {

    private String mBoard;
    private String mDisplay;
    public String mOS;

    protected ComPuter() {

    }

    //主机
    public void setBoard(String board) {
        this.mBoard = board;
    }

    //显示器
    public void setDisplay(String display) {
        this.mDisplay = display;
    }

    //操作系统
    public abstract void setOS();

    @Override
    public String toString() {
        return "mboard=" + mBoard + "display=" + mDisplay + "OS=" + mOS;
    }
}
