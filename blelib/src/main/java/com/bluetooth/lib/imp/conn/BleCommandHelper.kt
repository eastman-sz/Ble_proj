package com.bluetooth.lib.imp.conn

import com.bluetooth.lib.imp.gattcallback.BleCharacteristicHelper
import java.util.*
/**
 * Write,Read and notify BLE device.
 * @author E
 */
class BleCommandHelper {

    private constructor()

    companion object{
        val instance : BleCommandHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { BleCommandHelper() }
    }

    fun write(address : String , serviceUUID: UUID, characteristicUUID: UUID , command : String) : Boolean{
        val gatt = BleGattRecorder.instance.getBluetoothGatt(address) ?: return false
        return BleCharacteristicHelper.writeCommandToCharacteristic(gatt , serviceUUID , characteristicUUID , command)
    }

    fun write(address : String , serviceUUID: UUID, characteristicUUID: UUID , command : ByteArray) : Boolean{
        val gatt = BleGattRecorder.instance.getBluetoothGatt(address) ?: return false
        return BleCharacteristicHelper.writeCommandToCharacteristic(gatt , serviceUUID , characteristicUUID , command)
    }

    fun read(address : String , serviceUUID: UUID, characteristicUUID: UUID) : Boolean{
        val gatt = BleGattRecorder.instance.getBluetoothGatt(address) ?: return false
        return BleCharacteristicHelper.readCharacteristic(gatt , serviceUUID , characteristicUUID)
    }

    fun notifyCharacteristic(address : String , serviceUUID: UUID, characteristicUUID: UUID) : Boolean{
        val gatt = BleGattRecorder.instance.getBluetoothGatt(address) ?: return false
        return BleCharacteristicHelper.notifyCharacteristic(gatt , serviceUUID , characteristicUUID)
    }

}