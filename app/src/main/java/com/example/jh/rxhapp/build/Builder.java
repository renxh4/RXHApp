package com.example.jh.rxhapp.build;

/**
 * Created by xiaohui on 2017/10/13.
 */

public abstract class Builder {

    //设置主机
    public abstract Builder buildBoard(String board);

    //设置显示器
    public abstract Builder buildDisplay(String display);

    //设置操作系统
    public abstract Builder buildOS();

    //创建Computer
    public abstract ComPuter create();

}
