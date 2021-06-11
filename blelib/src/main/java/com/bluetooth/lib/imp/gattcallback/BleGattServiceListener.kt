package com.bluetooth.lib.imp.gattcallback

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.os.Handler
import android.os.Looper

/**
 * Base interface for bluetooth gatt callback.
 * @author E
 */
abstract class BleGattServiceListener {

    protected val delayMillis = 500L

    abstract fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic)

    open fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int){}

    open fun onCharacteristicWrite(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int){}

    abstract fun onServicesDiscovered(gatt: BluetoothGatt, status: Int)

    protected val handler = Handler(Looper.getMainLooper())
}