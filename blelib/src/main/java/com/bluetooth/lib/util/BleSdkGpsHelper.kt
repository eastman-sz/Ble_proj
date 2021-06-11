package com.bluetooth.lib.util

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
/**
 * Gps相关.
 * @author E
 */
class BleSdkGpsHelper {

    companion object {

        /**
         * 判断GPS是否打开。
         */
        fun isOPen(context: Context) : Boolean{
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
            val gps = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            return gps
        }

        /**
         * 帮用户打开系统的设置GPS页面.
         */
        fun openGPS(context: Context){
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            context.startActivity(intent)
        }

    }

}