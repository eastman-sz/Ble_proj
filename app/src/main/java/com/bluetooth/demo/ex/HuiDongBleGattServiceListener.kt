package com.bluetooth.demo.ex

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.bluetooth.demo.huidong.HuiDongCmdHelper
import com.bluetooth.demo.huidong.HuiDongDataHelper
import com.bluetooth.lib.imp.gattcallback.BleCharacteristicHelper
import com.bluetooth.lib.imp.gattcallback.BleGattServiceListener
import com.bluetooth.lib.util.BleCommonUUIDs
import com.bluetooth.lib.util.BleLog
import com.bluetooth.lib.util.BleUtils
import java.util.*

class HuiDongBleGattServiceListener : BleGattServiceListener() {

    override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
        val uuid = characteristic.uuid
        val data = characteristic.value

        val hexString = BleUtils.byteArray2Hex(data)

        BleLog.e("===数据=data: $hexString")
        //开始解析数据
        try {
            HuiDongDataHelper.parseData(hexString)

        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
        /*
        val services =  gatt.services
        services.forEach { it ->
            val uuid =  it.uuid
            BleLog.e("===服务=0=uuid: $uuid")

            val characteristics =  it.characteristics
            characteristics.forEachIndexed { index, bluetoothGattCharacteristic ->
                BleLog.e("==================特征=0=uuid: ${bluetoothGattCharacteristic.uuid}")
            }
        }
        */

        /*
        handler.postDelayed({
            val a = HuiDongCmdHelper.setMachineParamsThreshold(2,50 , 60)

            val success = BleCharacteristicHelper.writeCommandToCharacteristic(gatt,
                    UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"),
                    UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"), BleUtils.hexTextToBytes(a))

            BleLog.e("======写命令=setMachineData======success: $success")

        } , delayMillis)

        handler.postDelayed({
            val a = HuiDongCmdHelper.setMachineParams(25 , 15,25)

            val success = BleCharacteristicHelper.writeCommandToCharacteristic(gatt,
                    UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"),
                    UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"), BleUtils.hexTextToBytes(a))

            BleLog.e("======写命令=setMachineParams======success: $success")

        } , delayMillis*2)

        handler.postDelayed({
            val a = HuiDongCmdHelper.startAdjust()

            val success = BleCharacteristicHelper.writeCommandToCharacteristic(gatt,
                    UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"),
                    UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"), BleUtils.hexTextToBytes(a))

            BleLog.e("======写命令=startAdjust======success: $success")

        } , delayMillis*3)

        handler.postDelayed({
            val a = HuiDongCmdHelper.setBroadcast(1)

            val success = BleCharacteristicHelper.writeCommandToCharacteristic(gatt,
                    UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"),
                    UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"), BleUtils.hexTextToBytes(a))

            BleLog.e("======写命令=setBroadcast======success: $success")

        } , delayMillis*4)

        handler.postDelayed({
            val a = HuiDongCmdHelper.start()

            val success = BleCharacteristicHelper.writeCommandToCharacteristic(gatt,
                    UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"),
                    UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"), BleUtils.hexTextToBytes(a))

            BleLog.e("======写命令=startX======success: $success")

        } , delayMillis*5)

        handler.postDelayed({
            val success = BleCharacteristicHelper.notifyCharacteristic(gatt,
                    UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"),
                    UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e"))

            BleLog.e("======写命令==notifyCharacteristic=====success: $success")

        } , delayMillis*6)

        */

    }
}