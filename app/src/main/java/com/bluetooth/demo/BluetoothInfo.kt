package com.bluetooth.demo

import android.bluetooth.BluetoothDevice

class BluetoothInfo {

    var bluetoothDevice : BluetoothDevice ?= null
    var state = 0

    companion object {

        fun makeBluetoothInfo(bluetoothDevice : BluetoothDevice) : BluetoothInfo{
            val device = BluetoothInfo()
            device.bluetoothDevice = bluetoothDevice
            device.state = 0
            return device
        }

    }
}