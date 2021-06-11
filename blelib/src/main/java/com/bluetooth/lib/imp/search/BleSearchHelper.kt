package com.bluetooth.lib.imp.search

import android.os.Build
import com.bluetooth.lib.util.BleSdkContext

class BleSearchHelper {

    private var bleSearchInterface: BleSearchInterface? = null

    private constructor(){
        bleSearchInterface = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH) {
            BleSearchSDK20Imp(BleSdkContext.context)
        } else {
            BleSearchSDK23Imp(BleSdkContext.context)
        }
    }

    companion object{
        val instance : BleSearchHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { BleSearchHelper() }
    }

    fun startSearch() {
        bleSearchInterface?.startSearch()
    }

    fun stopSearch() {
        bleSearchInterface?.stopSearch()
    }


}