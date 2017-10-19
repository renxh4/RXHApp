package com.example.jh.rxhapp.activity;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.abfactory.ConcreteFactory1;
import com.example.jh.rxhapp.abfactory.ConcreteFactory2;
import com.example.jh.rxhapp.abfactory.ProductA;

public class AbFactoryActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_ab_factory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("抽象工厂模式");
        TextView textView = (TextView) findViewById(R.id.abfactory_text1);
        TextView textView1 = (TextView) findViewById(R.id.abfactory_text2);
        ConcreteFactory1 concreteFactory1 = new ConcreteFactory1();
        String method = concreteFactory1.createproductA().method();
        String method1 = concreteFactory1.createproductB().method();
        textView.setText(method + method1);

        ConcreteFactory2 concreteFactory2 = new ConcreteFactory2();
        String method2 = concreteFactory2.createproductA().method();
        String method3 = concreteFactory2.createproductB().method();
        textView1.setText(method2 + method3);



    }

}
