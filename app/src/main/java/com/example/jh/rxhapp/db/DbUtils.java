package com.example.jh.rxhapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.finalteam.toolsfinal.Logger;


/**
 * Created by xiaohui on 2018/4/20.
 */

public class DbUtils {

    private static DbUtils mDbUtils;
    private DataBaseHelper mDataBaseHelper;
    private SQLiteDatabase mWritableDatabase;

    private DbUtils(Context context) {
        if (mDataBaseHelper == null) {
            mDataBaseHelper = new DataBaseHelper(context);
            mWritableDatabase = mDataBaseHelper.getWritableDatabase();
        }
    }

    public static DbUtils getInstants(Context context) {
        if (mDbUtils == null) {
            mDbUtils = new DbUtils(context);
        }
        return mDbUtils;
    }

    /**
     * 添加数据方式1
     * 利用sql语句添加
     *
     * @param name
     * @param age
     */
    public void insertUseSql(String name, int age) {
        String sql = "insert into text (name,age) values (" + name + "," + age + ");";
        mWritableDatabase.execSQL(sql);
    }

    /**
     * 添加数据方式2
     *
     * @param name
     * @param age
     */
    public void insert(String name, int age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        mWritableDatabase.insert("text", null, contentValues);
    }



    public void deleteUseSql(String name) {
        String sql = "delete from text where name=" + name + ";";
        mWritableDatabase.execSQL(sql);
    }

    public void delete(String name) {
        String whereClause = "name=?";
        String[] whereArgs = {name};
        mWritableDatabase.delete("text", whereClause, whereArgs);
    }

    public void qurey() {
        Cursor cursor = mWritableDatabase.query("text", null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            //移动到首位
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                Logger.d("mmmdb", "name=" + name + "/age=" + age);
                //移动到下一位
                cursor.moveToNext();
            }
        }

        cursor.close();
        mWritableDatabase.close();

    }
}
