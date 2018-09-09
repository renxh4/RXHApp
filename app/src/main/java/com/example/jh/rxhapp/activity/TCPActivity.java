package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.tcp.TcpClient;
import com.example.jh.rxhapp.tcp.TcpServer;

public class TCPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);
        new TcpServer().start();

        findViewById(R.id.tcp_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TcpClient().start();
            }
        });
    }
}
