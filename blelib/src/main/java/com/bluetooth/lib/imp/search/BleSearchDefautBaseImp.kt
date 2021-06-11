package com.bluetooth.lib.imp.search

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
/**
 * 系统的。原始的搜索方式。这里主要是注册广播。
 * @author E
 */
open class BleSearchDefautBaseImp : BleSearchInterface {

    private var defBleScannerBroadcastReceiver : DefBleScannerBroadcastReceiver ?= null

    constructor(context: Context) : super(context)

    override fun readySearch() {
        registerDefBleScannerBroadcast()
    }

    override fun stopSearch() {
        super.stopSearch()
        unregisterDefBleScannerBroadcast()
    }

    private fun registerDefBleScannerBroadcast(){
        if (null == defBleScannerBroadcastReceiver){
            defBleScannerBroadcastReceiver = DefBleScannerBroadcastReceiver()

            val filter = IntentFilter()
            filter.addAction(BluetoothDevice.ACTION_FOUND)
            context?.registerReceiver(defBleScannerBroadcastReceiver , filter)
        }
    }

    private fun unregisterDefBleScannerBroadcast(){
        if (null != defBleScannerBroadcastReceiver){
            context?.unregisterReceiver(defBleScannerBroadcastReceiver)
            defBleScannerBroadcastReceiver = null
        }
    }

    inner class DefBleScannerBroadcastReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            when(action){
                BluetoothDevice.ACTION_FOUND -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)

                    onBluetoothDeviceFound(device)
                }
            }
        }
    }

    open fun onBluetoothDeviceFound(bluetoothDevice : BluetoothDevice){

    }

}