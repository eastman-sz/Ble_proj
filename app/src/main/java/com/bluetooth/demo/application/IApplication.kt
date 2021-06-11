package com.bluetooth.demo.application

import android.app.Application
import com.bluetooth.demo.ex.HuiDongBleGattServiceListener
import com.bluetooth.lib.imp.conn.BleCallbackMgr
import com.bluetooth.lib.invoke.BleSdkInitHelper
import com.bluetooth.lib.util.BleSdkContext

class IApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BleSdkContext.needCheckLocationPermission = true
        BleSdkInitHelper.initSDK(this)

        //初始化
        BleCallbackMgr.registerGattServiceListener(HuiDongBleGattServiceListener())
    }

}