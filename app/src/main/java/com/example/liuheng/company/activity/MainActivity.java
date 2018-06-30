package com.example.liuheng.company.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuheng.company.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CAPTURE = 1;

    @BindView(R.id.tv_company_description)
    TextView tvCompanyDescription;

//    @BindView(R.id.navigation) BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setOnMenuItemClickListener(this);
//        toolbar.setTitle("");

//        mTextMessage = (TextView) findViewById(R.id.message);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initView();

    }

    @OnClick({R.id.tv_ewm, R.id.ll_lanya, R.id.ll_wifi, R.id.ll_saoyisao})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_ewm:
                startActivity(new Intent(this, CompanyQRcodeActivity.class));
                break;
            case R.id.ll_lanya:
                startActivity(new Intent(MainActivity.this, BlueToothActivity.class));
                break;
            case R.id.ll_wifi:
                startActivity(new Intent(MainActivity.this, WifiActivity.class));
                break;
            case R.id.ll_saoyisao:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CAPTURE);
                break;

        }
    }

    private void initView() {
        SpannableStringBuilder span = new SpannableStringBuilder("缩进" + tvCompanyDescription.getText());
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvCompanyDescription.setText(span);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bluetooth:
                    startActivity(new Intent(MainActivity.this, BlueToothActivity.class));
                    return true;
                case R.id.navigation_wifi:
//                    mTextMessage.setText(R.string.title_wifi);
                    startActivity(new Intent(MainActivity.this, WifiActivity.class));
                    return true;
                case R.id.navigation_saoyisao:
//                    mTextMessage.setText(R.string.title_saoyisao);
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_CAPTURE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE:
                    //处理扫描结果（在界面上显示）
                    if (null != data) {
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            String result = bundle.getString(CodeUtils.RESULT_STRING);
                            Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
            }
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
//                break;
////            case R.id.action_add:
////                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
////                break;
////            case R.id.action_setting:
////                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
////                break;
//        }
//
//        return false;
//    }

    // 返回按钮
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
