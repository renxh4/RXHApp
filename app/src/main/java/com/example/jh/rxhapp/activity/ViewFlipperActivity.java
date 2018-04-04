package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.jh.rxhapp.R;

import java.util.ArrayList;

public class ViewFlipperActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_view_flipper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("仿头条滚动条");
        initView();
    }

    private void initView() {
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.filp_view);
        //View inflate = View.inflate(this, R.layout.viewflip_layout, null);
        View inflate = LayoutInflater.from(this).inflate(R.layout.viewflip_layout, null);
        TextView textView = (TextView) inflate.findViewById(R.id.filp_text);
        textView.setText("哈哈哈哈哈");
        viewFlipper.addView(inflate);
        viewFlipper.addView(View.inflate(this,R.layout.viewflip_layout1,null));

    }
}
