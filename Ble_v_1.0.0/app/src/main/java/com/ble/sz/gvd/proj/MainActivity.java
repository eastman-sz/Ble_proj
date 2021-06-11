package com.ble.sz.gvd.proj;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ble.base.BaseAppCompactActivitiy;
import com.ble.base.CommonTitleView;
import com.ble.info.BleInfoActivity;
import com.ble.util.PermissionHelpler;
import com.sdk.ble.BleSearchListener;
import com.sdk.ble.BleState;
import com.sdk.ble.BleStateListener;
import com.sdk.ble.u.BleActionControl;
import com.sdk.ble.u.BleCallBackContrl;
import com.sdk.dev.DevInfo;
import java.util.ArrayList;

public class MainActivity extends BaseAppCompactActivitiy {

    private ArrayList<DevInfo> list = new ArrayList<>();
    private DevAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivitys();

        addBleListeners();

        startSearch();
    }

    @Override
    protected void initTitle() {
        CommonTitleView commonTitleView = (CommonTitleView)findViewById(R.id.common_title_view);
        commonTitleView.setLeftBtnBackgroud(null);
        commonTitleView.setLeftBtnText("退出");
        commonTitleView.setCenterTitleText("设备列表");
        commonTitleView.setRightBtnText("搜索");
        commonTitleView.setOnTitleClickListener(new CommonTitleView.OnTitleClickListener() {
            @Override
            public void onLeftBtnClick() {

            }
            @Override
            public void onRightBtnClick() {
                startSearch();
            }
        });
    }

    @Override
    protected boolean setTranslucentStatus() {
        return false;
    }

    @Override
    protected void initViews() {
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new DevAdapter(context , list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DevInfo devInfo = list.get(position);
                int state = devInfo.getState();
                String address = devInfo.getAddress();

                if (state == BleState.STATE_CONNECTED.getState()){
                    //点击断开
                    BleActionControl.getInstance().disconnect(address);

                }else if (state == BleState.STATE_DISCONNECTED.getState()){
                    //点击连接
                    BleActionControl.getInstance().startConnect(context , 1 , address);

                    startActivity(new Intent(context , BleInfoActivity.class)
                            .putExtra("mName" , devInfo.getName())
                            .putExtra("mAddress" , address));
                }

            }
        });
    }

    private void addBleListeners(){
        BleCallBackContrl.getInstance().addSearchListener(bleSearchListener);
        BleCallBackContrl.getInstance().addStateListener(bleStateListener);
    }

    private void startSearch(){
        list.clear();

        boolean hasPermission = PermissionHelpler.hasValiedLocationPermisson(this);
        if (hasPermission){
            BleActionControl.getInstance().startSearch(context);

            return;
        }
        PermissionHelpler.requetLocationPermission(this , 1000);
    }

    BleSearchListener bleSearchListener = new BleSearchListener(){
        @Override
        public void onSearchResult(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    list.add(converFrom(device , rssi));

                    adapter.notifyDataSetChanged();
                }
            });
        }
    };

    BleStateListener bleStateListener = new BleStateListener(){
        @Override
        public void onStateChange(final BleState bleState, final String address) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (DevInfo devInfo : list){
                        String addr = devInfo.getAddress();
                        if (addr.equals(address)){
                            devInfo.setState(bleState.getState());

                            adapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            });
        }
    };

    private DevInfo converFrom(BluetoothDevice device , int rssi){
        DevInfo devInfo = new DevInfo(device.getAddress() , device.getName() , rssi);
        devInfo.setState(BleState.STATE_DISCONNECTED.getState());
        return devInfo;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1000:
            {
                boolean hasPermission = PermissionHelpler.hasValiedLocationPermisson(this);
                if (hasPermission){
                    BleActionControl.getInstance().startSearch(context);
                }
            }
                break;
        }
    }
}
