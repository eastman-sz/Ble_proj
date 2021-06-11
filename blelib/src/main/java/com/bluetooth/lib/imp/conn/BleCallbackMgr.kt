package com.bluetooth.lib.imp.conn

import com.bluetooth.lib.imp.gattcallback.BleGattServiceListener
import com.bluetooth.lib.imp.gattcallback.OnBleDataReceiveListener
import kotlin.collections.ArrayList

object BleCallbackMgr {

    val stateListeners = ArrayList<OnBleConnectionStateChangeListener>()

    val gattServiceListeners = ArrayList<BleGattServiceListener>()

    val dataReceiveListeners = ArrayList<OnBleDataReceiveListener>()

    fun registerStateListener(listener: OnBleConnectionStateChangeListener) : BleCallbackMgr{
        if (stateListeners.contains(listener)){
            return this
        }
        stateListeners.add(listener)
        return this
    }

    fun unRegisterStateListener(listener: OnBleConnectionStateChangeListener) : BleCallbackMgr{
        stateListeners.remove(listener)
        return this
    }

    fun registerGattServiceListener(listener: BleGattServiceListener) : BleCallbackMgr{
        if (gattServiceListeners.contains(listener)){
            return this
        }
        gattServiceListeners.add(listener)
        return this
    }

    fun unRegisterGattServiceListener(listener: BleGattServiceListener) : BleCallbackMgr{
        gattServiceListeners.remove(listener)
        return this
    }

    fun registerBleDataReceiveListener(listener : OnBleDataReceiveListener) : BleCallbackMgr{
        if (dataReceiveListeners.contains(listener)){
            return this
        }
        dataReceiveListeners.add(listener)
        return this
    }

    fun unRegisterBleDataReceiveListener(listener : OnBleDataReceiveListener) : BleCallbackMgr{
        dataReceiveListeners.remove(listener)
        return this
    }


    fun unRegisterAll(){
        stateListeners.clear()
    }



}