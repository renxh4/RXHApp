package com.example.jh.rxhapp.udp;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by xiaohui on 2018/9/4.
 */

public class UdpClient extends Thread {
    /*
     * UDP的发送端：
     *
     * 1.实例化一个DatagramSocket对象；
     * 2.准备数据：
     * 		1).目标IP--InetAddress对象
     * 		2).目标端口；
     * 		3).信息内容--byte[]数组
     * 3.实例化一个数据包对象：DatagramPacket对象
     * 4.调用DatagramSocket对象的方法，将数据包发送出去；
     */

    @Override
    public void run() {
        //实例化一个DatagramSocket对象
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            Log.d("mmm", "服务器连接失败");
        }
        //准备数据
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.d("mmm", "未找到服务器");
        }
        int port = 8888;
        byte[] bytes = "UDP发送的数据，请接收".getBytes();
        //实例化DatagramPacket对象
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, ip, port);
        //发送数据
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("mmm", "消息发送失败");
        }
        //释放资源
        datagramSocket.close();

        Log.d("mmm", "发送完毕");

    }
}
