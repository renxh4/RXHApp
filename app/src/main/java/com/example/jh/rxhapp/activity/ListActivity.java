package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.adapter.MainRecycleAdapter;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.custom_recycle);
        recyclerView.setAdapter(new MainRecycleAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}

