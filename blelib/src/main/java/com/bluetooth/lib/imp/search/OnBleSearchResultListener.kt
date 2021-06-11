package com.bluetooth.lib.imp.search

import android.bluetooth.BluetoothDevice
/**
 * Call back for bluetooth search.
 * @author E
 */
open class OnBleSearchResultListener {

    open fun onPermissionDenied(){}

    open fun onStart(){}

    open fun onStop(){}

    open fun onResult(bluetoothDevice : BluetoothDevice , rssi : Int , scanRecord : ByteArray){}

}