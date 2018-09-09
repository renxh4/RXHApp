package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.udp.UdpClient;
import com.example.jh.rxhapp.udp.UdpServer;

public class UDPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udp);
        new UdpServer().start();

        findViewById(R.id.udp_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UdpClient().start();
            }
        });
    }
}
