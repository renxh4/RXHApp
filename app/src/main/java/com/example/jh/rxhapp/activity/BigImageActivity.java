package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.weight.BigImageView;

public class BigImageActivity extends AppCompatActivity {

    private BigImageView mBigImageView;
    public String path = "/storage/emulated/0/DCIM/GalleryFinal/IMG20180628163824.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mBigImageView = (BigImageView) findViewById(R.id.big_image_1);
        mBigImageView.setFilePath(path);
    }
}
