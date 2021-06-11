package com.bluetooth.demo.info

import android.os.Bundle
import com.bluetooth.demo.R
import com.bluetooth.lib.imp.gattcallback.BleDataType
import com.bluetooth.lib.imp.gattcallback.temp.BleDataTempUtils
import com.common.base.CommonTitleView
import kotlinx.android.synthetic.main.activity_ble_info.*

class BleInfoActivity : BasicBleActivity() {

    var name : String ?= null
    var mAddress : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ble_info)

        initActivitys()
    }

    override fun getIntentData() {
        name = intent.getStringExtra("name")
        mAddress = intent.getStringExtra("address")
    }

    override fun initTitle() {
        commonTitleView.setCenterTitleText(name)
        commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                finish()
            }
            override fun onRightBtnClick() {

            }
        })
    }

    override fun initViews() {
        bleInfoItemView0.setTitle("firmwareVersion")
        bleInfoItemView1.setTitle("manufactureName")
        bleInfoItemView2.setTitle("modelNumber")
        bleInfoItemView3.setTitle("serialNumber")
        bleInfoItemView4.setTitle("hardwareRevision")
        bleInfoItemView5.setTitle("softwareVersion")
        bleInfoItemView6.setTitle("SYSTEM_ID")
        bleInfoItemView7.setTitle("batteryLevel")
        bleInfoItemView8.setTitle("standardHr")

        mAddress?.let {
            val bleData = BleDataTempUtils.get(it)

            bleInfoItemView0.setValue(bleData.firmwareVersion)
            bleInfoItemView1.setValue(bleData.manufactureName)
            bleInfoItemView2.setValue(bleData.modelNumber)
            bleInfoItemView3.setValue(bleData.serialNumber)
            bleInfoItemView4.setValue(bleData.hardwareRevision)
            bleInfoItemView5.setValue(bleData.softwareVersion)
            bleInfoItemView6.setValue(bleData.systemId)
            bleInfoItemView7.setValue(bleData.batteryLevel.toString())
        }

    }


    override fun onBleDataReceived(dataType: Int, address: String, data: String) {
        mAddress?.let {
            when(address){
                mAddress ->{
                    runOnUiThread {
                        when(dataType){
                            BleDataType.STANDARD_HR.type -> bleInfoItemView8.setValue(data)

                            BleDataType.BATTERY_LEVEL.type -> bleInfoItemView7.setValue(data)

                            BleDataType.SYSTEM_ID.type -> bleInfoItemView6.setValue(data)

                            BleDataType.SOFTWARE_VERSION.type -> bleInfoItemView5.setValue(data)

                            BleDataType.HARDWARE_VERSION.type -> bleInfoItemView4.setValue(data)

                            BleDataType.SERIAL_NUMBER.type -> bleInfoItemView3.setValue(data)

                            BleDataType.MODEL_NUMBER.type -> bleInfoItemView2.setValue(data)

                            BleDataType.MANUFACTURE_NAME.type -> bleInfoItemView1.setValue(data)

                            BleDataType.FIRMWARE_VERSION.type -> bleInfoItemView0.setValue(data)
                        }
                    }
                }
            }
        }
    }



}
