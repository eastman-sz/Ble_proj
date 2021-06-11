package com.bluetooth.lib.imp.conn

import android.bluetooth.BluetoothAdapter
import android.content.Context

class BleConnectSDK23Imp : BleConnectInterface {

    constructor(context: Context , address : String) : super(context, address){
    }

    override fun getBluetoothAdapter(): BluetoothAdapter {
        return BluetoothAdapter.getDefaultAdapter()
    }

}