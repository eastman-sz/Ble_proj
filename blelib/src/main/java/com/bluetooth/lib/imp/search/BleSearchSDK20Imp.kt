package com.bluetooth.lib.imp.search

import android.bluetooth.BluetoothAdapter
import android.content.Context
import com.bluetooth.lib.util.BleLog
/**
 * 最后支持版本20,21及以上@see bluetoothLeScanner.
 */
class BleSearchSDK20Imp : BleSearchInterface {

    constructor(context: Context?) : super(context)

    override fun readySearch(){
        val bluetoothManager = BleStatusHelper.getBluetoothAdapter(context!!)
        bluetoothManager.startLeScan(leScanCallback)
    }

    override fun stopSearch() {
        val bluetoothManager = BleStatusHelper.getBluetoothAdapter(context!!)
        bluetoothManager.stopLeScan(leScanCallback)
    }

    private val leScanCallback = BluetoothAdapter.LeScanCallback{ device, rssi, bytes ->
        BleLog.d("搜索结果20: ${device.name}  ${device.address}")

        onResult(device , rssi , bytes)
    }


}