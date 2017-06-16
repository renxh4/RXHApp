package com.example.jh.rxhapp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.jh.rxhapp.R;

public class PermissionUtils {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    public static void checkPermission(Context context, int code) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    code);
        }
    }

}
