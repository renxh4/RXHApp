package com.example.jh.rxhapp.fanshe;

import android.util.Log;

/**
 * Created by xiaohui on 2018/8/14.
 */

public class Student1 {

    public Student1(){
        Log.d("mmm","调用公有无参构造方法");
    }

    public Student1(char a){
        Log.d("mmm","调用公有有参构造方法"+a);
    }

    protected Student1(String name){
        Log.d("mmm","调用受保护有参构造方法"+name);
    }

    Student1(int age){
        Log.d("mmm","调用默认有参构造方法"+age);
    }

    private Student1(String sex,int age){
        Log.d("mmm","调用私有有参构造方法"+sex+age);
    }




}
