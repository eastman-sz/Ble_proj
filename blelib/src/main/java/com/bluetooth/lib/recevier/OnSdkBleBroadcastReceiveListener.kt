package com.bluetooth.lib.recevier

import android.bluetooth.BluetoothDevice

open class OnSdkBleBroadcastReceiveListener {

    open fun onSdkBleSearchStart(){}

    open fun onSdkBleSearchStop(){}

    open fun onSdkBleSearchResult(bluetoothDevice : BluetoothDevice, rssi : Int, scanRecord : ByteArray){}

    open fun onSdkBleStateChange(address : String , name : String?, state : Int){}

    open fun onSdkBleDataReceived(jsonData : String){}

    open fun onSdkBleServiceDiscovered(address : String , name : String?){}

    /**
     * BluetoothAdapter.STATE_OFF
     * @param state BluetoothAdapter.STATE_OFF , BluetoothAdapter.STATE_ON , BluetoothAdapter.STATE_TURNING_ON , BluetoothAdapter.STATE_TURNING_OFF
     */
    open fun onSdkBleSystemStateChange(state: Int){}


}