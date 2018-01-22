package com.example.jh.rxhapp.activity;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.fragment.ShowDialogFragment;

import java.util.logging.Logger;

public class DialogActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        int[] arr = {1, 2, 3, 4, 5,8,20};
        sort(arr, 8, 0, arr.length - 1);
    }

    //二分查找
    private void sort(int[] arr, int num, int di, int gao) {
        int zhong = (di + gao) / 2;
        int zhongnum = arr[zhong];

        if (zhongnum == num) {
            Log.d("mmmsort", zhong + "/" + arr[zhong]);
        } else if (zhongnum < num) {
            sort(arr, num, zhong, gao);
        } else {
            sort(arr, num, di, zhong);
        }
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
        showDialogFragment.show(getSupportFragmentManager(), "haha");
    }
}
