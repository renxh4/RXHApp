package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;

public class SortActivity extends BaseActivity {

    private volatile boolean isRunning=true;

    @Override
    public int setMainView() {
        return R.layout.activity_sort;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("基本算法");
        //二分查找
        int i = sort1(1);
        Log.d("mmm二分查找", "你查找的索引=" + i);
        //冒泡排序
        int[] arr = {16, 17, 2, 43, 233, 47};
        //sort2(arr);
        //选择排序
        sort3(arr);
        textThread();
    }

    private void textThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    Log.d("mmmsortthread", "1");
                }
            }
        }).start();
    }

    /**
     * 二分查找
     *
     * @param i 查找的数字
     */
    private int sort1(int i) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //最低索引
        int low = 0;
        //最高索引
        int height = arr.length - 1;

        while (low <= height) {
            //中间索引
            int middle = (low + height) / 2;
            if (i == arr[middle]) {
                return middle;
            } else if (i < arr[middle]) {
                height = middle;
            } else {
                low = middle;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序
     * 相邻元素两两比较，大的往后放，第一次完毕后，最大值出现最后面
     *
     * @param arr
     */
    private void sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//外层控制循环次数
            for (int j = 0; j < arr.length - 1 - i; j++) {//里层控制比交逻辑
                if (arr[j] > arr[j + 1]) {
                    int o = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = o;
                }
            }
        }
        sort4(arr);
    }


    /**
     * 选择排序
     * 第一次用第一个数依次跟后面所有数比较，小的放前面，，第一次循环之后，小的在最前面
     *
     * @param arr
     */
    private void sort3(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//外层控制循环次数
            for (int j = 0; j < arr.length - 1 - i; j++) {//里层控制逻辑
                if (arr[i] > arr[j + i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[j + i + 1];
                    arr[j + i + 1] = temp;
                }
            }
        }

        sort4(arr);
    }


    /**
     * 打印数组
     *
     * @param arr
     */
    private void sort4(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Log.d("mmm打印数组", "索引=" + i + "值=" + arr[i]);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }
}
