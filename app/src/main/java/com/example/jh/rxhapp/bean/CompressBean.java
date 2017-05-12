package com.example.jh.rxhapp.bean;

import java.io.File;

/**
 * Created by jh on 2017/5/12.
 */

public class CompressBean {
    public File file;
    public String path;

    public CompressBean(File file, String path) {
        this.file = file;
        this.path = path;
    }
}
