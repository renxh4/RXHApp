package com.example.jh.rxhapp.fanshe;

/**
 * Created by xiaohui on 2018/8/14.
 */

public class Student2 {

    public String show(String s,int age){
        System.out.println("调用了public 的show方法"+s+age);
        return "abc";
    }

    protected void show1(){
        System.out.println("调用了protected 的show1方法");
    }

    void show2(){
        System.out.println("调用了默认 的show2方法");
    }

    private void show3(){
        System.out.println("调用了私有 的show3方法");
    }
}
