package com.example.jh.rxhapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xiaohui on 2018/4/20.
 * 这个类帮助我们创建一个数据库
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    /**
     *
     * @param context
     * @param name 数据库的名字
     * @param factory 通常为null
     * @param version 数据库的版本
     */
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DataBaseHelper(Context context) {
        super(context, "user", null, 1);
    }
    /**
     * 数据库第一次创建时调用这个方法，可以在此处初始化表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table text (_id INTEGER PRIMARY KEY AUTOINCREMENT,name text,age int);");
    }


    /**
     * 当数据库版本变化时，调用这个方法，可以在这里删除或添加新表
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
