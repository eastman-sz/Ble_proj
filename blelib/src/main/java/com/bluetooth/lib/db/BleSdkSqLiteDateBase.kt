package com.bluetooth.lib.db

import com.bluetooth.lib.util.BleSdkContext

object BleSdkSqLiteDateBase {

    val sqLiteDataBase = BleSdkSQLiteOpenHelper(BleSdkContext.context).writableDatabase

}