package com.bluetooth.lib.imp.gattcallback

import android.bluetooth.BluetoothGatt
import android.os.Handler
import android.os.Looper
import com.bluetooth.lib.util.BleCommonUUIDs
/**
 * Inspire common service.it will take 5 seconds.
 * @author E
 */
class BleCommonServicesDiscoveredHelper {

    companion object {

        private const val delayMillis = 500L

        fun onServicesDiscovered(gatt: BluetoothGatt){
            handler.postDelayed({readBatteryLevel(gatt)} , delayMillis*1)
            handler.postDelayed({notifyHRateData(gatt)} , delayMillis*2)
            handler.postDelayed({readSystemID(gatt)} , delayMillis*3)
            handler.postDelayed({readSoftwareRevision(gatt)} , delayMillis*4)
            handler.postDelayed({readHardwareRevision(gatt)} , delayMillis*5)
            handler.postDelayed({readSerialNumber(gatt)} , delayMillis*6)
            handler.postDelayed({readModelNumber(gatt)} , delayMillis*7)
            handler.postDelayed({readManufactureName(gatt)} , delayMillis*8)
            handler.postDelayed({readFirmwareVersion(gatt)} , delayMillis*9)
        }

        private val handler = Handler(Looper.getMainLooper()){
            true
        }

        /**
         * Read battery level
         * @param gatt BluetoothGatt
         */
        private fun readBatteryLevel(gatt: BluetoothGatt): Boolean {
            return BleCharacteristicHelper.notifyCharacteristic(gatt, BleCommonUUIDs.BATTERY_SERVICE_UUID, BleCommonUUIDs.Battery_LEVEL_UUID)
        }

        /**
         * SoftwareRevision
         * @param gatt BluetoothGatt
         */
        private fun readSystemID(gatt: BluetoothGatt) {
            BleCharacteristicHelper.readCharacteristic(gatt, BleCommonUUIDs.SYSTEM_INFO_SERVICE_UUID, BleCommonUUIDs.SYSTEM_ID_CHARACTER_UUID)
        }

        /**
         * SoftwareRevision
         * @param gatt BluetoothGatt
         */
        private fun readSoftwareRevision(gatt: BluetoothGatt) {
            BleCharacteristicHelper.readCharacteristic(gatt, BleCommonUUIDs.SYSTEM_INFO_SERVICE_UUID, BleCommonUUIDs.SOFTWARE_VERSION_CHARACTER_UUID)
        }

        /**
         * readSerialNumber
         * @param gatt BluetoothGatt
         */
        private fun readHardwareRevision(gatt: BluetoothGatt) {
            BleCharacteristicHelper.readCharacteristic(gatt, BleCommonUUIDs.SYSTEM_INFO_SERVICE_UUID, BleCommonUUIDs.HARDWARE_REVISION_CHARACTER_UUID)
        }

        /**
         * readSerialNumber
         * @param gatt BluetoothGatt
         */
        private fun readSerialNumber(gatt: BluetoothGatt) {
            BleCharacteristicHelper.readCharacteristic(gatt, BleCommonUUIDs.SYSTEM_INFO_SERVICE_UUID, BleCommonUUIDs.SERIAL_NUMBER_CHARACTER_UUID)
        }

        /**
         * ModelNumber
         * @param gatt BluetoothGatt
         */
        private fun readModelNumber(gatt: BluetoothGatt) {
            BleCharacteristicHelper.readCharacteristic(gatt, BleCommonUUIDs.SYSTEM_INFO_SERVICE_UUID, BleCommonUUIDs.MODEL_NUMBER_CHARACTER_UUID)
        }

        /**
         * 读取工厂名
         * @param gatt BluetoothGatt
         */
        private fun readManufactureName(gatt: BluetoothGatt) {
            BleCharacteristicHelper.readCharacteristic(gatt, BleCommonUUIDs.SYSTEM_INFO_SERVICE_UUID, BleCommonUUIDs.MANUFACTURE_NAME_CHARACTER_UUID)
        }

        /**
         * 读取固件版本号
         * @param gatt BluetoothGatt
         */
        private fun readFirmwareVersion(gatt: BluetoothGatt) {
            BleCharacteristicHelper.readCharacteristic(gatt, BleCommonUUIDs.SYSTEM_INFO_SERVICE_UUID, BleCommonUUIDs.FIRMWARE_VERSION_CHARACTER_UUID)
        }

        /**
         * 标准心率
         * @param gatt BluetoothGatt
         */
        private fun notifyHRateData(gatt: BluetoothGatt) {
            BleCharacteristicHelper.notifyCharacteristic(gatt, BleCommonUUIDs.HEART_RATE_SERVICE_UUID, BleCommonUUIDs.HEART_RATE_CHARACT_UUID)
        }

    }

}