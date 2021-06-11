package com.bluetooth.lib.recevier

import android.content.BroadcastReceiver
import android.content.IntentFilter
import com.bluetooth.lib.util.BleSdkContext

abstract class BaseBroadcastReceiver : BroadcastReceiver{

    private var hasRegistered = false

    constructor()

    fun register(){
        if (!hasRegistered){
            val filter = IntentFilter()
            val actionS = addActions()
            actionS.forEach {
                filter.addAction(it)
            }
            BleSdkContext.context?.registerReceiver(this , filter)
            hasRegistered = true
        }
    }

    fun unRegister(){
        if (hasRegistered){
            BleSdkContext.context?.unregisterReceiver(this)
            hasRegistered = false
        }
    }

    abstract fun addActions() : List<String>


}