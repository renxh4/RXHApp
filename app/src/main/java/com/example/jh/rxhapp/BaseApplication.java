package com.example.jh.rxhapp;

import android.app.Application;

import com.example.jh.rxhapp.utils.GlideImageLoader;
import com.mob.MobApplication;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by jh on 2017/5/12.
 */

public class BaseApplication extends Application {


    private static BaseApplication sInstance;

    public static BaseApplication getInstance() {

        if (sInstance == null) {
            sInstance = new BaseApplication();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //这里可以全局初始化配置
        initGalleryFinal();
    }

    private void initGalleryFinal() {
        //配置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        //设置核心配置信息
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)

                .build();
        GalleryFinal.init(coreConfig);
    }


}
