package com.bluetooth.lib.util

import android.util.Log

class BleLog {

    companion object {

        private const val tag = "bleLibLog"

        fun e(msg : String){
            Log.e(tag , msg)
        }

        fun d(msg : String){
            Log.d(tag , msg)
        }


    }

}