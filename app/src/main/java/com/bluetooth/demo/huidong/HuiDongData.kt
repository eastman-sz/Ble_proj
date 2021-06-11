package com.bluetooth.demo.huidong

import com.bluetooth.lib.util.BleJsonTools
/**
 * 慧动设备返回的数据。
 */
class HuiDongData {

    var deviceNo = 0 //设备编号
    var count = 0 //次数
    var brickSeq = 0 //配重块序号
    var speed = 0 //速度（厘米/秒）
    var bottomLineHeight = 0 //销子基准高度（单位：毫米，销子在开始运动前距离地面的高度）
    var readHeight = 0 //销子实时高度（单位：毫米，销子实时高度就是开始运动时，销子距离地面的高度）
    var distance = 0 //销子的行程=销子实时高度-销子基准高度）
    var status = false //销子位置状态 0x01 - 销子位置OK；0x00 - 销子未插入正确位置

    override fun toString(): String {
        return   BleJsonTools.JSONBean()
                .put("deviceNo" , deviceNo)
                .put("count" , count)
                .put("brickSeq" , brickSeq)
                .put("speed" , speed)
                .put("bottomLineHeight" , bottomLineHeight)
                .put("readHeight" , readHeight)
                .put("distance" , distance)
                .put("status" , status)
                .buildJson()
    }

}