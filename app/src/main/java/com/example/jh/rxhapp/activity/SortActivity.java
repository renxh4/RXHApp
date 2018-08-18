package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;

public class SortActivity extends BaseActivity {

    private volatile boolean isRunning = true;
    private Object mLowArr;

    @Override
    public int setMainView() {
        return R.layout.activity_sort;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("基本算法");
        //二分查找
        //int i = sort1(1);
        //Log.d("mmm二分查找", "你查找的索引=" + i);
        //冒泡排序
        int[] arr = {16, 17, 2, 43, 233, 47, 5, 12, 8, 54};
        //sort2(arr);
        //选择排序
        //sort3(arr);
        sort5(arr, 0, arr.length - 1);
        //quickSort(arr,0,arr.length-1);
        sort4(arr);

    }


    /**
     * 二分查找
     * ① 首先确定整个查找区间的中间位置 mid = （ left + right ）/ 2
     * <p>
     * ② 用待查关键字值与中间位置的关键字值进行比较；
     * <p>
     * 若相等，则查找成功
     * <p>
     * 若大于，则在后（右）半个区域继续进行折半查找
     * <p>
     * 若小于，则在前（左）半个区域继续进行折半查找
     * <p>
     * ③ 对确定的缩小区域再按折半公式，重复上述步骤。
     * 时间复杂度  O(logn)
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
     * 时间复杂度 O(n2) n的平方
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
     * 快速排序
     * 时间复杂度 O(nlogn)
     *
     * @param arr
     */
    private void sort5(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low >= high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];
        while (i < j) {
            //先看右边依次递减
            while (arr[j] >= temp && i < j) {
                j--;
            }

            //再看左边依次递增

            while (arr[i] <= temp && i < j) {
                i++;
            }

            //如果满足条件交换数字

            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准与i和j相等的位置的数字交换交换

        arr[low] = arr[i];
        arr[i] = temp;

        //现在左半边都是小于基准值的，递归调用左半边

        sort5(arr, low, i - 1);

        //现在右半边都是大于基准值的，递归调用右半边

        sort5(arr, i + 1, high);
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

    /**
     * 判断传进来的是否是回文
     *
     * @param str1
     * @param str2
     */
    public void isHuiWen(String str1, String str2) {

        StringBuffer sb = new StringBuffer(str2);
        sb.reverse();// 将str2中的字符串倒置

        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == sb.charAt(i)) {
                count++;
            }
        }
        if (count == str1.length()) {

            System.out.println("此字符串是一个回文字符串");
        } else {
            System.out.println("此字符串不是一个回文字符串");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
