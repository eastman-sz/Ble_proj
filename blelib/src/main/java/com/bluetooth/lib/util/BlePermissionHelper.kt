package com.bluetooth.lib.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
/**
 * Permissions.
 * @author E
 */
class BlePermissionHelper {

    companion object {

        //是否需要检测后台定位权限，设置为true时，如果用户没有给予后台定位权限会弹窗提示
        private var needCheckBackLocation = false
        //如果设置了target > 28，需要增加这个权限，否则不会弹出"始终允许"这个选择框
        private const val BACK_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION"

        fun hasValidLocationPermission(activity: Context): Boolean {
/*            val permission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
            return permission == PackageManager.PERMISSION_GRANTED*/

            if (Build.VERSION.SDK_INT < 23 && activity.applicationInfo.targetSdkVersion < 23){
                return true
            }

            if (Build.VERSION.SDK_INT > 28
                    && activity.applicationContext.applicationInfo.targetSdkVersion > 28){

                        return hasPermission(activity , Manifest.permission.ACCESS_FINE_LOCATION)
                        && hasPermission(activity , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && hasPermission(activity , Manifest.permission.READ_EXTERNAL_STORAGE)
                        && hasPermission(activity , Manifest.permission.READ_PHONE_STATE)
                        && hasPermission(activity , Manifest.permission.ACCESS_COARSE_LOCATION)
                                && hasPermission(activity , BACK_LOCATION_PERMISSION)
            }else{
                return hasPermission(activity , Manifest.permission.ACCESS_FINE_LOCATION)
                        && hasPermission(activity , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && hasPermission(activity , Manifest.permission.READ_EXTERNAL_STORAGE)
                        && hasPermission(activity , Manifest.permission.READ_PHONE_STATE)
                        && hasPermission(activity , Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }

        fun requestLocationPermission(activity: Activity, requestCode: Int) {
/*            if (hasValidLocationPermission(activity)) {
                return
            }
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)*/

            val list = ArrayList<String>()
            list.addAll(getPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION))
            list.addAll(getPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            list.addAll(getPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE))
            list.addAll(getPermission(activity, Manifest.permission.READ_PHONE_STATE))
            list.addAll(getPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION))
            if (Build.VERSION.SDK_INT > 28
                    && activity.applicationContext.applicationInfo.targetSdkVersion > 28){
                list.addAll(getPermission(activity, BACK_LOCATION_PERMISSION))

                needCheckBackLocation = true
            }
            if (list.isEmpty()){
                return
            }
            val permissions = list.toTypedArray()
            ActivityCompat.requestPermissions(activity, permissions, requestCode)


        }

        private fun getPermission(context: Context, permission: String) : List<String>{
            val list = ArrayList<String>()
            if (hasPermission(context, permission)){
                return list
            }
            list.add(permission)
            return list
        }

        //是否有某种权限
        private fun hasPermission(context: Context, permission: String) : Boolean{
            val permission = ContextCompat.checkSelfPermission(context, permission)
            return permission == PackageManager.PERMISSION_GRANTED
        }
    }

}