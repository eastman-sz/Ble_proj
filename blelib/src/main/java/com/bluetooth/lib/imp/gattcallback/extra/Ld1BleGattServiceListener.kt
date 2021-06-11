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
import com.bluetooth.lib.util.BleUtils
/**
 * LD I
 * @author E
 */
class Ld1BleGattServiceListener : BleGattServiceListener() {

    override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
        val uuid = characteristic.uuid
        val data = characteristic.value

        when(uuid){
            BleCommonUUIDs.STEAMING_DATA_UUID ->{
                //realTime data
                if (data.size != 4) {
                    return
                }
                val hr = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1)
                val step = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2)
                val activitys = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 3)

                val json = BleJsonTools.JSONBean()
                        .put("name" , gatt.device.name)
                        .put("address" , gatt.device.address)
                        .put("hr" , hr)
                        .put("steps" , step)
                        .put("activitys" , activitys)
                        .put("dataType" , BleDataType.LD_Data.type)
                        .buildJson()

                BleLog.d("----LD-I-RealTimeData--: $json   name: ${gatt.device.name}")

                //call back
                BleCallbackInvoke.onBleDataReceived(BleDataType.LD_Data.type , gatt.device.address , json)

                //发送广播
                SdkBleBroadcastHelper.onSdkBleDataReceive(json)
            }

            BleCommonUUIDs.BURST_DATA_UUID ->{
                //一代设备离线数据
                val hexString = BleUtils.byteArray2Hex(data)

                BleLog.e("----LD-I-offlineData--: $hexString   name: ${gatt.device.name}")
            }
        }

    }

    override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
        //realTime data
        handler.postDelayed({
            BleCharacteristicHelper.notifyCharacteristic(gatt, BleCommonUUIDs.DATA_UUID, BleCommonUUIDs.STEAMING_DATA_UUID)
        } , delayMillis)

        //offline data
        handler.postDelayed({
            BleCharacteristicHelper.notifyCharacteristic(gatt, BleCommonUUIDs.DATA_UUID, BleCommonUUIDs.BURST_DATA_UUID)
        } , delayMillis*2)
        handler.postDelayed({
            val hexCommand = "0403A27469FE"
            val command = BleUtils.hexTextToBytes(hexCommand)
            BleCharacteristicHelper.writeCommandToCharacteristic(gatt, BleCommonUUIDs.DATA_UUID, BleCommonUUIDs.MESSAGE_UUID, command)
        } , delayMillis*3)

    }

}