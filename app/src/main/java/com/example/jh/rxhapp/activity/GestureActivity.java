package com.example.jh.rxhapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.weight.GestureLockViewGroup;

import java.util.List;

public class GestureActivity extends BaseActivity {

    private GestureLockViewGroup mGestureLockViewGroup;

    @Override
    public int setMainView() {
        return R.layout.activity_gesture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("手势解锁");
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        List<Integer> answers = mGestureLockViewGroup.getAnswers();
        if (answers.size() <= 0) {
            mGestureLockViewGroup.setCurrentModel(GestureLockViewGroup.SET_GESTURES);
        }
        mGestureLockViewGroup.setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {

            @Override
            public void onUnmatchedExceedBoundary() {
                Toast.makeText(GestureActivity.this, "错误5次...",
                        Toast.LENGTH_SHORT).show();
                mGestureLockViewGroup.setUnMatchExceedBoundary(5);
            }

            @Override
            public void isSetGestureSucess(boolean isSucess) {
                if (isSucess) {
                    mGestureLockViewGroup.setCurrentModel(GestureLockViewGroup.VERYFI_GESTURES);
                } else {
                }
                Toast.makeText(GestureActivity.this, isSucess + "/", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGestureEvent(boolean matched) {
//                        Toast.makeText(MainActivity.this, matched+"",
//                                Toast.LENGTH_SHORT).show();

                if (matched == true) {
                    startActivity(new Intent(GestureActivity.this, PDFActivity.class));
                }
            }

            @Override
            public void onBlockSelected(int cId) {
            }
        });
    }
}
