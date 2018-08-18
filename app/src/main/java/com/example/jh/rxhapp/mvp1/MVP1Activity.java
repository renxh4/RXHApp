package com.example.jh.rxhapp.mvp1;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jh.rxhapp.R;

public class MVP1Activity extends AppCompatActivity implements MvpView {

    private TextView mTextView;
    private Button mButton;
    private ProgressDialog mProgressDialog;
    private MvpPresenter mMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp1);
        initView();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在加载数据");
        mProgressDialog.setCancelable(false);

        mMvpPresenter = new MvpPresenter(this);

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.text_view);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMvpPresenter.getDtata();
            }
        });
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dissMissLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showData(String msg) {
        mTextView.setText(msg);
    }
}
