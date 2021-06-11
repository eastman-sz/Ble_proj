package com.bluetooth.lib.imp.search

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.bluetooth.lib.util.*

/**
 * 蓝牙权限相关操作。
 * @author E
 */
class BlePermissionActivity : Activity() {

    private val LOCATION_PERMISSION_CODE = 555

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkBlePermission()
    }

    private fun checkBlePermission(){
        val hasBleFeatures = BleStatusHelper.hasBleFeatures(this)
        if (!hasBleFeatures){
            finish()

            BlePermissionConfig.onBlePermissionRequestResultListener?.onDenied()
            return
        }
        val isBleOpened = BleStatusHelper.isBleOpened(this)
        if (isBleOpened){
            //6.0及以后需要检查权限
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                finish()
                BlePermissionConfig.onBlePermissionRequestResultListener?.onGranted()
                return
            }

            //如果蓝牙已打开，检查定位权限
                var hasValidLocationPermission = BlePermissionHelper.hasValidLocationPermission(this)
                if (hasValidLocationPermission){
                    //如果已有定位权限，判断GPS有没有开启（高版本有机，没有开启搜索不到蓝牙）
                    if (BleSdkContext.needCheckLocationPermission){
                        checkGpsIsOpened()
                    }else{
                        finish()
                        BlePermissionConfig.onBlePermissionRequestResultListener?.onGranted()
                    }
                }else{
                    //请求定位权限
                    BlePermissionHelper.requestLocationPermission(this , LOCATION_PERMISSION_CODE)
                }
        }else{
            //打开蓝牙
            BleStatusHelper.openBle(this)
        }
    }


    //检查GPS是否开启
    //如果已有定位权限，判断GPS有没有开启（高版本有机，没有开启搜索不到蓝牙）
    private fun checkGpsIsOpened(){
        val isOPen = BleSdkGpsHelper.isOPen(this)
        if (isOPen){
            finish()
            BlePermissionConfig.onBlePermissionRequestResultListener?.onGranted()
            return
        }
        //GPS开启提示
        BleDialogHelper.showGpsTipDialog(this , object : OnBleSdkItemClickListener{
            override fun onClick(item: Int) {
                if (0 == item){
                    finish()
                    BlePermissionConfig.onBlePermissionRequestResultListener?.onDenied()
                    return
                }else if (1 == item){
                    BleSdkGpsHelper.openGPS(this@BlePermissionActivity)
                }
            }
        })
    }

    override fun onRestart() {
        BleLog.e("GPS是否开启_onRestart : ${false}")
        super.onRestart()
        //判断GPS是否开启
        val isOPen = BleSdkGpsHelper.isOPen(this)

        BleLog.e("GPS是否开启: $isOPen")

        if (isOPen){
            finish()
            BlePermissionConfig.onBlePermissionRequestResultListener?.onGranted()
            return
        }
        finish()
        BlePermissionConfig.onBlePermissionRequestResultListener?.onDenied()
        return
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            BleStatusHelper.REQUEST_ENABLE_BT ->{
                val isBleOpened = BleStatusHelper.isBleOpened(this)
                if (isBleOpened){
                    //6.0及以后需要检查权限
                    if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                        finish()
                        BlePermissionConfig.onBlePermissionRequestResultListener?.onGranted()
                        return
                    }

                    //如果蓝牙已打开，检查定位权限
                    var hasValidLocationPermission = BlePermissionHelper.hasValidLocationPermission(this)
                    if (hasValidLocationPermission){

                        //如果已有定位权限，判断GPS有没有开启（高版本有机，没有开启搜索不到蓝牙）
                        if (BleSdkContext.needCheckLocationPermission){
                            checkGpsIsOpened()
                        }else{
                            finish()
                            BlePermissionConfig.onBlePermissionRequestResultListener?.onGranted()
                        }

                    }else{
                        //请求定位权限
                        BlePermissionHelper.requestLocationPermission(this , LOCATION_PERMISSION_CODE)
                    }
                }else{
                    //蓝牙未打开
                    finish()
                    BlePermissionConfig.onBlePermissionRequestResultListener?.onDenied()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
        when(requestCode){
            LOCATION_PERMISSION_CODE ->{
                var hasValidLocationPermission = BlePermissionHelper.hasValidLocationPermission(this)
                if (hasValidLocationPermission){

                    //如果已有定位权限，判断GPS有没有开启（高版本有机，没有开启搜索不到蓝牙）
                    if (BleSdkContext.needCheckLocationPermission){
                        checkGpsIsOpened()
                    }else{
                        finish()
                        BlePermissionConfig.onBlePermissionRequestResultListener?.onGranted()
                    }

                }else{
                    //未允许定位权限
                    finish()
                    BlePermissionConfig.onBlePermissionRequestResultListener?.onDenied()
                }
            }
        }
    }

}
