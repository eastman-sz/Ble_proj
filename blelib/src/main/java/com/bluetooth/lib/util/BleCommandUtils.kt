package com.bluetooth.lib.util

import android.text.TextUtils

class BleCommandUtils {

    companion object {

        private val mHexStr = "0123456789ABCDEF"

        /**
         * 将16进制的命令转化为数组。
         * val value = byteArrayOf(0xFF.toByte(), 0xB9.toByte(), 0x0D.toByte(), 0x90.toByte())
         * @param hexCommand
         */
        fun hexToByteArray(hexCommand : String) : ByteArray{
            val empty = hexCommand.isNullOrEmpty()
            if (empty){
                return ByteArray(0)
            }
            val length = hexCommand.length/2
            val byteArray = ByteArray(length)
            for (i in 0 until length){

                val result = hexCommand.substring(i*2, i*2+2)
//                ILog.e("命令分解::      $result")

                val intOx = result.toInt(16)

                byteArray[i] = intOx.toByte()
            }
            return byteArray
        }


        /**
         * 生成校验码。取两位，不足两位，前面补0，大于两位，取后两位。
         * @param hexStr 16进制数据
         * @return 校验码
         */
        fun calChecksum(hexStr: String): String {
            if (TextUtils.isEmpty(hexStr)) {
                return "00"
            }
            val length = hexStr.length
            val num = length / 2
            var index = 0
            var total = 0
            for (i in 0 until num) {
                val hex = hexStr.substring(index, index + 2)
                total += BleUtils.hex2Integral(hex)
                index += 2
            }
            /**
             * 用256求余最大是255，即16进制的FF
             */
            val mod = total % 256
            var hex = Integer.toHexString(mod)
            val len = hex.length
            if (len < 2) {
                hex = "0$hex"
            }
            return hex
        }

        /**
         * 十进制转化为十六进制
         * @param integral 十进制
         * @return 十六进制
         */
        fun integral2Hex(integral: Int): String {
            var result = Integer.toHexString(integral)
            val mod = result.length % 2
            result = if (0 == mod) result else "0$result"
            return result
        }

        /**
         * 十六进制转换为十进制。
         * @param hexStr 十六进制
         * @return 十进制
         */
        fun hex2Integral(hexStr: String): Int {
            return Integer.parseInt(hexStr, 16)
        }

        fun hexToBytes(hex: CharArray): ByteArray {
            val length = hex.size / 2
            val raw = ByteArray(length)
            for (i in 0 until length) {
                val high = Character.digit(hex[i * 2], 16)
                val low = Character.digit(hex[i * 2 + 1], 16)
                var value = high shl 4 or low
                if (value > 127)
                    value -= 256
                raw[i] = value.toByte()
            }
            return raw
        }

        /**
         * 16进制字符串转化为byte数组
         * @param hexText
         * @return
         */
        fun hexTextToBytes(hexText: String): ByteArray {
            return hexToBytes(hexText.toCharArray())
        }

        /**
         * 十六进制转换为十进制。
         * @param hexStr 十六进制
         * @return 十进制
         */
        fun hex2Float(hexStr: String): Float {
            return java.lang.Float.intBitsToFloat(Integer.valueOf(hexStr, 16))
        }

        /**
         * 十进制转化为二进制
         * @param i 十进制
         * @return 二进制
         */
        fun toBinaryString(i: Int): String {
            return Integer.toBinaryString(i)
        }

        /**
         * 十六进制转换为二进制
         * @param hex 十六进制
         * @return 二进制
         */
        fun hex2BinaryString(hex: String): String {
            val i = hex2Integral(hex)
            return toBinaryString(i)
        }

        /**
         * 十六进制转换为二进制
         * @param hex 十六进制
         * @return 二进制
         */
        fun hex2BinaryString(hex: String, bitsNum: Int): String {
            var binaryString = hex2BinaryString(hex)
            val length = binaryString.length
            if (length >= bitsNum) {
                return binaryString
            }
            //不足位，前面补0
            val diff = bitsNum - length
            for (i in 0 until diff) {
                binaryString = "0$binaryString"
            }
            return binaryString
        }

    }

}