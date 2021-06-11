package com.bluetooth.lib.util

import android.content.Context
/**
 * Obtain context instance by sdk init.
 * @author E
 */
object BleSdkContext {

    var context : Context ?= null;

    var userId : Int = 0

    var needCheckLocationPermission = false

    //是否需要使用 commonBleGattServiceListener
    var needUseCommonBleGattService = false
}