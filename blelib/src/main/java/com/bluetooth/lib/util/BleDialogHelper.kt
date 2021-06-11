package com.bluetooth.lib.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.bluetooth.lib.R

class BleDialogHelper {

    companion object {

        fun showGpsTipDialog(context: Context , onBleSdkItemClickListener : OnBleSdkItemClickListener?){
            val style = context.resources.getIdentifier("Base.V21.Theme.AppCompat.Light.Dialog" , "style" , context.packageName)
            val builder = AlertDialog.Builder(context , style)
            builder.setTitle("温馨提示")
            builder.setMessage("Android高版本手机需要开启GPS才能正常使用蓝牙功能，是否开启")
            builder.setPositiveButton("开启" , object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    onBleSdkItemClickListener?.onClick(1)
                }
            })
            builder.setNegativeButton("取消" , object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    onBleSdkItemClickListener?.onClick(0)
                }
            })
            builder.setCancelable(false)

            builder.show()

        }

    }

}