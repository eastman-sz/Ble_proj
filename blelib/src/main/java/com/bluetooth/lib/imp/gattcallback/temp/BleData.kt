package com.bluetooth.lib.imp.gattcallback.temp

class BleData {

    var batteryLevel = 0
    var firmwareVersion : String ?= null
    var manufactureName : String ?= null
    var modelNumber : String ?= null
    var serialNumber : String ?= null
    var hardwareRevision : String ?= null
    var softwareVersion : String ?= null
    var systemId : String ?= null

    //extra
    var hr = 0
    var steps = 0
    var activitys = 0
}