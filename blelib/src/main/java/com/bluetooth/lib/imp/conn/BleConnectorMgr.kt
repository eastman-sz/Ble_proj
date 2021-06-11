package com.bluetooth.lib.imp.conn

import android.content.Context
/**
 * Manage all bluetooth connect session. one session only obtained by one connect.
 * @author E
 */
object BleConnectorMgr {

    private val map = HashMap<String , BleConnector>()

    fun getBleConnector(address : String) : BleConnector?{
        synchronized(map){
            if (map.containsKey(address)){
                return map[address]
            }
            return null
        }
    }

    fun getBleConnectorWithDefault(context : Context , address : String) : BleConnector?{
        synchronized(map){
            if (!map.containsKey(address)){
                map[address] = BleConnector(context , address)
            }
            return map[address]
        }
    }

    fun clearBleConnector(address : String){
        synchronized(map){
            if (map.containsKey(address)){
                map.remove(address)
            }
        }

        //清空相关
        BleGattRecorder.instance.clear(address)
    }

    //清除所有的实例并断开
    fun clearAllBleConnector(){
        synchronized(map){
            map.forEach {
                it?.value?.disConnect()
            }
            map.clear()
        }

        //清空所有记录
        BleGattRecorder.instance.clear()
    }

}