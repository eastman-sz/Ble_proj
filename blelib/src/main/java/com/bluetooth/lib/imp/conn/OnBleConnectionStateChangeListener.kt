package com.bluetooth.lib.imp.conn
/**
 * Call back when the connection state change.
 * @author E
 */
interface OnBleConnectionStateChangeListener {

    fun onStateChange(address : String , state : Int)

}