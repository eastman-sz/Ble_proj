package com.bluetooth.lib.imp.conn

import android.bluetooth.BluetoothGatt
/**
 * 在onServicesDiscovered调用后记录BluetoothGatt,断开时清除。
 * @param E
 */
class BleGattRecorder {

    private val map = HashMap<String , BluetoothGatt>()

    private constructor()

    companion object{
        val instance : BleGattRecorder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { BleGattRecorder() }
    }

    fun add(gatt : BluetoothGatt){
        add(gatt.device.address , gatt)
    }

    private fun add(address : String , gatt : BluetoothGatt){
        map[address] = gatt
    }

    fun getBluetoothGatt(address : String) : BluetoothGatt?{
        if (!map.containsKey(address)){
            return null
        }
        return map[address]
    }

    fun clear(address : String){
        if (map.containsKey(address)){
            map.remove(address)
        }
    }

    fun clear(){
        map.clear()
    }



}