package com.example.jh.rxhapp.tcp;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaohui on 2018/9/5.
 */

public class TcpServer extends Thread {
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
        //实例化一个实例化一个ServerSocket，并指定端口
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            //调用serverSocket的accept方法，等待连接
            Log.d("mmm", "等待连接");
            Socket socket = serverSocket.accept();
            Log.d("mmm", "有用户连接");
            //获取输入流读取数据
            InputStream inputStream = socket.getInputStream();
            //读取信息
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            String s = new String(bytes, 0, len);
            Log.d("mmm", s);
            //释放资源
            serverSocket.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
