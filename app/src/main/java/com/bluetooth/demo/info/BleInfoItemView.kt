package com.bluetooth.demo.info

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bluetooth.demo.R
import com.common.base.BaseRelativeLayout
import kotlinx.android.synthetic.main.ble_info_item_view.view.*

class BleInfoItemView : BaseRelativeLayout {

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context , attrs : AttributeSet) : super(context , attrs){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.ble_info_item_view , this)
    }

    fun setTitle(title : String){
        titleTextView.text = title
    }

    fun setValue(value : String?){
        valueTextView.text = value
    }

}