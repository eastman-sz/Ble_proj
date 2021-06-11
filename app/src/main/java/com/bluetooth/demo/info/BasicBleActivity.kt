package com.bluetooth.demo.info

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import com.bluetooth.lib.imp.conn.BleCallbackMgr
import com.bluetooth.lib.imp.conn.BleConnectHelper
import com.bluetooth.lib.imp.conn.OnBleConnectionStateChangeListener
import com.bluetooth.lib.imp.gattcallback.OnBleDataReceiveListener
import com.bluetooth.lib.recevier.OnSdkBleBroadcastReceiveListener
import com.bluetooth.lib.recevier.SdkBleBroadcastReceiver
import com.bluetooth.lib.util.BleLog
import com.common.base.BaseAppCompactActivitiy

open class BasicBleActivity : BaseAppCompactActivitiy() {

    private val sdkBleBroadcastReceiver = SdkBleBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BleLog.e("=====广播注册==================")
        sdkBleBroadcastReceiver.register()
        sdkBleBroadcastReceiver.onSdkBleBroadcastReceiveListener = object : OnSdkBleBroadcastReceiveListener(){
            override fun onSdkBleSearchStart() {
                BleLog.e("=====开始搜索==================")
            }

            override fun onSdkBleSearchStop() {
                BleLog.e("=====停止搜索==================")
            }
            override fun onSdkBleSearchResult(bluetoothDevice: BluetoothDevice, rssi: Int, scanRecord: ByteArray) {
                onSdkBleSearchResultGet(bluetoothDevice , rssi , scanRecord)
            }
            override fun onSdkBleDataReceived(jsonData: String) {
                BleLog.e("=====蓝牙数据============jsonData: $jsonData")
            }

            override fun onSdkBleStateChange(address: String, name: String?, state: Int) {
                BleLog.e("状态变化onSdkBleStateChange00:  address:$address  name: $name   state:$state")
                onBleStateChanged(address , state)
            }

            override fun onSdkBleServiceDiscovered(address: String, name: String?) {
                BleLog.e("=====发现服务============address: $address    name: $name")
            }
        }
    }


    open fun startConnect(address: String){
        BleConnectHelper.startConnect(context , address)
    }

    open fun disconnect(address: String){
        BleConnectHelper.disconnect(address)
    }

    open fun onSdkBleSearchResultGet(bluetoothDevice: BluetoothDevice, rssi: Int, scanRecord: ByteArray){

    }

    open fun onBleStateChanged(address: String, state: Int){

    }

    open fun onBleDataReceived(dataType: Int, address: String, data: String){

    }

    override fun onDestroy() {
        sdkBleBroadcastReceiver.unRegister()
        super.onDestroy()
    }



}
