package com.bluetooth.demo

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.bluetooth.lib.imp.conn.BleState
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder

class BluetoothAdapter : IBaseAdapter<BluetoothInfo> {

    var onAdapterClickListener : OnAdapterClickListener ?= null

    constructor(context: Context , list: List<BluetoothInfo>) : super(context , list , R.layout.bluetooth_adapter){
    }

    override fun getConvertView(convertView: View, list: List<BluetoothInfo>, position: Int) {
        val contentLayout = ViewHolder.getView<LinearLayout>(convertView , R.id.contentLayout)
        val nameTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.nameTextView)
        val stateTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.stateTextView)
        val infoTextView = ViewHolder.getView<CustomFontTextView>(convertView , R.id.infoTextView)

        val bluetoothInfo = list[position]
        val bluetoothDevice = bluetoothInfo.bluetoothDevice
        val state = bluetoothInfo.state

        nameTextView.text = bluetoothDevice?.name

        when(state){
            BleState.STATE_CONNECTING.state -> stateTextView.text = "connecting..."
            BleState.STATE_DISCONNECTED.state -> stateTextView.text = "disconnected"
            BleState.STATE_DISCONNECTING.state -> stateTextView.text = "disconnecting..."
            BleState.STATE_CONNECTED.state -> stateTextView.text = "connected"
        }

        contentLayout.setOnClickListener {
            onAdapterClickListener?.onItemClick(bluetoothInfo)
        }

        infoTextView.setOnClickListener {
            onAdapterClickListener?.onInfoClick(bluetoothInfo)
        }

    }
}