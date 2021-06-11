package com.bluetooth.lib.imp.bean

open class SDKBleDevice {

    open var address : String ?= null
    open var name : String ?= null
    open var type = 0 //设备类型
    open var state = 0 //设备的连接状态

    open var firmwareVersion : String ?= null
    open var manufactureName : String ?= null
    open var modelNumber : String ?= null
    open var serialNumber : String ?= null
    open var hardwareRevision : String ?= null
    open var softwareVersion : String ?= null
    open var systemId : String ?= null

    open var rssi = 0 //设备的信号强弱

    open var newGet = 0 //是否是当次APP周期内的设备。应用重启后，置为0 ， 添加置为1.

}