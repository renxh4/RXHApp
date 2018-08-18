package com.example.jh.rxhapp.factory;

import java.lang.reflect.Constructor;

/**
 * Created by xiaohui on 2017/10/16.
 */

public class ProductFactory extends Factory {
    @Override
    public Product createProduct() {
        return new ProductB();
        //return new ProductA();
    }

    @Override
    public <T extends Product> T createProduct1(Class<T> cls) {
        Product p= null;
        try {
            p = (Product) Class.forName(cls.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) p;
    }
}
