package com.bluetooth.lib.imp.conn

class BleCallbackInvoke {

    companion object {

        fun onBleStateChange(address : String , state : Int){
            val size = BleCallbackMgr.stateListeners.size
            for (i in 0 until size){
                BleCallbackMgr.stateListeners[i].onStateChange(address , state)
            }
        }


        fun onBleDataReceived(dataType : Int , address: String , data : String){
            BleCallbackMgr.dataReceiveListeners.forEach {
                it.onDataReceive(dataType , address , data)
            }
        }

    }

}