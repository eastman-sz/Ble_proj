package com.bluetooth.lib.recevier

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import com.bluetooth.lib.util.BleLog

class SdkBleBroadcastReceiver : BaseBroadcastReceiver() {

    var onSdkBleBroadcastReceiveListener : OnSdkBleBroadcastReceiveListener ?= null

    override fun addActions(): List<String> {
        val list = ArrayList<String>()
        list.add(BluetoothAdapter.ACTION_STATE_CHANGED)
        list.add("onSdkBleSearchStart")
        list.add("onSdkBleSearchStop")
        list.add("onSdkBleSearchResult")
        list.add("onSdkBleStateChange")
        list.add("onSdkBleDataReceive")
        list.add("onSdkBleServiceDiscovered")
        return list
    }

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            "onSdkBleSearchStart" ->{
                onSdkBleBroadcastReceiveListener?.onSdkBleSearchStart()
            }
            "onSdkBleSearchStop" ->{
                onSdkBleBroadcastReceiveListener?.onSdkBleSearchStop()
            }
            "onSdkBleSearchResult" ->{
                BleLog.e("========收到============")

                val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>("bluetoothDevice")
                val rssi = intent.getIntExtra("rssi" , 0)
                val scanRecord = intent.getByteArrayExtra("scanRecord")

                onSdkBleBroadcastReceiveListener?.onSdkBleSearchResult(bluetoothDevice, rssi, scanRecord)
            }

            "onSdkBleStateChange" ->{
                val address = intent.getStringExtra("address")
                val name = intent.getStringExtra("name")
                val state = intent.getIntExtra("state" , 0)

                onSdkBleBroadcastReceiveListener?.onSdkBleStateChange(address, name, state)
            }

            "onSdkBleDataReceive" ->{
                val sdkBleJsonData = intent.getStringExtra("sdkBleJsonData")
                onSdkBleBroadcastReceiveListener?.onSdkBleDataReceived(sdkBleJsonData)
            }

            BluetoothAdapter.ACTION_STATE_CHANGED ->{
                //系统蓝牙状态变化
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                onSdkBleBroadcastReceiveListener?.onSdkBleSystemStateChange(state)
            }

            "onSdkBleServiceDiscovered" ->{
                val address = intent.getStringExtra("address")
                val name = intent.getStringExtra("name")
                onSdkBleBroadcastReceiveListener?.onSdkBleServiceDiscovered(address, name)
            }

        }


    }

}