package com.bluetooth.lib.imp.gattcallback

enum class BleDataType(var type : Int) {

    STANDARD_HR(1),
    BATTERY_LEVEL(2),
    FIRMWARE_VERSION(3),   //firmwareVersion
    MANUFACTURE_NAME(4),   //manufactureName
    MODEL_NUMBER(5),   //modelNumber
    SERIAL_NUMBER(6),   //serialNumber
    HARDWARE_VERSION(7),   //hardwareRevision
    SOFTWARE_VERSION(8),   //softwareVersion
    SYSTEM_ID(9),   //SYSTEM ID
    LD_Data(10)   //LD_1, LD_2

}