package com.bluetooth.demo.broadcast

import android.content.Context
import android.content.Intent
import com.bluetooth.demo.huidong.HuiDongData
import com.bluetooth.lib.recevier.BaseBroadcastReceiver
import org.json.JSONObject

class HuiDongBroadcastReceiver : BaseBroadcastReceiver() {

    var onHuiDongBroadcastReceiverListener : OnHuiDongBroadcastReceiverListener ?= null

    override fun addActions(): List<String> {
        val list = ArrayList<String>()
        list.add("onHuiDongDataReceive")
        return list
    }

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            "onHuiDongDataReceive" ->{
                val huiDongDataJson = intent.getStringExtra("huiDongDataJson")
                val jSONObject = JSONObject(huiDongDataJson)

                val huiDongData = HuiDongData()
                huiDongData.deviceNo = jSONObject.getInt("deviceNo")
                huiDongData.count = jSONObject.getInt("count")
                huiDongData.brickSeq = jSONObject.getInt("brickSeq")
                huiDongData.speed = jSONObject.getInt("speed")
                huiDongData.bottomLineHeight = jSONObject.getInt("bottomLineHeight")
                huiDongData.readHeight = jSONObject.getInt("readHeight")
                huiDongData.distance = jSONObject.getInt("distance")
                huiDongData.status = jSONObject.getBoolean("status")

                onHuiDongBroadcastReceiverListener?.onHuiDongDataReceive(huiDongData)
            }
        }
    }


}