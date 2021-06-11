package com.bluetooth.lib.imp.gattcallback.temp

import com.bluetooth.lib.imp.gattcallback.BleDataType

class BleDataTempUtils {

    companion object {

        private val map = HashMap<String , HashMap<Int , String>>()

        fun put(dataType : Int , address : String , value : Any){
            if (!map.containsKey(address)){
                map[address] = HashMap()
            }
            map[address]?.put(dataType , value.toString())
        }

        fun get(address: String , dataType: Int) : String?{
            return map[address]?.get(dataType)
        }

        fun get(address: String) : BleData{
            val bleData = BleData()
            if (!map.containsKey(address)){
                return bleData
            }
            val dataMap = map[address]!!
            if (dataMap.containsKey(BleDataType.BATTERY_LEVEL.type)){
                bleData.batteryLevel = dataMap[BleDataType.BATTERY_LEVEL.type]!!.toInt()
            }

            if (dataMap.containsKey(BleDataType.FIRMWARE_VERSION.type)){
                bleData.firmwareVersion = dataMap[BleDataType.FIRMWARE_VERSION.type]
            }

            if (dataMap.containsKey(BleDataType.MANUFACTURE_NAME.type)){
                bleData.manufactureName = dataMap[BleDataType.MANUFACTURE_NAME.type]
            }

            if (dataMap.containsKey(BleDataType.MODEL_NUMBER.type)){
                bleData.modelNumber = dataMap[BleDataType.MODEL_NUMBER.type]
            }

            if (dataMap.containsKey(BleDataType.SERIAL_NUMBER.type)){
                bleData.serialNumber = dataMap[BleDataType.SERIAL_NUMBER.type]
            }

            if (dataMap.containsKey(BleDataType.HARDWARE_VERSION.type)){
                bleData.hardwareRevision = dataMap[BleDataType.HARDWARE_VERSION.type]
            }

            if (dataMap.containsKey(BleDataType.SOFTWARE_VERSION.type)){
                bleData.softwareVersion = dataMap[BleDataType.SOFTWARE_VERSION.type]
            }

            if (dataMap.containsKey(BleDataType.SYSTEM_ID.type)){
                bleData.systemId = dataMap[BleDataType.SYSTEM_ID.type]
            }
            return bleData
        }
    }

}