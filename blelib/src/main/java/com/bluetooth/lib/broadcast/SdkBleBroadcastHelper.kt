package com.bluetooth.lib.broadcast

import android.bluetooth.BluetoothDevice
import android.content.Intent
import com.alibaba.fastjson.JSON
import com.bluetooth.lib.util.BleLog
import com.bluetooth.lib.util.BleSdkContext

class SdkBleBroadcastHelper {

    companion object {

        //开始搜索。
        private val onSdkBleSearchStartIntent = Intent("onSdkBleSearchStart")
        //停止搜索。
        private val onSdkBleSearchStopIntent = Intent("onSdkBleSearchStop")
        //搜索到设备.
        private val onSdkBleSearchResultIntent = Intent("onSdkBleSearchResult")
        //设备状态变化。
        private val onSdkBleStateChangeIntent = Intent("onSdkBleStateChange")
        //收到数据。
        private val onSdkBleDataReceiveIntent = Intent("onSdkBleDataReceive")
        //发现蓝牙服务。
        private val onSdkBleServiceDiscoveredIntent = Intent("onSdkBleServiceDiscovered")

        /**
         * 开始搜索。
         */
        fun onSdkBleSearchStart(){
            BleSdkContext.context?.sendBroadcast(onSdkBleSearchStartIntent)
        }

        /**
         * 停止搜索。
         */
        fun onSdkBleSearchStop(){
            BleSdkContext.context?.sendBroadcast(onSdkBleSearchStopIntent)
        }

        /**
         * 搜索到设备.
         */
        fun onSdkBleSearchResult(bluetoothDevice : BluetoothDevice, rssi : Int, scanRecord : ByteArray){
            onSdkBleSearchResultIntent.putExtra("bluetoothDevice" , bluetoothDevice)
            onSdkBleSearchResultIntent.putExtra("rssi" , rssi)
            onSdkBleSearchResultIntent.putExtra("scanRecord" , scanRecord)
            BleSdkContext.context?.sendBroadcast(onSdkBleSearchResultIntent)
        }

        /**
         * 设备状态变化。
         */
        fun onSdkBleStateChange(address : String , name : String? , state : Int){
            onSdkBleStateChangeIntent.putExtra("address" , address)
            onSdkBleStateChangeIntent.putExtra("name" , name)
            onSdkBleStateChangeIntent.putExtra("state" , state)
            BleSdkContext.context?.sendBroadcast(onSdkBleStateChangeIntent)
        }

        /**
         * 收到数据。
         */
        fun onSdkBleDataReceive(jsonData : String){
            onSdkBleDataReceiveIntent.putExtra("sdkBleJsonData" , jsonData)
            BleSdkContext.context?.sendBroadcast(onSdkBleDataReceiveIntent)
        }

        /**
         * 发现蓝牙服务
         */
        fun onBleSdkServiceDiscovered(address : String , name : String?){
            onSdkBleServiceDiscoveredIntent.putExtra("address" , address)
            onSdkBleServiceDiscoveredIntent.putExtra("name" , name)
            BleSdkContext.context?.sendBroadcast(onSdkBleServiceDiscoveredIntent)
        }


    }

}