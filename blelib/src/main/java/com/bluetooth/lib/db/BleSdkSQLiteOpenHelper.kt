package com.bluetooth.lib.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bluetooth.lib.imp.bean.SDKBleDbHelper
/**
 * SQLiteOpenHelper for ble devices.
 * @author E
 */
class BleSdkSQLiteOpenHelper : SQLiteOpenHelper {

    constructor(context: Context?) : super(context , "sdkbleNS" , null , 2)

    override fun onCreate(db: SQLiteDatabase) {
        SDKBleDbHelper.createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        SDKBleDbHelper.dropTable(db)

        onCreate(db)
    }

}