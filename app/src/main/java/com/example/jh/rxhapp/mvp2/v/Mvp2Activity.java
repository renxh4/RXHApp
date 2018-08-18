package com.example.jh.rxhapp.mvp2.v;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.mvp2.p.MvpPersenter;

public class Mvp2Activity extends Base1Activity implements MvpView {

    private Button mButton;
    private TextView mTextView;
    private MvpPersenter mMvpPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp2);
        initView();
        mMvpPersenter = new MvpPersenter();
        mMvpPersenter.attchView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMvpPersenter.detachView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button2);
        mTextView = (TextView) findViewById(R.id.text_view2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPersenter.getData();
            }
        });
    }

    @Override
    public void showData(String msg) {
        mTextView.setText(msg);
    }
}
