package com.ble.info;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ble.base.BaseAppCompactActivitiy;
import com.ble.base.CommonTitleView;
import com.ble.empty.BaseEmptyLoadingView;
import com.ble.sz.gvd.proj.R;
import com.sdk.ble.BleState;
import com.sdk.ble.BleStateListener;
import com.sdk.ble.add.BleServiceListener;
import com.sdk.ble.u.BleCallBackContrl;

import java.util.ArrayList;
import java.util.List;

public class BleInfoActivity extends BaseAppCompactActivitiy {

    private CommonTitleView commonTitleView = null;

    private ListView listView = null;
    private ArrayList<BleInfo> list = new ArrayList<>();
    private BleInfoAdapter adapter = null;

    private String mAddress = null;
    private String mName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_info);

        initActivitys();

        addBleListeners();
    }

    @Override
    protected void getIntentData() {
        mAddress = getIntent().getStringExtra("mAddress");
        mName = getIntent().getStringExtra("mName");
    }

    @Override
    protected boolean setTranslucentStatus() {
        return false;
    }

    @Override
    protected void initTitle() {
        commonTitleView = (CommonTitleView)findViewById(R.id.common_title_view);
        commonTitleView.setLeftBtnBackgroud(null);
        commonTitleView.setLeftBtnText("返回");
        commonTitleView.setCenterTitleText(TextUtils.isEmpty(mName) ?  "设备信息" : mName);
        commonTitleView.setOnTitleClickListener(new CommonTitleView.OnTitleClickListener() {
            @Override
            public void onLeftBtnClick() {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initViews() {
        listView = (ListView)findViewById(R.id.listView);
        adapter = new BleInfoAdapter(context , list);
        listView.setAdapter(adapter);

        addEmptyView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BleInfo bleInfo = list.get(position);

                if (bleInfo.getCharacteristicList().isEmpty()){
                    //// TODO: 2017/6/7
                    return;
                }

                String serviceUuid = bleInfo.getService().getUuid().toString();

                ArrayList<String> characteristicUuids = new ArrayList<String>();

                List<BluetoothGattCharacteristic> characteristicList = bleInfo.getCharacteristicList();
                for (BluetoothGattCharacteristic characteristic : characteristicList){
                    characteristicUuids.add(characteristic.getUuid().toString());
                }

                startActivity(new Intent(context , CharacteristicInfoActivity.class)
                .putExtra("mAddress" , mAddress)
                        .putExtra("serviceUuid" , serviceUuid)
                        .putExtra("characteristicUuids" , characteristicUuids)
                );

            }
        });

    }

    private void addBleListeners(){
        BleCallBackContrl.getInstance().addStateListener(bleStateListener);
        BleCallBackContrl.getInstance().addServiceListener(bleServiceListener);
    }

    BleServiceListener bleServiceListener = new BleServiceListener(){
        @Override
        public void onDevServicesDiscovered(final BluetoothGatt gatt, int status) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String address = gatt.getDevice().getAddress();

                    if (!address.equals(mAddress)){
                        return;
                    }

                    String devName = gatt.getDevice().getName();
                    commonTitleView.setCenterTitleText(TextUtils.isEmpty(devName) ? "设备信息" : devName);

                    List<BluetoothGattService> servicelist = gatt.getServices();
                    if (null == servicelist){
                        return;
                    }
                    for (BluetoothGattService service : servicelist){
                        BleInfo bleInfo = new BleInfo();
                        bleInfo.setService(service);

                        List<BluetoothGattCharacteristic> characteristicList = service.getCharacteristics();

                        bleInfo.setCharacteristicList(characteristicList);

                        list.add(bleInfo);
                    }

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
                    if (!TextUtils.isEmpty(address)
                            && !TextUtils.isEmpty(mAddress)
                            && address.equals(mAddress)){
                        if (bleState.getState() == BleState.STATE_DISCONNECTED.getState()){
                            onBackPressed();
                        }
                    }
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        BleCallBackContrl.getInstance().removeStateListener(bleStateListener);
        BleCallBackContrl.getInstance().removeServiceListener(bleServiceListener);
        super.onDestroy();
    }

    private void addEmptyView(){
        ViewGroup viewGroup =  (ViewGroup) listView.getParent();

        BaseEmptyLoadingView emptyLoadingView = new BaseEmptyLoadingView(context);

        viewGroup.addView(emptyLoadingView);
        listView.setEmptyView(emptyLoadingView);
    }
}
