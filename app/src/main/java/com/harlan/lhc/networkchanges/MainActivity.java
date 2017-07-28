package com.harlan.lhc.networkchanges;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tiny.connect_change.ConnectionChangeAnnotation;
import com.tiny.connect_change.ConnectionChangeUtils;
import com.tiny.connect_change.NetStatus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View mNetErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetErrorView = findViewById(R.id.net_status_bar_top);
        ConnectionChangeUtils.register(this);
        mNetErrorView.setOnClickListener(this);
    }

    @ConnectionChangeAnnotation(tag = NetStatus.NO_NET)
    public void onNO_NETChange() {
        mNetErrorView.setVisibility(View.VISIBLE);
    }

    @ConnectionChangeAnnotation(tag = NetStatus.HAVE_NET)
    public void onNetChange() {
        mNetErrorView.setVisibility(View.GONE);
    }
    @ConnectionChangeAnnotation(tag = NetStatus.NET_2G)
    public void on2GChange() {
        mNetErrorView.setVisibility(View.GONE);
    }
    @ConnectionChangeAnnotation(tag = NetStatus.NET_3G)
    public void on3GChange() {
        mNetErrorView.setVisibility(View.GONE);
    }
    @ConnectionChangeAnnotation(tag = NetStatus.NET_4G)
    public void on4GChange() {
        mNetErrorView.setVisibility(View.GONE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnectionChangeUtils.unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.net_status_bar_top:
                // 跳转到网络设置
                startActivity(new Intent(
                        android.provider.Settings.ACTION_WIFI_SETTINGS));
                break;

            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetUtil.isNetConnected(this))
            mNetErrorView.setVisibility(View.VISIBLE);
        else
            mNetErrorView.setVisibility(View.GONE);
    }
}
