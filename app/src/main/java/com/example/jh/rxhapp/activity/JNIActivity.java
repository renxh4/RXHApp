package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.utils.NDKHelper;
import com.example.jh.rxhapp.utils.ToastUtils;

public class JNIActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_jni;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("JNI");
        ToastUtils.showToast(this, NDKHelper.GetStringFromC("欢迎来到PalmRead"));
        TextView textView = (TextView) findViewById(R.id.jni_textview);
        textView.setText(NDKHelper.GetStringFromC("欢迎来到PalmRead"));
    }
}
