package com.example.jh.rxhapp.mvp2.m;

/**
 * Created by xiaohui on 2018/8/7.
 */

public class ModleManager {

    public static <T extends BaseModel> T request(Class<T> clazz) {
        T t = null;

        try {
            t = (T) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }
}
