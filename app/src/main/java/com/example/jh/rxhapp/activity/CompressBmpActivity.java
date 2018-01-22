package com.example.jh.rxhapp.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jh.rxhapp.Constants;
import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.bean.CompressBean;
import com.example.jh.rxhapp.utils.CompressBmpToFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class CompressBmpActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_GALLERY = 101;
    private Button mButton;
    private ImageView mYasuoImage;
    private ImageView mYuantuImage;
    private TextView mYuantuText;
    private TextView mYasuoText;
    private int REQUEST_CODE_CAMERA = 100;
    private File mTmpFile;
    private int REQUEST_CAMERA = 1000;

    @Override
    public int setMainView() {
        return R.layout.activity_compress_bmp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }


    }

    private void initView() {
        mButton = (Button) findViewById(R.id.compress_yasuo_button);
        mYasuoImage = (ImageView) findViewById(R.id.compress_yasuo_image);
        mYuantuImage = (ImageView) findViewById(R.id.compress_yuantu_image);
        mYuantuText = (TextView) findViewById(R.id.compress_yuantu_text);
        mYasuoText = (TextView) findViewById(R.id.compress_yasuo_text);
        mButton.setOnClickListener(this);
        int measuredHeight = mButton.getMeasuredHeight();
        int width = mButton.getWidth();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compress_yasuo_button:
                //开启相册
                // GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new Myhander());
                showPickPhotoDialog();
                break;
            default:
        }
    }

    private void showPickPhotoDialog() {
        final Dialog dialog = new Dialog(this);
        ListView listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"Take a selfie", "Browse pics"}));
        listView.setDividerHeight(2);
        dialog.setContentView(listView);
        dialog.setCanceledOnTouchOutside(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Logger.d(TAG, "position = " + position);
                switch (position) {
                    case 0:
                        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, new Myhander());
                        //showCameraAction();
                        break;
                    case 1:
                        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new Myhander());
                        break;
                    default:
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    class Myhander implements GalleryFinal.OnHanlderResultCallback {

        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            String photoPath = resultList.get(0).getPhotoPath();
            File file = new File(photoPath);
            initBitmap(photoPath, file);
            compressBitmap(resultList);
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(CompressBmpActivity.this, "选择失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void compressBitmap(List<PhotoInfo> photoPath) {
        //Bitmap bitmap1 = BitmapUtils.compressBimap(photoPath, maxlong);
        mYasuoText.setText("正在压缩");
        CompressBmpToFile.getFile(new CompressBmpToFile.CompressBitmap() {
            @Override
            public void toFile(HashMap<String, CompressBean> map) {
                CompressBean compressBean = map.get(CompressBmpToFile.KEY);
                float fileSize = getFileSize(compressBean.file);
                Bitmap bitmap = BitmapFactory.decodeFile(compressBean.path);
                float bitmapsize = getBitmapsize(bitmap);
                mYasuoText.setText("压缩后：bitmapsize=" + bitmapsize + "MB / filesize=" + fileSize);
                mYasuoImage.setImageBitmap(bitmap);
            }
        }, photoPath, "renyasuo", this);

    }

    private void initBitmap(String photoPath, File file) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
        float bitmapcount = getBitmapsize(bitmap);
        float filecount = getFileSize(file);
        mYuantuText.setText("原图：bitmapsize=" + bitmapcount + "MB / filesize=" + filecount);
        mYuantuImage.setImageBitmap(bitmap);
    }

    private float getFileSize(File file) {
        float i1 = file.length() / 1024;
        return i1 / 1024;
    }

    private float getBitmapsize(Bitmap bitmap) {
        float i = bitmap.getByteCount() / 1024;
        return i / 1024;
    }

    private void showCameraAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = new File(Constants.IMAGE);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            long l = System.currentTimeMillis();
            String time = String.valueOf(l);
            mTmpFile = new File(Constants.IMAGE + time);

            if (mTmpFile != null) {
                /*获取当前系统的android版本号*/
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                Log.e("currentapiVersion", "currentapiVersion====>" + currentapiVersion);
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, mTmpFile.getAbsolutePath());
                Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, REQUEST_CAMERA);
            } else {
                Toast.makeText(this, "图片不存在", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "相机不存在", Toast.LENGTH_SHORT).show();
        }

    }
}
