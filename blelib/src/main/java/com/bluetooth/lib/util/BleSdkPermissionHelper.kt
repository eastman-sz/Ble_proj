package com.bluetooth.lib.util

import android.content.Context
import android.content.Intent
import com.bluetooth.lib.imp.search.BlePermissionActivity
import com.bluetooth.lib.imp.search.BlePermissionConfig
import com.bluetooth.lib.imp.search.OnBlePermissionRequestResultListener

/**
 * Permission Request.
 * @author E
 */
class BleSdkPermissionHelper {

    companion object {
        /**
         * Request bluetooth permission and return request result.
         */
        fun onBlePermissionRequest(context: Context? , onBlePermissionRequestResultListener: OnBlePermissionRequestResultListener){
            BlePermissionConfig.onBlePermissionRequestResultListener = onBlePermissionRequestResultListener
            val intent = Intent(context , BlePermissionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(intent)
        }

    }

}