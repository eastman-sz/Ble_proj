package com.bluetooth.lib.imp.gattcallback

interface OnBleDataReceiveListener {
    /**
     *  数据回调
     *  @dataType 数据类型
     *  @address 设置地址
     *  @data 真实数据,json格式
     */
    fun onDataReceive(dataType : Int , address : String , data : String)

}