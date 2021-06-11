package com.bluetooth.lib.imp.conn

import android.bluetooth.BluetoothAdapter
import android.content.Context
import com.bluetooth.lib.imp.search.BleStatusHelper

class BleConnectSDK20Imp : BleConnectInterface {

    constructor(context: Context, address : String) : super(context, address){
    }

    override fun getBluetoothAdapter(): BluetoothAdapter {
        return BleStatusHelper.getBluetoothAdapter(context!!)
    }

}