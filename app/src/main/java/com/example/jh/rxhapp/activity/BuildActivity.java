package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.build.ComPuter;
import com.example.jh.rxhapp.build.Director;
import com.example.jh.rxhapp.build.MacBookBuilder;

public class BuildActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_build;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("Build 模式");
        MacBookBuilder macBookBuilder = new MacBookBuilder();
        Director director = new Director(macBookBuilder);
        director.construct("因特尔主机", "三星显示屏");
        TextView textView = (TextView) findViewById(R.id.build_text);

        //显示开发一般省去Director，用Builder进行链式调用

        ComPuter comPuter = new MacBookBuilder().buildOS()
                .buildDisplay("三星显示屏")
                .buildBoard("没有主机")
                .create();
        textView.setText(comPuter.toString());
    }
}
