package com.bluetooth.lib.imp.conn

enum class BleState(var state : Int) {

    STATE_CONNECTED(2),
    STATE_CONNECTING(1),
    STATE_DISCONNECTED(0),
    STATE_DISCONNECTING(3),
    STATE_OFF(4), //蓝牙关闭
    STATE_ON(5) //蓝牙开启
}