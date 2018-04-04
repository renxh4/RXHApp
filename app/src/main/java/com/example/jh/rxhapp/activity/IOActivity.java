package com.example.jh.rxhapp.activity;

import android.os.Bundle;

import com.example.jh.rxhapp.Constants;
import com.example.jh.rxhapp.R;

import java.io.File;

public class IOActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_io;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("IO流");
        fileApis();
        iofenlei();
        fileDemo();
    }

    /**
     * io的分类
     */
    private void iofenlei() {
        /*
        * 1.字节流：
		1).输出流：OutputStream(抽象类):
				输出的方法：
				1).write(int n):输出一个字节
				2).write(byte[] b):输出一个字节数组；
				3).write(byte[] b, int off,int len):输出字节数组的一部分；

				|--FileOutputStream(类):
				|--FilterOutputStream(不学):
					|--BufferedOutputStream(缓冲流)
		2).输入流：InputStream(抽象类):
				读取的方法：
				1).int read():读取一个字节
				2).int read(byte[] b)：读取一个字节数组；

				|--FileInputStream(类):
				|--FilterInputStream(不学):
					|--BufferedInputStream(缓冲流);
	2.字符流：
		1).输出流：Writer:
				输出的方法：
				1).write(int n)：输出一个字符；
				2).write(char[] c):输出一个字符数组；
				3).write(char[] c ,int off,int len):输出字符数组的一部分；
				4).write(String s):输出一个字符串；
				5).write(String s,int off,int len):输出字符串的一部分；
				|--OutputStreamWriter(转换流)
					|--FileWriter(字符流)
				|--BufferedWriter(缓冲流):
					void newLine():输出一个换行符；
		2).输入流：Reader:
				读取的方法：
				1).int read():读取一个字符；
				2).int read(char[] c):读取一个字符数组；

				|--InputStreamReader(转换流);
					|--FileReader(字符流)
				|--BufferedReader(缓冲流):
					String readLine():读取一行数据；
        * */
    }

    private void fileDemo() {

    }

    /**
     * File类的常用api
     */
    private void fileApis() {
        // File file1 = new File("C:\\aaa\\a.txt");//绝对路径，文件存在

        //File file2 = new File("demo17.txt");//相对路径(项目根目录)，文件不存在

        // File file3 = new File("C:\\aaa","a.txt");//作用跟：file1相同

        //  File file4 = new File("C:\\aaa");
        //  File file5 = new File(file4,"a.txt");//跟file3和file1的效果相同
        //public boolean createNewFile():创建文件(如果成功创建返回true，否则(文件已经存在)返回false)
        //public boolean mkdir()：创建单级目录
        //public boolean mkdirs()：创建多级目录
        //public boolean delete():可以删除文件、可以删除目录(一定要是空目录)
        //判断功能
        // public boolean isDirectory():判断是否是一个目录；
        // public boolean isFile()：判断是否是一个文件；
        // public boolean exists()：判断文件/目录是否存在；
        // public boolean canRead()：判断是否可读；
        // public boolean canWrite()：判断是否可写；
        // public boolean isHidden()：判断是否隐藏；
        //File类的基本获取功能:
        // public String getAbsolutePath()：获取绝对路径
        //public String getPath()：获取File封装的路径
        //public String getName()：获取文件/目录的名称
        //public long length()：获取文件的大小(单位：字节)
        //public long lastModified()：最后修改时间(单位：毫秒)
        //File类的高级获取功能:
        //public String[] list():如果当前File表示的是一个目录，则返回此目录下所有的子文件/子目录的名称的String[]数组；
        //public File[] listFiles():如果当前File表示的一个目录，则返回此目录下所有的子文件/子目录的File[]数组；
        //区别：如果仅仅想获取文件名，使用第一种；
        //如果需要对子文件/子目录进行进一步的操作，可以使用第二种；
        //重命名功能
        //public boolean renameTo(File dest):将当前File对象，重命名到dest表示的路径下；
        //注意：
        //1.如果dest和原文件不在同目录下，重命名操作将会相当于"剪切"操作；
        //2.如果dest和原文件在同目录下，相当于"重命名"
    }
}
