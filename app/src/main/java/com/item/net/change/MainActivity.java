package com.item.net.change;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.item.net.change.receiver.MyNetReceiver;

public class MainActivity extends AppCompatActivity implements MyNetReceiver.NetChangeEvent {

    private MyNetReceiver mNetReceiver;
    public static MyNetReceiver.NetChangeEvent event;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReceiver();
        event = this;
        textView = findViewById(R.id.tv_net);
    }

    private void initReceiver() {
        // 动态注册广播
        mNetReceiver = new MyNetReceiver();
        // 创建意图过滤器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 动态注册，有注册一定要有销毁
        if (mNetReceiver != null) {
            unregisterReceiver(mNetReceiver);
            mNetReceiver = null;
        }
    }


    @Override
    public void onNetChange(int state) {
        if (state == 0) {
            textView.setText("当前连接为数据流量");
        } else if (state == 1) {
            textView.setText("当前连接为wifi");
        } else {
            textView.setText("当前没有网络连接");
        }
    }
}
