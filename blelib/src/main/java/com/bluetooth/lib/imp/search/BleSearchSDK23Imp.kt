package com.bluetooth.lib.imp.search

import android.annotation.TargetApi
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import com.bluetooth.lib.util.BleLog
/**
 * Version 6.0
 * @author E
 */
class BleSearchSDK23Imp : BleSearchInterface {

    constructor(context: Context?) : super(context)

    @TargetApi(android.os.Build.VERSION_CODES.M)
    override fun readySearch(){
        val bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().bluetoothLeScanner
        bluetoothLeScanner.flushPendingScanResults(scanCallback)
        bluetoothLeScanner.startScan(scanCallback)

    }

    @TargetApi(android.os.Build.VERSION_CODES.M)
    override fun stopSearch() {
        super.stopSearch()
        val bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().bluetoothLeScanner
        bluetoothLeScanner?.stopScan(scanCallback)
    }

    @TargetApi(android.os.Build.VERSION_CODES.M)
    private val scanCallback = object : ScanCallback(){
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            BleLog.d("搜索结果23: ${result.device.name}  ${result.device.address}")

            onResult(result.device , result.rssi , result.scanRecord.bytes)
        }
    }

}