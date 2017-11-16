package com.example.jh.rxhapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jh.rxhapp.R;
import com.mob.MobSDK;
import com.mob.tools.MobUIShell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SmsActivity extends BaseActivity {

    private EventHandler eventHandler;

    @Override
    public int setMainView() {
        return R.layout.activity_sms;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("短信验证");
        initSdk();
        initData();
    }


    private void initSdk() {


        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(this, "2247b4b4844c0", "f10e6fb1b4f12baeb401a56d825f3ddd");

        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码
        // SMSSDK.setAskPermisionOnReadContact(boolShowInDialog);

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                        Log.d("mmm区号", data.getClass() + "/");
                        ArrayList country = (ArrayList) data;
                        HashMap o = (HashMap) country.get(0);
                        Log.d("mmm区号", country.get(0).getClass() + "/");
                        Log.d("mmm区号", o.toString() + "/");

                        Iterator iter = o.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry entry = (Map.Entry) iter.next();
                            Object key = entry.getKey();
                            Object val = entry.getValue();
                            Log.d("mmm区号", key + "/" + val);
                        }
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);

    }

    private void initData() {
        //获取支持列表
        SMSSDK.getSupportedCountries();
        SMSSDK.getContacts(true);
        Log.d("mmm", "initdata");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
