package com.bluetooth.lib.imp.gattcallback

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.bluetooth.lib.broadcast.SdkBleBroadcastHelper
import com.bluetooth.lib.imp.bean.SDKBleDbHelper
import com.bluetooth.lib.imp.conn.BleCallbackInvoke
import com.bluetooth.lib.imp.gattcallback.temp.BleDataTempUtils
import com.bluetooth.lib.util.BleCommonUUIDs
import com.bluetooth.lib.util.BleJsonTools
import com.bluetooth.lib.util.BleLog
import com.bluetooth.lib.util.BleUtils
/**
 * Basic impl.
 * @author E
 */
class CommonBleGattServiceListener : BleGattServiceListener() {

    override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
        val uuid = characteristic.uuid
        when(uuid){
            BleCommonUUIDs.HEART_RATE_CHARACT_UUID ->{
                //standard heart rate
                val flag = characteristic.properties
                var format = -1
                if (flag and 0x01 != 0) {
                    format = BluetoothGattCharacteristic.FORMAT_UINT16
                    //Heart rate format UINT16.
                } else {
                    format = BluetoothGattCharacteristic.FORMAT_UINT8
                    //Heart rate format UINT8.
                }
                val heartRate = characteristic.getIntValue(format, 1)!!

                BleLog.d("--标准心率数据--: heartRate: $heartRate   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("hr" , heartRate)
                        .put("dataType" , BleDataType.STANDARD_HR.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.STANDARD_HR.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)
            }
        }
    }

    override fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
        val address = gatt.device.address
        val uuid = characteristic.uuid
        val data = characteristic.value
        val hexString = BleUtils.byteArray2Hex(data)
        when(uuid){
            BleCommonUUIDs.Battery_LEVEL_UUID ->{
                //battery level
                val batteryLevel = Integer.valueOf(hexString, 16)

                BleLog.d("--电量--: batteryLevel: $batteryLevel   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("batteryLevel" , batteryLevel)
                        .put("dataType" , BleDataType.BATTERY_LEVEL.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.BATTERY_LEVEL.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.BATTERY_LEVEL.type , gatt.device.address , batteryLevel)
            }

            BleCommonUUIDs.FIRMWARE_VERSION_CHARACTER_UUID ->{
                //Firmware version
                val msg = String(hexString.toByteArray())
                val firmwareVersion = BleUtils.hexStr2Str(msg)

                BleLog.d("--firmwareVersion--: firmwareVersion: $firmwareVersion   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("firmwareVersion" , firmwareVersion)
                        .put("dataType" , BleDataType.FIRMWARE_VERSION.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.FIRMWARE_VERSION.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.FIRMWARE_VERSION.type , gatt.device.address , firmwareVersion)

                //save db
                SDKBleDbHelper.saveFirmwareVersion(address , firmwareVersion)
            }

            BleCommonUUIDs.MANUFACTURE_NAME_CHARACTER_UUID ->{
                //Manufacture Name
                val msg = String(hexString.toByteArray())
                val manufactureName = BleUtils.hexStr2Str(msg)

                BleLog.d("--manufactureName--: manufactureName: $manufactureName   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("manufactureName" , manufactureName)
                        .put("dataType" , BleDataType.MANUFACTURE_NAME.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.MANUFACTURE_NAME.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.MANUFACTURE_NAME.type , gatt.device.address , manufactureName)

                //save db
                SDKBleDbHelper.saveManufactureName(address , manufactureName)
            }

            BleCommonUUIDs.MODEL_NUMBER_CHARACTER_UUID ->{
                //Model Number
                val msg = String(hexString.toByteArray())
                val modelNumber = BleUtils.hexStr2Str(msg)

                BleLog.d("--modelNumber--: modelNumber: $modelNumber   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("modelNumber" , modelNumber)
                        .put("dataType" , BleDataType.MODEL_NUMBER.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.MODEL_NUMBER.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.MODEL_NUMBER.type , gatt.device.address , modelNumber)

                //save db
                SDKBleDbHelper.saveModelNumber(address , modelNumber)
            }

            BleCommonUUIDs.SERIAL_NUMBER_CHARACTER_UUID ->{
                //Serial Number
                val msg = String(hexString.toByteArray())
                val serialNumber = BleUtils.hexStr2Str(msg)

                BleLog.d("--serialNumber--: serialNumber: $serialNumber   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("serialNumber" , serialNumber)
                        .put("dataType" , BleDataType.SERIAL_NUMBER.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.SERIAL_NUMBER.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.SERIAL_NUMBER.type , gatt.device.address , serialNumber)

                //save db
                SDKBleDbHelper.saveSerialNumber(address , serialNumber)
            }

            BleCommonUUIDs.HARDWARE_REVISION_CHARACTER_UUID ->{
                //Hardware Revision
                val msg = String(hexString.toByteArray())
                val hardwareRevision = BleUtils.hexStr2Str(msg)

                BleLog.d("--hardwareRevision--: hardwareRevision: $hardwareRevision   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("hardwareRevision" , hardwareRevision)
                        .put("dataType" , BleDataType.HARDWARE_VERSION.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.HARDWARE_VERSION.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.HARDWARE_VERSION.type , gatt.device.address , hardwareRevision)

                //save db
                SDKBleDbHelper.saveHardwareRevision(address , hardwareRevision)
            }

            BleCommonUUIDs.SOFTWARE_VERSION_CHARACTER_UUID ->{
                //SOFTWARE version
                val msg = String(hexString.toByteArray())
                val softwareVersion = BleUtils.hexStr2Str(msg)

                BleLog.d("--softwareVersion--: softwareVersion: $softwareVersion   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("softwareVersion" , softwareVersion)
                        .put("dataType" , BleDataType.SOFTWARE_VERSION.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.SOFTWARE_VERSION.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.SOFTWARE_VERSION.type , gatt.device.address , softwareVersion)

                //save db
                SDKBleDbHelper.saveSoftwareVersion(address , softwareVersion)
            }

            BleCommonUUIDs.SYSTEM_ID_CHARACTER_UUID ->{
                //SYSTEM ID

                BleLog.d("--SYSTEM_ID--: SYSTEM_ID: $hexString   name: ${gatt.device.name}")

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("systemId" , hexString)
                        .put("dataType" , BleDataType.SYSTEM_ID.type)
                        .buildJson()

                BleCallbackInvoke.onBleDataReceived(BleDataType.SYSTEM_ID.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)

                //Temp
                BleDataTempUtils.put(BleDataType.SYSTEM_ID.type , gatt.device.address , hexString)

                //save db
                SDKBleDbHelper.saveSystemId(address , hexString)
            }

        }
    }

    override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
        BleCommonServicesDiscoveredHelper.onServicesDiscovered(gatt)

        //save db
        SDKBleDbHelper.save(gatt.device.address , gatt.device.name)
    }

}