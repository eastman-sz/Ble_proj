package com.bluetooth.lib.imp.conn

import android.bluetooth.BluetoothGatt
import android.content.Context

object BleConnectHelper {

    fun startConnect(context: Context , address : String){
        val bleConnector = BleConnectorMgr.getBleConnectorWithDefault(context , address)
        bleConnector?.startConnect()
    }

    fun disconnect(address : String){
        val bleConnector = BleConnectorMgr.getBleConnector(address)
        bleConnector?.let {
            it.disConnect()
        }
    }

    fun getBluetoothGatt(address : String) : BluetoothGatt?{
        val bleConnector = BleConnectorMgr.getBleConnector(address)
        return bleConnector?.getBluetoothGatt()
    }

    fun disconnectAll(){
        BleConnectorMgr.clearAllBleConnector()
    }

}