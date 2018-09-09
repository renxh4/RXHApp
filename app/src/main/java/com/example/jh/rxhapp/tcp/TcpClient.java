package com.example.jh.rxhapp.tcp;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xiaohui on 2018/9/5.
 */

public class TcpClient extends Thread{
    /*
     * TCP发送端：
     *
     * 1.实例化一个Socket对象，要指定IP和端口；
     * 2.如果要发送数据，通过Socket对象获取输出流：OutputStream
     * 3.输出数据；
     * 4.释放资源：
     */
    @Override
    public void run() {
        try {
            //实例化一个Scoket对象，指定ip和端口
            Socket socket = new Socket("127.0.0.1", 8888);
            //获取发送数据的输出流
            OutputStream outputStream = socket.getOutputStream();
            //输出数据
            outputStream.write("我是TCP发送数据".getBytes());

            //释放资源
            socket.close();
            Log.d("mmm","客户端发送完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
