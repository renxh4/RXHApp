package com.example.jh.rxhapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.utils.VideoLiveWallpaper;

public class MagicWallPaperActivity extends BaseActivity {

    private CheckBox mCbVoice;

    @Override
    public int setMainView() {
        return R.layout.activity_magic_wall_paper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

        mCbVoice = (CheckBox) findViewById(R.id.id_cb_voice);

        mCbVoice.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            // 静音
                            VideoLiveWallpaper.voiceSilence(getApplicationContext());
                        } else {
                            VideoLiveWallpaper.voiceNormal(getApplicationContext());
                        }
                    }
                });
    }

    public void setVideoToWallPaper(View view) {
        VideoLiveWallpaper.setToWallPaper(this);
    }
}
