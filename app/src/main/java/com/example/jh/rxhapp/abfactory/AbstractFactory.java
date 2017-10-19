package com.example.jh.rxhapp.abfactory;

/**
 * Created by xiaohui on 2017/10/17.
 */

//抽象工厂
public abstract class AbstractFactory {

    /**
     * 创建A的方法
     * @return
     */
    public abstract ProductA createproductA();


    /**
     * 创建B的方法
     * @return
     */
    public abstract ProductB createproductB();
}
