package com.example.jh.rxhapp.tcp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xiaohui on 2018/9/8.
 */

public class TcpChatClient extends Thread {

    private final Handler mHandler;
    private OutputStream mOutputStream;
    private Socket mSocket;

    public TcpChatClient(Handler handler) {
        this.mHandler = handler;
    }

    /*
         * TCP发送端：
         *
         * 1.实例化一个Socket对象，要指定IP和端口；
         * 2.如果要发送数据，通过Socket对象获取输出流：OutputStream
         * 3.输出数据；
         * 4.不断读取数据
         */
    @Override
    public void run() {

        try {
            //实例化一个Socket
            mSocket = new Socket("127.0.0.1", 6666);
            //获取输出流
            mOutputStream = mSocket.getOutputStream();
            //发送消息
            sendMessage("客户端请求链接");
            //获取输入流
            InputStream inputStream = mSocket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            //不断的读取数据
            while (true) {
                len = inputStream.read(bytes);
                String msg = new String(bytes, 0, len);
                Message msg1 = new Message();
                msg1.obj = "客户端收到：" + msg;
                mHandler.sendMessage(msg1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(String message) {
        try {
            mOutputStream.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void colseSocket() {
        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
