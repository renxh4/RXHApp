package com.example.jh.rxhapp.tcp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaohui on 2018/9/8.
 */

public class TcpChatServer extends Thread {

    private final Handler mHanlder;
    private Socket mSocket;
    private OutputStream mOutputStream;

    public TcpChatServer(Handler handler) {
        this.mHanlder = handler;
    }

    /*
            * TCP接收数据：
            *
            * 1.实例化一个ServerSocket，并指定端口号；
            * 	 调用ServerSocket的accept()方法等待连接：
            * 2.由于要读取数据，所以通过ServerSocket对象获取"输入流"--InputStream
            * 3.读取信息；
            * 4.释放资源；
            */
    @Override
    public void run() {
        try {
            //实例化ServerSocket
            ServerSocket serverSocket = new ServerSocket(8888);
            //等待链接
            Log.d("mmm", "服务器等待链接");
            mSocket = serverSocket.accept();
            Log.d("mmm", "链接成功");
            //获取书输入流
            InputStream inputStream = mSocket.getInputStream();
            //获取输出流
            mOutputStream = mSocket.getOutputStream();
            //读取消息
            byte[] bytes = new byte[1024];
            while (true) {
                int len = inputStream.read(bytes);
                String s = new String(bytes, 0, len);
                Message msg = new Message();
                msg.obj = "服务端收到："+s;
                mHanlder.sendMessage(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg) {
        try {
            mOutputStream.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
