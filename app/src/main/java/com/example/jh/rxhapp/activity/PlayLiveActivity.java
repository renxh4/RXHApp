package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PlayLiveActivity extends AppCompatActivity {

    private static final String VIDEO_NAME = "text1.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_live);
        File videoFile = getFileStreamPath("text1.mp4");
        if (!videoFile.exists()) {
            videoFile = copyVideoFile();
        }
        initSdk(videoFile);
    }

    private File copyVideoFile() {
        File videoFile;
        try {
            FileOutputStream fos = openFileOutput(VIDEO_NAME, MODE_PRIVATE);
            InputStream in = getResources().openRawResource(R.raw.test1);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists())
            throw new RuntimeException("video file has problem, are you sure you have welcome_video.mp4 in res/raw folder?");
        return videoFile;
    }

    private void initSdk(File videoFile) {
        //mPlayerView即step1中添加的界面view
        TXCloudVideoView mPlayerView = (TXCloudVideoView) findViewById(R.id.video_view1);
//创建player对象
        TXLivePlayer mLivePlayer = new TXLivePlayer(this);
//关键player对象与界面view
        mLivePlayer.setPlayerView(mPlayerView);
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);

        String flvUrl = "https://www.douyu.com/2215757";
        mLivePlayer.startPlay(flvUrl, 1); //推荐FLV

    }
}
