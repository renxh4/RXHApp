package com.example.jh.rxhapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jh.rxhapp.R;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class LiveActivity extends AppCompatActivity {

    private TXLivePushConfig mLivePushConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        initSdk();
    }

    private void initSdk() {
        findViewById(R.id.live_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LiveActivity.this,PlayLiveActivity.class));
            }
        });
        //初始化推流对象
        TXLivePusher mLivePusher = new TXLivePusher(this);
        mLivePushConfig = new TXLivePushConfig();
        mLivePusher.setConfig(mLivePushConfig);
        //启动推流
        String rtmpUrl = "rtmp://2000.livepush.myqcloud.com/live/2000_4eb4da7079af11e69776e435c87f075e?bizid=2000";
        mLivePusher.startPusher(rtmpUrl);
        TXCloudVideoView mCaptureView = (TXCloudVideoView) findViewById(R.id.video_view);
        mLivePusher.startCameraPreview(mCaptureView);
        //设置清晰度
        //设置美颜
        mLivePusher.setBeautyFilter(7, 3);
        //设置滤镜
       /* Bitmap bmp = null;
        if (mLivePusher != null) {
            mLivePusher.setFilter(bmp);
        }*/

    }
}
