package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.proxy.ProxyHandler;
import com.example.jh.rxhapp.proxy.ProxySubject;
import com.example.jh.rxhapp.proxy.RealSubjiect;
import com.example.jh.rxhapp.proxy.Subject;

import java.lang.reflect.Proxy;

public class ProxyActivity extends AppCompatActivity {

    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        //静态代理
        /*ProxySubject proxySubject = new ProxySubject();
        proxySubject.request();*/

        //动态代理

        //创建调用处理器对象
        ProxyHandler proxyHandler = new ProxyHandler();
        //动态生成代理对象
        Subject proxy = (Subject) Proxy.newProxyInstance(RealSubjiect.class.getClassLoader(), RealSubjiect.class.getInterfaces(), proxyHandler);
        //调用方法
        proxy.request();

    }

}
