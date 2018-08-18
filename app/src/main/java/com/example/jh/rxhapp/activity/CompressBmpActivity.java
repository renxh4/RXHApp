package com.example.jh.rxhapp.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.utils.utils;
import com.example.jh.rxhapp.weight.BigImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        BigImageView bigImageView = (BigImageView) findViewById(R.id.big_image);
        try {
            InputStream inputStream = getAssets().open("bigimage.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compress_yasuo_button:
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
            initBitmap(photoPath);
            compressBitmap(photoPath);
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(CompressBmpActivity.this, "选择失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void initBitmap(String photoPath) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
        float bitmapcount = getBitmapsize(bitmap);
        mYuantuText.setText("原图：图片占内存大小=" + bitmapcount + "MB / 宽度=" + bitmap.getWidth() + "高度=" + bitmap.getHeight());
        Log.d("mmm", "原图：图片占内存大小=" + bitmapcount + "MB / 宽度=" + bitmap.getWidth() + "高度=" + bitmap.getHeight());
        mYuantuImage.setImageBitmap(bitmap);
    }

    private float getBitmapsize(Bitmap bitmap) {
        float i = bitmap.getByteCount() / 1024;
        return i / 1024;
    }

    private void compressBitmap(String photoPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //不获取图片，不加载到内存中，只返回图片属性
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);
        Log.d("mmm", "图片地址" + photoPath);
        //图片的宽高
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        Log.d("mmm", "图片宽=" + outWidth + "图片高=" + outHeight);
        //计算采样率
        int i = utils.computeSampleSize(options, -1, 1000 * 1000);
        //设置采样率，不能小于1 假如是2 则宽为之前的1/2，高为之前的1/2，一共缩小1/4 一次类推
        options.inSampleSize = i;
        Log.d("mmm", "采样率为=" + i);
        //图片格式压缩
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
        float bitmapsize = getBitmapsize(bitmap);
        mYasuoText.setText("压缩后：图片占内存大小" + bitmapsize + "MB / 宽度=" + bitmap.getWidth() + "高度=" + bitmap.getHeight());
        Log.d("mmm", "压缩后：图片占内存大小" + bitmapsize + "MB / 宽度=" + bitmap.getWidth() + "高度=" + bitmap.getHeight());
        mYasuoImage.setImageBitmap(bitmap);
    }

}
