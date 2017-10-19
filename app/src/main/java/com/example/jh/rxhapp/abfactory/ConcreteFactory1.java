package com.example.jh.rxhapp.abfactory;

import com.example.jh.rxhapp.activity.AbFactoryActivity;

/**
 * Created by xiaohui on 2017/10/17.
 */
//具体工厂
public class ConcreteFactory1 extends AbstractFactory {
    @Override
    public ProductA createproductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createproductB() {
        return new ConcreteProductB1();
    }
}
