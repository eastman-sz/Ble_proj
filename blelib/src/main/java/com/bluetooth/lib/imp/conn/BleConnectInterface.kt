package com.bluetooth.lib.imp.conn

import android.bluetooth.*
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.bluetooth.lib.broadcast.SdkBleBroadcastHelper
import com.bluetooth.lib.imp.bean.SDKBleDbHelper
import com.bluetooth.lib.imp.gattcallback.BluetoothGattCallbackHelper
import com.bluetooth.lib.imp.search.OnBlePermissionRequestResultListener
import com.bluetooth.lib.util.BleLog
import com.bluetooth.lib.util.BleSdkPermissionHelper

abstract class BleConnectInterface {

    protected var context : Context ?= null
    private var address : String ?= null

    private var bluetoothGatt : BluetoothGatt ?= null

    constructor(context: Context , address : String){
        this.context = context
        this.address = address
    }

    fun startConnect(){
        //check ble permission
        BleSdkPermissionHelper.onBlePermissionRequest(context , object : OnBlePermissionRequestResultListener {
            override fun onDenied() {
            }
            override fun onGranted() {
                readyStartConnect()
            }
        })
    }

    private fun readyStartConnect(){
        val bluetoothAdapter = getBluetoothAdapter()
        val bluetoothDevice = bluetoothAdapter.getRemoteDevice(address)
        if (null == bluetoothGatt){
            bluetoothGatt = bluetoothDevice.connectGatt(context , false , bluetoothGattCallback)
        }else{
            bluetoothGatt?.connect()
        }

        refreshDeviceCache(bluetoothGatt)

        BleLog.e("状态变化: 正在连接....   ${bluetoothDevice?.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:""}")

        //save db
        SDKBleDbHelper.save(address!! , bluetoothDevice.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:"")
        //更新状态
        SDKBleDbHelper.saveState(address!! , BleState.STATE_CONNECTING.state)
        //回调
        BleCallbackInvoke.onBleStateChange(address!! , BleState.STATE_CONNECTING.state)

        //发送状态广播
        SdkBleBroadcastHelper.onSdkBleStateChange(address!! , bluetoothDevice.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:"" , BleState.STATE_CONNECTING.state)
    }


    fun disConnect(){
        bluetoothGatt?.let {
            it.disconnect()

            BleLog.e("状态变化: 正在断开....   ${it.device?.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:""}")
//            it.close()
            bluetoothGatt = null

            //更新状态
            SDKBleDbHelper.saveState(address!! , BleState.STATE_DISCONNECTED.state)
            //回调
            BleCallbackInvoke.onBleStateChange(address!! , BleState.STATE_DISCONNECTED.state)

            //发送状态广播
            SdkBleBroadcastHelper.onSdkBleStateChange(address!! , it.device?.name?: SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:"" , BleState.STATE_DISCONNECTED.state)
        }
        address?.let {
            //释放连接器
            BleConnectorMgr.clearBleConnector(it)
        }
    }


    abstract fun getBluetoothAdapter() : BluetoothAdapter

    private val bluetoothGattCallback = object : BluetoothGattCallback(){
        override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
            BluetoothGattCallbackHelper.onCharacteristicChanged(gatt , characteristic)
        }

        override fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            BluetoothGattCallbackHelper.onCharacteristicRead(gatt , characteristic , status)
        }

        override fun onCharacteristicWrite(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            BluetoothGattCallbackHelper.onCharacteristicWrite(gatt , characteristic , status)
        }

        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            when(status){
                BluetoothGatt.GATT_SUCCESS ->{
                    when(newState){
                        BluetoothProfile.STATE_CONNECTED ->{
                            BleLog.e("状态变化: 连接成功....   ${gatt.device.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:""}")

                            handler.post {
                                //更新状态
                                SDKBleDbHelper.saveState(gatt.device.address , BleState.STATE_CONNECTED.state)
                                //回调
                                BleCallbackInvoke.onBleStateChange(gatt.device.address , BleState.STATE_CONNECTED.state)

                                //发送状态广播
                                SdkBleBroadcastHelper.onSdkBleStateChange(gatt.device.address , gatt.device.name, BleState.STATE_CONNECTED.state)
                            }

                            handler.postDelayed({
                                //检测服务
                                gatt.discoverServices()
                            } , 500)
                        }

                        BluetoothProfile.STATE_DISCONNECTED ->{
                            BleLog.e("状态变化: 断开成功....   ${gatt.device.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:""}")

                            handler.post {
                                //释放连接器
                                BleConnectorMgr.clearBleConnector(gatt.device.address)
                                //release resource
                                gatt?.close()

                                //更新状态
                                SDKBleDbHelper.saveState(gatt.device.address , BleState.STATE_DISCONNECTED.state)
                                //回调
                                BleCallbackInvoke.onBleStateChange(gatt.device.address , BleState.STATE_DISCONNECTED.state)

                                //发送状态广播
                                SdkBleBroadcastHelper.onSdkBleStateChange(gatt.device.address , gatt.device.name , BleState.STATE_DISCONNECTED.state)
                            }
                        }
                    }
                }

                else ->{
                    //异常断开，准备重连....
                    try {
                        refreshDeviceCache(gatt)
                        gatt.disconnect()
                        bluetoothGatt?.disconnect()

                        handler.postDelayed({
                            gatt?.close()
                            bluetoothGatt?.close()
                            bluetoothGatt = null

                        } , 400)

                    }catch (e : Exception){
                        e.printStackTrace()
                    }

                    handler.postDelayed({
                        startConnect()

                        BleLog.e("异常断开，准备重连....   ${gatt?.device?.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:""}")

                        //更新状态
                        SDKBleDbHelper.saveState(gatt.device.address , BleState.STATE_CONNECTING.state)
                        //回调
                        BleCallbackInvoke.onBleStateChange(gatt.device.address , BleState.STATE_CONNECTING.state)

                        //发送状态广播
                        SdkBleBroadcastHelper.onSdkBleStateChange(gatt.device.address , gatt?.device?.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:"" , BleState.STATE_CONNECTING.state)

                    } , 1000)
                }
            }

            BleLog.e("状态变化: status: $status  newState: $newState   ${gatt.device.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:""}")

        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            BleLog.e("发现服务: $status  ${gatt.device.name?:SDKBleDbHelper.getSDKBleDevice(address!!)?.name?:""}")
            BluetoothGattCallbackHelper.onServicesDiscovered(gatt , status)
        }
    }

    private val handler = Handler(Looper.getMainLooper()){
        true
    }

    //Fresh exist BluetoothGatt
    private fun refreshDeviceCache(gatt: BluetoothGatt?): Boolean {
        try {
            if (null == gatt) {
                return false
            }
            val localMethod = gatt.javaClass.getMethod("refresh", *arrayOfNulls(0))
            if (localMethod != null) {
                return localMethod.invoke(gatt, *arrayOfNulls(0)) as Boolean
            }
        } catch (localException: Exception) {
            BleLog.e("An exception occured while refreshing device")
        }
        return false
    }

    fun getBluetoothGatt() : BluetoothGatt? {
        return bluetoothGatt
    }

}