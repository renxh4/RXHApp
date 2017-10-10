package com.example.jh.rxhapp.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.jh.rxhapp.R;

/**
 * Created by xiaohui on 2017/10/10.
 */

public class ToastUtils {

    public static void showToast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}

