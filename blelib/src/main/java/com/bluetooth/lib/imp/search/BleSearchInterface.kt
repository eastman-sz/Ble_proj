package com.bluetooth.lib.imp.search

import android.bluetooth.BluetoothDevice
import android.content.Context
import com.bluetooth.lib.broadcast.SdkBleBroadcastHelper
import com.bluetooth.lib.util.BleLog
import com.bluetooth.lib.util.BleSdkPermissionHelper

/**
 * Searching interface.
 * @author E
 */
abstract class BleSearchInterface {

    protected var context : Context ?= null

    constructor(context: Context?){
        this.context = context
    }

    open fun startSearch(){
        BleSdkPermissionHelper.onBlePermissionRequest(context , object : OnBlePermissionRequestResultListener{
            override fun onDenied() {
                BleLog.e("====没有蓝牙相关权限======")
            }
            override fun onGranted() {
                readySearch()

                //Send searchState
                SdkBleBroadcastHelper.onSdkBleSearchStart()
            }
        })
    }

    protected abstract fun readySearch()

    open fun stopSearch(){
        SdkBleBroadcastHelper.onSdkBleSearchStop()
    }

    protected fun onResult(bluetoothDevice : BluetoothDevice, rssi : Int, scanRecord : ByteArray){
        bluetoothDevice?.name?.let {
            //Send broadcast
            SdkBleBroadcastHelper.onSdkBleSearchResult(bluetoothDevice, rssi, scanRecord)
        }


    }
}