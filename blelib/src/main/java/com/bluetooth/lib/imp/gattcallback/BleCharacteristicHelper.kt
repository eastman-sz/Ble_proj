package com.bluetooth.lib.imp.gattcallback

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattDescriptor
import java.util.*
/**
 * Basic function ,include read,write,notify
 * @author E
 */
class BleCharacteristicHelper {

    companion object {

        /**
         * Read basic characteristic ,eg.battery,version ,model number
         * @param gatt
         * @param serviceUUID
         * @param characteristicUUID
         * @return
         */
        fun readCharacteristic(gatt: BluetoothGatt?, serviceUUID: UUID, characteristicUUID: UUID): Boolean {
            try {
                if (null == gatt) {
                    return false
                }
                val service = gatt.getService(serviceUUID) ?: return false
                val characteristic = service.getCharacteristic(characteristicUUID) ?: return false
                return gatt.readCharacteristic(characteristic)
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }


        /**
         * Notify characteristics
         * @param gatt
         * @param serviceUUID
         * @param characteristicUUID
         * @return
         */
        fun notifyCharacteristic(gatt: BluetoothGatt?, serviceUUID: UUID, characteristicUUID: UUID): Boolean {
            try {
                if (null == gatt) {
                    return false
                }
                val service = gatt.getService(serviceUUID) ?: return false
                val characteristic = service.getCharacteristic(characteristicUUID) ?: return false
                val isSet = gatt.setCharacteristicNotification(characteristic, true)
                if (!isSet) {
                    return false
                }
                val descriptorList = characteristic.descriptors
                if (descriptorList != null && descriptorList.size > 0) {
                    for (descriptor in descriptorList) {
                        descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                        gatt.writeDescriptor(descriptor)
                    }
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }

        /**
         * Write command to characteristic.
         * @param gatt
         * @param serviceUUID
         * @param characteristicUUID
         * @param hexCommand
         * @return
         */
        fun writeCommandToCharacteristic(gatt: BluetoothGatt?, serviceUUID: UUID, characteristicUUID: UUID, hexCommand: String): Boolean {
            try {
                if (null == gatt) {
                    return false
                }
                val service = gatt.getService(serviceUUID) ?: return false
                val characteristic = service.getCharacteristic(characteristicUUID) ?: return false
                characteristic.setValue(hexCommand)
                return gatt.writeCharacteristic(characteristic)
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }

        /**
         * Write command to characteristic.
         * @param gatt
         * @param serviceUUID
         * @param characteristicUUID
         * @param command
         * @return
         */
        fun writeCommandToCharacteristic(gatt: BluetoothGatt?, serviceUUID: UUID, characteristicUUID: UUID, command: ByteArray): Boolean {
            try {
                if (null == gatt) {
                    return false
                }
                val service = gatt.getService(serviceUUID) ?: return false
                val characteristic = service.getCharacteristic(characteristicUUID) ?: return false
                characteristic.value = command
                return gatt.writeCharacteristic(characteristic)
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }

    }


}