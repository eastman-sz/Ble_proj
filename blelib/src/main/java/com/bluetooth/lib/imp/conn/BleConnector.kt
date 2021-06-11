package com.bluetooth.lib.imp.conn

import android.bluetooth.BluetoothGatt
import android.content.Context
import android.os.Build
/**
 * Bluetooth connection.
 * @author E
 */
class BleConnector {

    private var bleConnectInterface : BleConnectInterface ?= null

    constructor(context: Context , address : String){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            bleConnectInterface = BleConnectSDK23Imp(context , address)
        }else{
            bleConnectInterface = BleConnectSDK20Imp(context ,address)
        }
    }

    fun startConnect(){
        bleConnectInterface?.startConnect()
    }

    fun disConnect(){
        bleConnectInterface?.disConnect()
    }

    fun getBluetoothGatt() : BluetoothGatt? {
        return bleConnectInterface?.getBluetoothGatt()
    }


}