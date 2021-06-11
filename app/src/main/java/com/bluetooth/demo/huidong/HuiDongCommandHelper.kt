package com.bluetooth.demo.huidong

import com.bluetooth.lib.imp.conn.BleCommandHelper
import com.bluetooth.lib.util.BleUtils
import java.util.*

class HuiDongCommandHelper {

    companion object{

        private val serviceUUID = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e")

        private val writeUUID = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e")

        private val notifyUUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e")

        fun start() : Boolean{
            val cmd = HuiDongCmdHelper.start()
            return BleCommandHelper.instance.write("BB:AA:14:71:B0:71" , serviceUUID , writeUUID , BleUtils.hexTextToBytes(cmd))
        }

        fun stop() : Boolean{
            val cmd = HuiDongCmdHelper.stop()
            return BleCommandHelper.instance.write("BB:AA:14:71:B0:71" , serviceUUID , writeUUID , BleUtils.hexTextToBytes(cmd))
        }

        fun setMachineParamsThreshold(actionNo : Int, upThreshold : Int , downThreshold : Int) : Boolean{
            val cmd = HuiDongCmdHelper.setMachineParamsThreshold(actionNo, upThreshold, downThreshold)
            return BleCommandHelper.instance.write("BB:AA:14:71:B0:71" , serviceUUID , writeUUID , BleUtils.hexTextToBytes(cmd))
        }

        fun setMachineParams(brickThickness : Int , brickNum : Int , bottomDistance : Int) : Boolean{
            val cmd = HuiDongCmdHelper.setMachineParams(brickThickness, brickNum, bottomDistance)
            return BleCommandHelper.instance.write("BB:AA:14:71:B0:71" , serviceUUID , writeUUID , BleUtils.hexTextToBytes(cmd))
        }

        fun disconnectBle() : Boolean{
            val cmd = HuiDongCmdHelper.disconnectBle()
            return BleCommandHelper.instance.write("BB:AA:14:71:B0:71" , serviceUUID , writeUUID , BleUtils.hexTextToBytes(cmd))
        }

        fun startAdjust() : Boolean{
            val cmd = HuiDongCmdHelper.startAdjust()
            return BleCommandHelper.instance.write("BB:AA:14:71:B0:71" , serviceUUID , writeUUID , BleUtils.hexTextToBytes(cmd))
        }

        fun setBleName(deviceNo : Int) : Boolean{
            val cmd = HuiDongCmdHelper.setBroadcast(deviceNo)
            return BleCommandHelper.instance.write("BB:AA:14:71:B0:71" , serviceUUID , writeUUID , BleUtils.hexTextToBytes(cmd))
        }

        fun notifyData() : Boolean{
            return BleCommandHelper.instance.notifyCharacteristic("BB:AA:14:71:B0:71" , serviceUUID , notifyUUID)
        }


    }


}