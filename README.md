# MyNetChange
NetWorkChange
网络变化监听

### 第一步：权限的添加
    <!--允许读取网络状态-->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

### 第二步：注册广播（建议使用动态广播）
    在清单文件注册广播
    在Activity动态注册
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
### 第三步：广播监听判断网络的连接状态
    public class MyNetReceiver extends BroadcastReceiver {

        private NetChangeEvent event ;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 这个监听包括WiFi和移动数据的打开和关闭
            // 最好用的还是这个监听 但是比较慢
            assert action != null;
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                int state = NetUtil.getNetWorkState(); // 判断网络是什么类型，0为流量1为wifi
                if(event == null){
                    event = MainActivity.event;
                }
                event.onNetChange(state);
            }
        }

        public interface NetChangeEvent {
            void onNetChange(int state);
        }
    }

### 第四步：定义接口并进行回调
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