package com.bluetooth.demo.huidong

import com.bluetooth.demo.broadcast.HuiDongBroadcastHelper
import com.bluetooth.lib.util.BleCommandUtils
import com.bluetooth.lib.util.BleLog
import java.lang.Exception

class HuiDongDataHelper {

    companion object{

        private val huiDongData = HuiDongData()

        /**
         * 销子发送状态信息给显示设备
         * 690F02821400001F003F007E00ED017143
         * 工作状态，销子按照10Hz的频率发送状态数据给显示屏。 
         * 命令格式如下： 
         *  帧头     数据长度  设备类型    命令字    数据      校验码      帧尾
         *  0x69      0X0F     0x02       0x82    11字节     CS        0x43
         *  数据详解：
         *  数据1：设备编号
         *  数据2、3：次数
         *  数据4：配重块序号
         *  数据5、6：速度（厘米/秒）
         *  数据7、8：销子基准高度（单位：毫米，销子在开始运动前距离地面的高度）
         *  数据9、10：销子实时高度（单位：毫米，销子实时高度就是开始运动时，销子距离地面的高度）
         *  销子的行程=销子实时高度-销子基准高度）
         *  数据11：销子位置状态 0x01 - 销子位置OK；0x00 - 销子未插入正确位置
         */
        fun parseData(data : String){
            if (data.isEmpty()){
                return
            }
            if (data.length < 34){
                return
            }
            parseSportData(data)
        }

        private fun parseSportData(data : String){
            try {
                val sportData = data.substring(8 , 30)
                BleLog.e("===sportData===: $sportData")

                //数据1：设备编号
                val deviceNoHex = sportData.substring(0 , 2)
                //数据2、3：次数
                val countHex = sportData.substring(2 , 6)
                //数据4：配重块序号
                val seqHex = sportData.substring(6 , 8)
                //数据5、6：速度（厘米/秒）
                val speedHex = sportData.substring(8 , 12)
                //数据7、8：销子基准高度（单位：毫米，销子在开始运动前距离地面的高度）
                val baseHeightHex = sportData.substring(12 , 16)
                //数据9、10：销子实时高度（单位：毫米，销子实时高度就是开始运动时，销子距离地面的高度）
                val realHeightHex = sportData.substring(16 , 20)
                //数据11：销子位置状态 0x01 - 销子位置OK；0x00 - 销子未插入正确位置
                val statusHex = sportData.substring(20 , 22)

                BleLog.e("===sportData=deviceNoHex: $deviceNoHex   countHex: $countHex   seqHex: $seqHex  " +
                        " speedHex: $speedHex   baseHeightHex: $baseHeightHex   realHeightHex: $realHeightHex   statusHex: $statusHex")

                val deviceNo = BleCommandUtils.hex2Integral(deviceNoHex)
                val count = BleCommandUtils.hex2Integral(countHex)
                val seq = BleCommandUtils.hex2Integral(seqHex)
                val speed = BleCommandUtils.hex2Integral(speedHex)
                val baseHeight = BleCommandUtils.hex2Integral(baseHeightHex)
                val realHeight = BleCommandUtils.hex2Integral(realHeightHex)
                val status = statusHex == "01"

                BleLog.e("===sportData=设备编号: $deviceNo   次数: $count   配重块序号: $seq  " +
                        " 速度（厘米/秒）: $speed   销子基准高度: $baseHeight   销子实时高度: $realHeight   销子位置状态: $status")


                huiDongData.deviceNo = deviceNo
                huiDongData.count = count
                huiDongData.brickSeq = seq
                huiDongData.speed = speed
                huiDongData.bottomLineHeight = baseHeight
                huiDongData.readHeight = realHeight
                val distance = realHeight - baseHeight
                huiDongData.distance = if (distance < 0) 0 else distance
                huiDongData.status = status

                //Send
                HuiDongBroadcastHelper.onHuiDongDataReceive(huiDongData)
            }catch (e : Exception){
                e.printStackTrace()
            }

        }

    }

}