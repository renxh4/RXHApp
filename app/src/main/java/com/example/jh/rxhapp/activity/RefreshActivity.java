package com.example.jh.rxhapp.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.weight.refresh.QQRefreshHeader;
import com.example.jh.rxhapp.weight.refresh.RefreshLayout;

public class RefreshActivity extends BaseActivity {

    private ListView listView;

    @Override
    public int setMainView() {
        return R.layout.activity_refresh;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToobarTitle("万能下拉刷新");
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listview);

        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshlayout);
        if (refreshLayout != null) {
            // 刷新状态的回调
            refreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // 延迟3秒后刷新成功
                    refreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.refreshComplete();
                            if (listView != null) {
                                listView.setAdapter(new MainAdapter());
                            }
                            ;
                        }
                    }, 3000);
                }
            });
        }

        QQRefreshHeader header = new QQRefreshHeader(this);
        refreshLayout.setRefreshHeader(header);
        refreshLayout.autoRefresh();
    }

    class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                TextView textView = new TextView(RefreshActivity.this);
                textView.setText(String.valueOf(position));
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundColor(0x55ff0000);
                textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
                textView.setGravity(Gravity.CENTER);
                convertView = textView;
            } else {
                ((TextView) convertView).setText(String.valueOf(position));
            }

            return convertView;
        }
    }
}
