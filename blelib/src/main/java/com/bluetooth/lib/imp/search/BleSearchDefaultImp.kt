package com.bluetooth.lib.imp.search

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import com.bluetooth.lib.util.BleLog
/**
 * 系统的、最原始的搜索方式。速度慢。
 * @author E
 */
class BleSearchDefaultImp : BleSearchDefautBaseImp {

    constructor(context: Context) : super(context)

    override fun readySearch() {
        super.readySearch()
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter.isDiscovering){
            bluetoothAdapter.cancelDiscovery()
        }
        bluetoothAdapter.startDiscovery()
    }

    override fun stopSearch() {
        super.stopSearch()
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter.cancelDiscovery()
    }

    override fun onBluetoothDeviceFound(device: BluetoothDevice) {
        BleLog.e("搜索结果: ${device.name}  ${device.address}")
    }



}