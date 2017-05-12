package com.example.jh.rxhapp;

import android.os.Environment;

/**
 * Created by jh on 2017/5/12.
 */

public interface Constants {

    String SD_CARD_DIR = Environment.getExternalStorageDirectory() + "/";
    String FILE_DIR = SD_CARD_DIR + "ren/xh";
    String IMAGE = FILE_DIR + "/image/";
}
