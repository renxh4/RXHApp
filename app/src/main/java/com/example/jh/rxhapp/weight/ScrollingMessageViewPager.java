package com.example.jh.rxhapp.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jh.rxhapp.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class ScrollingMessageViewPager extends ViewPager {

    private static final int SCROLLING_MESSAGE = 0;
    private static final int SCROLLING_INTERVAL = 4000;
    private Context mContext;

    public List<Integer> mIndicatorList;
    private MyPagerAdapter myPagerAdapter;
    private ScrollingMessageHandler handler = new ScrollingMessageHandler(this);

    private static class ScrollingMessageHandler extends Handler {
        private final WeakReference<ScrollingMessageViewPager> mViewPager;

        private ScrollingMessageHandler(ScrollingMessageViewPager activity) {
            mViewPager = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mViewPager.get() != null) {
                mViewPager.get().setCurrentItem(mViewPager.get().getCurrentItem() + 1);
                mViewPager.get().myPagerAdapter.notifyDataSetChanged();
            }
        }
    }

    public ScrollingMessageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollingMessageViewPager(Context context) {
        super(context);

    }

    public void startViewPager(Context context, List<Integer> indicatorList, final List<View> viewlist) {
        mContext = context;
        mIndicatorList = indicatorList;
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                handler.removeMessages(SCROLLING_MESSAGE);
                handler.sendEmptyMessageDelayed(SCROLLING_MESSAGE, SCROLLING_INTERVAL);
                if (viewlist.size() > 0) {
                    int currentIndex = position % viewlist.size();
                    for (int i = 0; i < viewlist.size(); i++) {
                        ImageView imageView = (ImageView) viewlist.get(i).findViewById(R.id.index);
                        if (i == currentIndex) {
                            imageView.setImageResource(R.drawable.red_two);
                        } else {
                            imageView.setImageResource(R.drawable.white_two);
                        }
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        myPagerAdapter = new MyPagerAdapter();
        setAdapter(myPagerAdapter);
        setCurrentItem(0);
        handler.sendEmptyMessageDelayed(SCROLLING_MESSAGE, SCROLLING_INTERVAL);
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            if (mIndicatorList.size() == 1) {
                return 1;
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(getContext(), R.layout.rollviewpager_item, null);
            Log.d("mmm",position+"/");
            ImageView imageview = (ImageView) view.findViewById(R.id.imageview);
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mIndicatorList.get(position % mIndicatorList.size()));
            imageview.setImageBitmap(bitmap);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
