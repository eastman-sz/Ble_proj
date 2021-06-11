package com.bluetooth.lib.invoke

import android.content.Context
import com.bluetooth.lib.imp.bean.SDKBleDbHelper
import com.bluetooth.lib.util.BleSdkContext
/**
 * Ble SDK init.
 * @author E
 */
class BleSdkInitHelper {

    companion object {

        fun initSDK(context: Context){
            BleSdkContext.context = context
            //clear db
            SDKBleDbHelper.reset()
        }

        //为设备区分用户
        fun initUserId(userId : Int){
            BleSdkContext.userId = userId
        }

    }

}