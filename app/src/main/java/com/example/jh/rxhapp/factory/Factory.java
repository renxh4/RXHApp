package com.example.jh.rxhapp.factory;


/**
 * Created by xiaohui on 2017/10/16.
 */

public  abstract class Factory {

    /**
     * 抽象工厂方法
     * 具体生产什么由子类去实现
     * @return 具体的产品
     */
    public abstract Product createProduct();

    public abstract <T extends Product> T createProduct1(Class<T> cls);
}
