package com.bluetooth.demo

import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bluetooth.demo.broadcast.HuiDongBroadcastReceiver
import com.bluetooth.demo.broadcast.OnHuiDongBroadcastReceiverListener
import com.bluetooth.demo.huidong.HuiDongCmdHelper
import com.bluetooth.demo.huidong.HuiDongCommandHelper
import com.bluetooth.demo.huidong.HuiDongData
import com.bluetooth.demo.info.BasicBleActivity
import com.bluetooth.demo.info.BleInfoActivity
import com.bluetooth.lib.imp.conn.BleCallbackMgr
import com.bluetooth.lib.imp.conn.BleConnectHelper
import com.bluetooth.lib.imp.conn.BleState
import com.bluetooth.lib.imp.gattcallback.extra.Ld1BleGattServiceListener
import com.bluetooth.lib.imp.gattcallback.extra.Ld2BleGattServiceListener
import com.bluetooth.lib.imp.search.BleSearchHelper
import com.bluetooth.lib.util.BleLog
import com.common.base.CommonTitleView
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothActivity : BasicBleActivity() {

    private val list = ArrayList<BluetoothInfo>()
    private var adapter : BluetoothAdapter ?= null

    private val map = HashMap<String , Int>()

    private val huiDongBroadcastReceiver = HuiDongBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        initActivitys()

        BleSearchHelper.instance.startSearch()

    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText("bluetooth")
        commonTitleView.setRightBtnText("搜索")
        commonTitleView.setLeftBtnText("停止")
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onRightBtnClick() {
                BleSearchHelper.instance.startSearch()
            }

            override fun onLeftBtnClick() {
                BleSearchHelper.instance.stopSearch()
            }
        })
    }

    override fun initViews() {
        adapter = BluetoothAdapter(context , list)
        listView.adapter = adapter

        adapter?.onAdapterClickListener = object : OnAdapterClickListener{
            override fun onItemClick(bluetoothInfo: BluetoothInfo) {
                bluetoothInfo.bluetoothDevice?.let {
                    val state = bluetoothInfo.state

                    when(state){
                        BleState.STATE_DISCONNECTED.state ->{

                            BleSearchHelper.instance.stopSearch()
                            BleConnectHelper.startConnect(context , it.address)
                        }

                        else ->{
                            BleConnectHelper.disconnect(it.address)
                        }
                    }
                }
            }

            override fun onInfoClick(bluetoothInfo: BluetoothInfo) {
                startActivity(Intent(context , BleInfoActivity::class.java)
                        .putExtra("name" , bluetoothInfo.bluetoothDevice?.name)
                        .putExtra("address" , bluetoothInfo.bluetoothDevice?.address)

                )
            }
        }
    }

    override fun onSdkBleSearchResultGet(bluetoothDevice: BluetoothDevice, rssi: Int, scanRecord: ByteArray) {
        val address = bluetoothDevice.address

        if (address.equals("BB:AA:14:71:B0:71" , false)){
            BleLog.e("==找到设备====::: ${bluetoothDevice.name}")
        }

        if (map.containsKey(address)){
            return
        }
        map[address] = 1

        list.add(BluetoothInfo.makeBluetoothInfo(bluetoothDevice))

        adapter?.notifyDataSetChanged()
    }

    override fun onBleStateChanged(address: String, state: Int) {
        list.forEach {
            if (it.bluetoothDevice?.address == address){
                it.state = state

                adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun initListener() {
        super.initListener()
        huiDongBroadcastReceiver.register()
        huiDongBroadcastReceiver.onHuiDongBroadcastReceiverListener = object : OnHuiDongBroadcastReceiverListener(){
            override fun onHuiDongDataReceive(huiDongData: HuiDongData) {
                mHandler.post {
                    dataTextView.text = huiDongData.toString()
                }
            }
        }


        checkSumBtn.setOnClickListener {
            //checkSum
//            HuiDongCmdHelper.start()
//            HuiDongCmdHelper.setMachineParamsThreshold(2,296 , 88)
//            HuiDongCmdHelper.setMachineParams(25 , 15,25 )
//            HuiDongCmdHelper.setBroadcast()
//            HuiDongCmdHelper.parseData("690F02821400001F003F007E00ED017143")


        }

        startBtn.setOnClickListener {
            //开始
            HuiDongCommandHelper.start()
            HuiDongCommandHelper.notifyData()
        }
        stopBtn.setOnClickListener {
            //停止
            HuiDongCommandHelper.stop()

//            BleConnectHelper.startConnect(context , "BB:AA:14:71:B0:71")
        }
        renameBtn.setOnClickListener {
            //改名
            HuiDongCommandHelper.setBleName(2)
        }
    }

    private val mHandler = Handler(Looper.myLooper())

    override fun onDestroy() {
        huiDongBroadcastReceiver.unRegister()
        super.onDestroy()
    }

}
