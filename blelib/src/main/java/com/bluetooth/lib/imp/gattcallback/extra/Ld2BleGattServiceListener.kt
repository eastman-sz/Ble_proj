package com.bluetooth.lib.imp.gattcallback.extra

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.bluetooth.lib.broadcast.SdkBleBroadcastHelper
import com.bluetooth.lib.imp.conn.BleCallbackInvoke
import com.bluetooth.lib.imp.gattcallback.BleCharacteristicHelper
import com.bluetooth.lib.imp.gattcallback.BleDataType
import com.bluetooth.lib.imp.gattcallback.BleGattServiceListener
import com.bluetooth.lib.util.BleCommonUUIDs
import com.bluetooth.lib.util.BleJsonTools
import com.bluetooth.lib.util.BleLog
/**
 * LD II
 * @author E
 */
class Ld2BleGattServiceListener : BleGattServiceListener() {

    override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
        val uuid = characteristic.uuid
        val data = characteristic.value

        when(uuid){
            BleCommonUUIDs.SP102_CHARACTER_4_UUID ->{
                if (null == data || data.size < 4) {
                    return
                }

                val hr = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1)!!
                val step = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2)!!
                val activitys = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 3)!!

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("hr" , hr)
                        .put("steps" , step)
                        .put("activitys" , activitys)
                        .put("dataType" , BleDataType.LD_Data.type)
                        .buildJson()

                BleLog.d("----LD-II-RealTimeData--: $json   name: ${gatt.device.name}")

                //call back
                BleCallbackInvoke.onBleDataReceived(BleDataType.LD_Data.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)
            }
        }
    }

    override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
        //realTime data
        handler.postDelayed({
            BleCharacteristicHelper.notifyCharacteristic(gatt, BleCommonUUIDs.SP102_SERVICE_UUID, BleCommonUUIDs.SP102_CHARACTER_4_UUID)
        } , delayMillis)
    }

}