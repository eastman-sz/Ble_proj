package com.bluetooth.lib.imp.search

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

object BleStatusHelper {

    val REQUEST_ENABLE_BT = 0X12048

    /**
     * 是否有蓝牙功能。
     * @param context 上下文
     */
    fun hasBleFeatures(context: Context?): Boolean {
        return context?.packageManager?.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
                ?: false
    }

    /**
     * 蓝牙是否打开。
     * @param context 上下文环境
     */
    fun isBleOpened(context: Context): Boolean {
        val bluetoothAdapter = getBluetoothAdapter(context)
        return null != bluetoothAdapter && bluetoothAdapter!!.isEnabled
    }

    /**
     * 打开蓝牙。
     * @param context 上下文环境
     */
    fun openBle(context: Context) {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if (context is Activity) {
            context.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
    }

    fun enableBle(context: Context) {
        val bluetoothAdapter = getBluetoothAdapter(context) ?: return
        bluetoothAdapter!!.enable()
    }

    /**
     * 关闭蓝牙。
     * @param context
     */
    fun disEnableBle(context: Context) {
        val bluetoothAdapter = getBluetoothAdapter(context) ?: return
        bluetoothAdapter!!.disable()
    }

    /**
     * 蓝牙适配器。
     * @param context
     * @return
     */
    fun getBluetoothAdapter(context: Context): BluetoothAdapter {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        return bluetoothManager.adapter
    }


}