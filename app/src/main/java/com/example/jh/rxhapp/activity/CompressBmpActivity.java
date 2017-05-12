package com.example.jh.rxhapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.bean.CompressBean;
import com.example.jh.rxhapp.utils.CompressBmpToFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.BitmapUtils;

public class CompressBmpActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_GALLERY = 101;
    private Button mButton;
    private ImageView mYasuoImage;
    private ImageView mYuantuImage;
    private TextView mYuantuText;
    private TextView mYasuoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        addMainView(R.layout.activity_compress_bmp);
        mButton = (Button) findViewById(R.id.compress_yasuo_button);
        mYasuoImage = (ImageView) findViewById(R.id.compress_yasuo_image);
        mYuantuImage = (ImageView) findViewById(R.id.compress_yuantu_image);
        mYuantuText = (TextView) findViewById(R.id.compress_yuantu_text);
        mYasuoText = (TextView) findViewById(R.id.compress_yasuo_text);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compress_yasuo_button:
                //开启相册
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new Myhander());
                break;
        }
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
                if (compressBean.path == CompressBmpToFile.NOT_COMPRESS) {
                    Toast.makeText(CompressBmpActivity.this, "没有压缩", Toast.LENGTH_SHORT).show();
                } else {
                    float fileSize = getFileSize(compressBean.file);
                    Bitmap bitmap = BitmapFactory.decodeFile(compressBean.path);
                    float bitmapsize = getBitmapsize(bitmap);
                    mYasuoText.setText("原图：bitmapsize=" + bitmapsize + "MB / filesize=" + fileSize);
                    mYasuoImage.setImageBitmap(bitmap);
                }
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
}
