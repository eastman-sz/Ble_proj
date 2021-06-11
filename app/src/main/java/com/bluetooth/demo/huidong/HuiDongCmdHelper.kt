package com.bluetooth.demo.huidong

import com.bluetooth.lib.util.BleCommandUtils
import com.bluetooth.lib.util.BleLog
import com.bluetooth.lib.util.CommonBleUtils
import com.bluetooth.lib.util.HexStrUtil

class HuiDongCmdHelper {

    companion object{

        /**
         * 2.1 显示设备发送启动、停止信息给销子
         * 命令格式如下： 
         *   帧头  数据长度  设备类型  命令字 数据   校验码  帧尾
         *   0x69  0x05     0x01     0x81  1字节  CS      0x43
         *   数据详解：
         *   数据 1  屏幕状态   0x01- 开始（按下开始按键）
         *   0x02- 结束（按下结束按键）
         *   说明： 
         *   屏幕按下开始后，下发开始状态通知销子，屏幕组数次数清零；销子开始工作； 
         *   屏幕按下结束后，下发结束状态通知销子，屏幕显示本次训练累计的组数、次数、累
         *   计重量、卡路里消耗、累计时间；销子暂停工作。 
         *
         */
        fun start() : String{
            val cmd = "05018101"
            val checksum = BleCommandUtils.calChecksum(cmd)
            val wholeCmd = "69$cmd$checksum".plus("43")

            BleLog.e("=====开始命令=wholeCmd: $wholeCmd")

            return wholeCmd
        }

        /**
         * 结束.
         */
        fun stop() : String{
            val cmd = "05018102"
            val checksum = BleCommandUtils.calChecksum(cmd)
            val wholeCmd = "69$cmd$checksum".plus("43")

            BleLog.e("=====开始命令=wholeCmd: $wholeCmd")

            return wholeCmd
        }

        /**
         * 显示设备发送动作类型编号及销子上升沿阈值、下降沿阈值给销子
         *  命令格式如下： 
         *   帧头  数据长度  设备类型  命令字 数据  校验码  帧尾
         *   0x69  0x09  0x01  0x83  5 字节  CS  0x43
         *
         *  数据 1   动作类型编号 actionNo
         *  数据 2、3：销子上升沿阈值；数据 2 为高字节数据 3 为低字节，单位毫米，数据范围：20-500 upThreshold
         *  数据 4、5：销子下降沿阈值；数据 4 为高字节数据 5 为低字节，单位毫米，数据范围：20-500 downThreshold
         *   判断是否完成一次训练动作，要求上升时行程高度大于销子上升沿阈值，下降时行程高度小于销子下降沿阈值。
         */
        fun setMachineParamsThreshold(actionNo : Int, upThreshold : Int , downThreshold : Int) : String{
            //数据 1   动作类型编号
            val actionNoValue = if (actionNo < 1) 1 else if (actionNo > 32) 32 else actionNo
            val actionHex = BleCommandUtils.integral2Hex(actionNoValue)

            //数据 2、3：销子上升沿阈值
            val upThresholdValue = if (upThreshold < 20) 20 else if (upThreshold > 500) 500 else upThreshold
            val up = BleCommandUtils.integral2Hex(upThresholdValue)
            val upHex = buQi4(up)
            //数据 4、5：销子下降沿阈值
            val downThresholdValue = if (downThreshold < 20) 20 else if (downThreshold > 500) 500 else downThreshold
            val down = BleCommandUtils.integral2Hex(downThresholdValue)
            val downHex = buQi4(down)

            val cmd = "090183".plus(actionHex).plus(upHex).plus(downHex)

            val checksum = BleCommandUtils.calChecksum(cmd)

            BleLog.e("===设置命令==actionNo: $actionNo==actionHex: $actionHex==up: $up==upHex: $upHex==down: $down==downHex: $downHex==cmd: $cmd    checksum: $checksum")

            val wholeCmd = "69$cmd$checksum".plus("43")

            BleLog.e("===设置命令==wholeCmd: $wholeCmd")

            return wholeCmd
        }

        private fun buQi4(hex : String) : String{
            if (hex.isEmpty()){
                return "0000";
            }
            if (hex.length > 4){
                return hex.substring(0 , 4)
            }
            if (hex.length == 3){
                return "0$hex"
            }
            if (hex.length == 2){
                return "00$hex"
            }
            if (hex.length == 1){
                return "000$hex"
            }
            return hex
        }

        /**
         * 2.4 显示设备发送配重块厚度、配重块数量及最下面配重块距离地面的高度数值给销子
         * 命令格式如下： 
         *   帧头  数据长度  设备类型 命令字  数据   校验码  帧尾
         *   0x69  0X08     0x01    0x84   4 字节  CS    0x43
         *
         *  数据 1：配重块厚度：单位毫米，数据范围：15-60
         *  数据 2：配重块数量，数据范围：5-30
         *  数数据 3、4：最下配重块块距离地面面的距离：把销子插入入到最下面的的配重块中中，销子测
         *  量得出的的距离即认认为是最下配配重块距离离地面的距离离。单位毫毫米，数据范范围：20-500
         */
        fun setMachineParams(brickThickness : Int , brickNum : Int , bottomDistance : Int) : String{
            //数据 1：配重块厚度：单位毫米，数据范围：15-60
            val brickThicknessValue = if (brickThickness < 15) 15 else if (brickThickness > 60) 60 else brickThickness
            val brickThicknessHex = BleCommandUtils.integral2Hex(brickThicknessValue)
            //数据 2：配重块数量，数据范围：5-30
            val brickNumValue = if (brickNum < 5) 5 else if (brickNum > 30) 30 else brickNum
            val brickNumHex = BleCommandUtils.integral2Hex(brickNumValue)
            //数数据 3、4：最下配重块块距离地面面的距离：把把销子插入入到最下面的的配重块中中，销子测
            //量得出的的距离即认认为是最下配配重块距离离地面的距离离。单位毫毫米，数据范范围：20-500
            val bottomDistanceValue = if (bottomDistance < 20) 20 else if (bottomDistance > 500) 500 else bottomDistance
            val levelDistance = BleCommandUtils.integral2Hex(bottomDistanceValue)
            val levelDistanceHex = buQi4(levelDistance)

            val cmd = "080184$brickThicknessHex$brickNumHex$levelDistanceHex"

            val checksum = BleCommandUtils.calChecksum(cmd)

            BleLog.e("=====设置命令2.4==brickThickness: $brickThickness brickThicknessHex: $brickThicknessHex brickNum: $brickNum  brickNumHex: $brickNumHex levelDistance: $levelDistance levelDistanceHex: $levelDistanceHex checksum: $checksum  cmd: $cmd")

            val wholeCmd = "69$cmd$checksum".plus("43")

            BleLog.e("===设置命令2.4==wholeCmd: $wholeCmd")

            return wholeCmd
        }

        /**
         * 2.6 显示设备发送断开蓝牙连接命令给销子
         * 命令格式如下： 
         * 帧头   数据长度  设备类型  命令字  数据    校验码   帧尾
         * 0x69   0x05     0x01     0x89   1字节   CS       0x43
         * 数据详解：
         * 数据 1  断开连接   0x01- 断开
         * 说明： 
         * 屏幕下发断开蓝牙连接命令，销子接收到后，主动断开与当前设备的连接，进入广播
         * 状态。
         */
        fun disconnectBle() : String{
            val cmd = "05018901"
            val checksum = BleCommandUtils.calChecksum(cmd)

            val wholeCmd = "69$cmd$checksum".plus("43")

            BleLog.e("===设置命令2.6==wholeCmd: $wholeCmd")

            return wholeCmd
        }


        /**
         * 2.8 显示设备发送加速度传感器校正命令给销子
         * 命令格式如下： 
         *   帧头  数据长度  设备类型  命令字 数据  校验码  帧尾
         *   0x69  0x05     0x01     0x8a  1字节  CS    0x43
         *   数据详解：
         *   数据 1  开始校正   0x01
         *   说明： 
         *   销子水平插入到校正设备中或者力量训练器配重块中，下发加速度校正命令，销子接
         *   收到后，进行当前位置加速度校正。 
         */
        fun startAdjust() : String{
            val cmd = "05018a01"
            val checksum = BleCommandUtils.calChecksum(cmd)
            val wholeCmd = "69$cmd$checksum".plus("43")
            BleLog.e("===设置命令2.8==wholeCmd: $wholeCmd")
            return wholeCmd
        }

        /**
         * 2.9 显示设备设置广播内容给销子
         * 命令格式如下： 
         *   帧头  数据长度  设备类型  命令字 数据       校验码  帧尾
         *   0x69  0x10     0x01     0x8b  12 字节    CS     0x43
         *   数据详解：
         *   数据 1-数据 10 为文本，为厂家名称缩写，不到 10 字符的用空格代替；
         *   数据 11 为符号“_” ；
         *   数据 12 为数字，范围 1-255，代表厂家设备代号；
         *   示例说明： 
         *   “QDHDJKYXGS_105”，  其中105 为0x69 ，一个字节； 
         *   注：广播内容保存在flash 中，断电不能丢失。 
         *   销子出厂默认广播内容为：“QDHDJKYXGS_100” 
         */
        fun setBroadcast(deviceNo : Int) : String{
            //数据 1-数据 10 为文本，为厂家名称缩写，不到 10 字符的用空格代替；
            val name = HexStrUtil.str2HexStr("QDHDJKYXGS")
            BleLog.e("===设置命令2.9==name: $name   length: ${name.length}" )
            val deviceNoValue = if (deviceNo < 1) 1 else if (deviceNo > 255) 255 else deviceNo
            val deviceNum = BleCommandUtils.integral2Hex(deviceNoValue)
            BleLog.e("===设置命令2.9==deviceNum: $deviceNum" )
            val line = HexStrUtil.str2HexStr("_")
            BleLog.e("===设置命令2.9==line: $line" )

            val cmd = "10018b$name".plus(line).plus(deviceNum)

            val checksum = BleCommandUtils.calChecksum(cmd)

            val wholeCmd = "69$cmd$checksum".plus("43")
            BleLog.e("===设置命令2.9==wholeCmd: $wholeCmd")

            return wholeCmd
        }

    }



}