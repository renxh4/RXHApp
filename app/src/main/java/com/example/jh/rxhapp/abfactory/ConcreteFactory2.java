package com.example.jh.rxhapp.abfactory;

/**
 * Created by xiaohui on 2017/10/17.
 */
//具体工厂
public class ConcreteFactory2 extends AbstractFactory {
    @Override
    public ProductA createproductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createproductB() {
        return new ConcreteProductB2();
    }
}
