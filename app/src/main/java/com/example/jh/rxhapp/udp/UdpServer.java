package com.example.jh.rxhapp.udp;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by xiaohui on 2018/9/4.
 */

public class UdpServer extends Thread {
    /*
     * 接收端：
     *
     * 1.实例化一个DatagramSocket(端口):
     * 2.准备一个空的byte[]数组，用于接收数据；
     * 	   准备一个空的数据包对象：DatagramPacket
     * 3.调用DatagramSocket的方法接收数据；
     * 4.从DatagramPacket中解析数据；
     * 5.释放资源
     */

    @Override
    public void run() {

        try {
            //实例化一个DatagramSocket，带端口
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            //创建一个空的byte数组
            byte[] bytes = new byte[1024];
            //准备一个空的DatagramPacket
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            Log.d("mmm", "服务端启动等待接收数据");
            //接收数据
            datagramSocket.receive(datagramPacket);
            Log.d("mmm", "服务端接收数据完毕");
            //解析数据
            String ip = datagramPacket.getAddress().getHostAddress();
            byte[] data = datagramPacket.getData();
            String s = new String(data, 0, data.length);
            Log.d("mmm", "接收到IP：" + ip + "接收到数据：" + s);

            //释放资源
            datagramSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("mmm", "接收异常");

        }
    }
}
