package com.bluetooth.lib.imp.gattcallback

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.os.Handler
import android.os.Looper
import com.bluetooth.lib.broadcast.SdkBleBroadcastHelper
import com.bluetooth.lib.imp.conn.BleCallbackMgr
import com.bluetooth.lib.imp.conn.BleGattRecorder
import com.bluetooth.lib.util.BleSdkContext
/**
 * Call back invoked when gatt connect successful
 * @author E
 */
class BluetoothGattCallbackHelper {

    companion object {

        private val commonBleGattServiceListener = CommonBleGattServiceListener()

        fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic){
            if (BleSdkContext.needUseCommonBleGattService){
                commonBleGattServiceListener.onCharacteristicChanged(gatt , characteristic)
            }

            BleCallbackMgr.gattServiceListeners.forEach {
                it.onCharacteristicChanged(gatt , characteristic)
            }
        }

        fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int){
            if (BleSdkContext.needUseCommonBleGattService){
                commonBleGattServiceListener.onCharacteristicRead(gatt , characteristic , status)
            }

            BleCallbackMgr.gattServiceListeners.forEach {
                it.onCharacteristicRead(gatt , characteristic , status)
            }
        }

        fun onCharacteristicWrite(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int){
            if (BleSdkContext.needUseCommonBleGattService){
                commonBleGattServiceListener.onCharacteristicWrite(gatt , characteristic , status)
            }

            BleCallbackMgr.gattServiceListeners.forEach {
                it.onCharacteristicWrite(gatt , characteristic , status)
            }
        }

        fun onServicesDiscovered(gatt: BluetoothGatt, status: Int){
            //记录
            BleGattRecorder.instance.add(gatt)

            //It's takes 5 seconds
            if (BleSdkContext.needUseCommonBleGattService){
                commonBleGattServiceListener.onServicesDiscovered(gatt , status)
            }

            Handler(Looper.getMainLooper()).postDelayed({
                BleCallbackMgr.gattServiceListeners.forEach {
                    it.onServicesDiscovered(gatt , status)
                }
            } ,  if (BleSdkContext.needUseCommonBleGattService) 5000 else 500)

            //send broadcast
            SdkBleBroadcastHelper.onBleSdkServiceDiscovered(gatt.device.address , gatt.device.name)
        }
    }

}