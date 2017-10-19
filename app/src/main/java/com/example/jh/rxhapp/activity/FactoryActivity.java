package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.factory.Product;
import com.example.jh.rxhapp.factory.ProductA;
import com.example.jh.rxhapp.factory.ProductFactory;

public class FactoryActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    public int setMainView() {
        return R.layout.activity_factory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = (TextView) findViewById(R.id.factory_text);
        ProductFactory productFactory = new ProductFactory();
        Product product = productFactory.createProduct();
        //mTextView.setText(product.method());

        //第二种方法
        ProductA productA = new ProductA();
        ProductFactory productFactory1 = new ProductFactory();
        ProductA product1 = productFactory1.createProduct1(productA.getClass());
        mTextView.setText(product1.method());

    }
}
