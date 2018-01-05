package com.item.net.change;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.item.net.change.service.IBindService;
import com.item.net.change.service.MyBindService;
import com.item.net.change.service.MyService;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {


    private IBindService mIBind = null; // 这个是我们定义的中间人对象
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // Log.d("jiejie", "------test---onConnect");
            mIBind = (IBindService) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("jiejie", "----test---" + componentName);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
    }

    private boolean isBound = false; // 服务是否绑定

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one: // 开始服务<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< onCreate -- onStartCommand
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.btn_two: // 结束服务<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< onDestroy
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_three: // 绑定服务<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< onCreate -- onBind
                Intent bindIntent = new Intent(this, MyBindService.class);
                isBound = bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_four: // 调用绑定服务中 其中的某个方法
                if (mIBind != null)
                    mIBind.callPause();
                break;
            case R.id.btn_five: // 解绑服务<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< onUnBind -- onDestroy
                if (isBound) {
                    unbindService(connection);
                    isBound = false;
                    mIBind = null;
                }
                break;
        }
    }
}
