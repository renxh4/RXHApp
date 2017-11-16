package com.example.jh.rxhapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextServicesManager;
import android.widget.Button;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.activity.AbFactoryActivity;
import com.example.jh.rxhapp.activity.BuildActivity;
import com.example.jh.rxhapp.activity.CameraLiveWallpaperActivity;
import com.example.jh.rxhapp.activity.CompressBmpActivity;
import com.example.jh.rxhapp.activity.CustomViewActivity;
import com.example.jh.rxhapp.activity.DialogActivity;
import com.example.jh.rxhapp.activity.ExecutorActivity;
import com.example.jh.rxhapp.activity.FactoryActivity;
import com.example.jh.rxhapp.activity.GestureActivity;
import com.example.jh.rxhapp.activity.HtmlToTextActivity;
import com.example.jh.rxhapp.activity.IPActivity;
import com.example.jh.rxhapp.activity.InstanstActivity;
import com.example.jh.rxhapp.activity.JNIActivity;
import com.example.jh.rxhapp.activity.ListActivity;
import com.example.jh.rxhapp.activity.LiveActivity;
import com.example.jh.rxhapp.activity.MagicWallPaperActivity;
import com.example.jh.rxhapp.activity.MaterialDesignActivity;
import com.example.jh.rxhapp.activity.ObserverActivity;
import com.example.jh.rxhapp.activity.PDFActivity;
import com.example.jh.rxhapp.activity.PlayLiveActivity;
import com.example.jh.rxhapp.activity.RefreshActivity;
import com.example.jh.rxhapp.activity.SmsActivity;
import com.example.jh.rxhapp.activity.TestServiceActivity;
import com.example.jh.rxhapp.activity.ThreadLocalActivity;
import com.example.jh.rxhapp.activity.ViewActivity;
import com.example.jh.rxhapp.activity.ViewPager1Activity;
import com.example.jh.rxhapp.activity.ViewPagerActivity;
import com.example.jh.rxhapp.activity.WindowActivity;
import com.example.jh.rxhapp.observer.Observer;
import com.mob.tools.MobUIShell;

import java.util.ArrayList;

/**
 * Created by jh on 2017/5/11.
 */

public class MainRecycleAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private ArrayList<String> mList;

    public MainRecycleAdapter(Context context) {
        this.mContext = context;
        initList();
    }

    private void initList() {
        mList = new ArrayList<>();
        mList.add("MaterialDesign");
        mList.add("图片压缩");
        mList.add("TextViewForHtml");
        mList.add("透明桌面");
        mList.add("视频壁纸");
        mList.add("获取IP");
        mList.add("tablayout+viewpager");
        mList.add("直播");
        mList.add("handler使用");
        mList.add("自定义view");
        mList.add("view的移动");
        mList.add("滑动事件的分发");
        mList.add("window开发");
        mList.add("线程池开发");
        mList.add("DialogFragment对话框");
        mList.add("显示pdf，word文件");
        mList.add("ThreadLocal的使用");
        mList.add("JNI开发");
        mList.add("单例模式");
        mList.add("Build 模式");
        mList.add("工厂方法模式");
        mList.add("抽象工厂模式");
        mList.add("观察者模式");
        mList.add("无限轮播的viewpager");
        mList.add("手势解锁");
        mList.add("短信验证");
        mList.add("万能下拉刷新");

        mList.add("蓝牙开发");


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).
                inflate(R.layout.recycle_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.mButton.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Button mButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.item_button);
            mButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_button:
                    if (getAdapterPosition() == 0) {
                        mContext.startActivity(new Intent(mContext, MaterialDesignActivity.class));
                    } else if (getAdapterPosition() == 1) {
                        mContext.startActivity(new Intent(mContext, CompressBmpActivity.class));
                    } else if (getAdapterPosition() == 2) {
                        mContext.startActivity(new Intent(mContext, HtmlToTextActivity.class));
                    } else if (getAdapterPosition() == 3) {
                        mContext.startActivity(new Intent(mContext, CameraLiveWallpaperActivity.class));
                    } else if (getAdapterPosition() == 4) {
                        mContext.startActivity(new Intent(mContext, MagicWallPaperActivity.class));
                    } else if (getAdapterPosition() == 5) {
                        mContext.startActivity(new Intent(mContext, IPActivity.class));
                    } else if (getAdapterPosition() == 6) {
                        mContext.startActivity(new Intent(mContext, ViewPagerActivity.class));
                    } else if (getAdapterPosition() == 7) {
                        mContext.startActivity(new Intent(mContext, LiveActivity.class));
                    } else if (getAdapterPosition() == 8) {
                        mContext.startActivity(new Intent(mContext, TestServiceActivity.class));
                    } else if (getAdapterPosition() == 9) {
                        mContext.startActivity(new Intent(mContext, CustomViewActivity.class));
                    } else if (getAdapterPosition() == 10) {
                        mContext.startActivity(new Intent(mContext, ViewActivity.class));
                    } else if (getAdapterPosition() == 11) {
                        mContext.startActivity(new Intent(mContext, ListActivity.class));
                    } else if (getAdapterPosition() == 12) {
                        mContext.startActivity(new Intent(mContext, WindowActivity.class));
                    } else if (getAdapterPosition() == 13) {
                        mContext.startActivity(new Intent(mContext, ExecutorActivity.class));
                    } else if (getAdapterPosition() == 14) {
                        mContext.startActivity(new Intent(mContext, DialogActivity.class));
                    } else if (getAdapterPosition() == 15) {
                        mContext.startActivity(new Intent(mContext, PDFActivity.class));
                    } else if (getAdapterPosition() == 16) {
                        mContext.startActivity(new Intent(mContext, ThreadLocalActivity.class));
                    } else if (getAdapterPosition() == 17) {
                        mContext.startActivity(new Intent(mContext, JNIActivity.class));
                    } else if (getAdapterPosition() == 18) {
                        mContext.startActivity(new Intent(mContext, InstanstActivity.class));
                    }else if (getAdapterPosition() == 19) {
                        mContext.startActivity(new Intent(mContext, BuildActivity.class));
                    }else if (getAdapterPosition() == 20) {
                        mContext.startActivity(new Intent(mContext, FactoryActivity.class));
                    }else if (getAdapterPosition() == 21) {
                        mContext.startActivity(new Intent(mContext, AbFactoryActivity.class));
                    }else if (getAdapterPosition() == 22) {
                        mContext.startActivity(new Intent(mContext, ObserverActivity.class));
                    }else if (getAdapterPosition() == 23) {
                        mContext.startActivity(new Intent(mContext, ViewPager1Activity.class));
                    }else if (getAdapterPosition() == 24) {
                        mContext.startActivity(new Intent(mContext, GestureActivity.class));
                    }else if (getAdapterPosition() == 25) {
                        mContext.startActivity(new Intent(mContext, SmsActivity.class));
                    }else if (getAdapterPosition() == 26) {
                        mContext.startActivity(new Intent(mContext, RefreshActivity.class));
                    }
                    break;
                default:

                    break;

            }
        }
    }
}
