package com.example.jh.rxhapp.activity;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.fragment.ShowDialogFragment;

public class DialogActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragmentDialog();
            }
        });
        setToobarTitle("DialogFragment");
    }

    private void showFragmentDialog() {
        ShowDialogFragment showDialogFragment = new ShowDialogFragment();
        showDialogFragment.show(getSupportFragmentManager(),"haha");
    }
}
