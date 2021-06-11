package com.bluetooth.lib.recevier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.bluetooth.lib.util.BleSdkContext

class SDKBleDataBroadcastReceiveListener : BroadcastReceiver {

    var onSdkBleBroadcastReceiveListener : OnSdkBleBroadcastReceiveListener ?= null

    private var hasRegistered = false

    constructor(){}

    fun register(){
        if (hasRegistered){
            return
        }
        val filter = IntentFilter()
        filter.addAction("sdkBleDataAction")
        filter.addAction("sdkBleStateAction")
        BleSdkContext.context?.registerReceiver(this , filter)

        hasRegistered = true
    }

    fun unRegister(){
        if (hasRegistered){
            BleSdkContext.context?.unregisterReceiver(this)
            hasRegistered = false
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        when(action){
            "sdkBleDataAction" ->{
                //收到的蓝牙设备的数据
                val jsonData = intent.getStringExtra("sdkBleJsonData")

//                onSdkBleBroadcastReceiveListener?.onSdkBleDataReceived(jsonData)
            }

            "sdkBleStateAction" ->{
                val address = intent.getStringExtra("address")
                val state = intent.getIntExtra("state" , 0)

//                onSdkBleBroadcastReceiveListener?.onSdkBleStateChange(address , state)
            }


        }
    }
}