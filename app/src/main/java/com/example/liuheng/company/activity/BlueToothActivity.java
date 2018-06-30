package com.example.liuheng.company.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.liuheng.company.R;

import java.util.ArrayList;
import java.util.List;

public class BlueToothActivity extends AppCompatActivity {
    MyAdapter mAdapter;

    List<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        TextView titleName = findViewById(R.id.tv_title_name);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        titleName.setText("蓝牙");

        initData();
        mAdapter = new MyAdapter(mData);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutmanager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
               startActivity(new Intent(BlueToothActivity.this,BlueToothDetailActivity.class));
            }
        });

    }

    private void initData(){
        mData = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            mData.add("SKDT"+i);
        }
    }

    public class MyAdapter extends BaseQuickAdapter<String> {

        public MyAdapter(List<String> mData) {
            super( R.layout.item_lanya,mData);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_name, item);

        }
    }
}
