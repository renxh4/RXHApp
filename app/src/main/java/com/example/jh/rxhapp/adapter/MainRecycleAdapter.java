package com.example.jh.rxhapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.activity.CameraLiveWallpaperActivity;
import com.example.jh.rxhapp.activity.CompressBmpActivity;
import com.example.jh.rxhapp.activity.HtmlToTextActivity;
import com.example.jh.rxhapp.activity.IPActivity;
import com.example.jh.rxhapp.activity.LiveActivity;
import com.example.jh.rxhapp.activity.MagicWallPaperActivity;
import com.example.jh.rxhapp.activity.MaterialDesignActivity;
import com.example.jh.rxhapp.activity.PlayLiveActivity;
import com.example.jh.rxhapp.activity.ViewPagerActivity;

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
        mList.add("自定义控件");
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
                        mContext.startActivity(new Intent(mContext, PlayLiveActivity.class));
                    }
                    break;
            }
        }
    }
}
