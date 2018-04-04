package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;

import java.net.InetAddress;
import java.net.Socket;

import static android.R.attr.port;

public class SocketActivity extends BaseActivity {

    private int port1=6066;
    private InetAddress socketNmane;

    @Override
    public int setMainView() {
        return R.layout.activity_socket;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("网络编程");
        client();
        server();
    }

    /**
     * 客户端
     */
    private void server() {
       // Socket socket = new Socket(socketNmane,port1);
    }

    /**
     * 服务端
     */
    private void client() {

    }
}
