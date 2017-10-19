package com.example.jh.rxhapp.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.observer.CaStudent;
import com.example.jh.rxhapp.observer.CaTeacher;
import com.example.jh.rxhapp.observer.StudentObserver;
import com.example.jh.rxhapp.observer.TeacherSubject;
import com.example.jh.rxhapp.observer.Observer;

public class ObserverActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_observer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("观察者模式");
        TeacherSubject teacherSubject = new TeacherSubject();
        StudentObserver 张三 = new StudentObserver("张三", teacherSubject);
        StudentObserver 李四 = new StudentObserver("李四", teacherSubject);
        StudentObserver 王五 = new StudentObserver("王五", teacherSubject);

        teacherSubject.setHomework("第五页第一题");
        teacherSubject.setHomework("第五页第二题");
        teacherSubject.setHomework("第五页第三题");

        CaTeacher caTeacher = new CaTeacher();
        caTeacher.register(new CaStudent() {
            @Override
            public void onCallBack(String work) {
                Log.d("mmmcallback", "今天作业是" + work);
            }
        });
        caTeacher.setWork("打游戏");
    }
}
