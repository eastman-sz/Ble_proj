package com.bluetooth.demo.broadcast

import android.content.Intent
import com.bluetooth.demo.huidong.HuiDongData
import com.bluetooth.lib.util.BleSdkContext

class HuiDongBroadcastHelper {

    companion object{

        private val dataIntent = Intent("onHuiDongDataReceive")

        fun onHuiDongDataReceive(huiDongData: HuiDongData){
            dataIntent.putExtra("huiDongDataJson" , huiDongData.toString())
            BleSdkContext.context?.sendBroadcast(dataIntent)
        }


    }


}