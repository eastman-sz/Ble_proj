package com.bluetooth.lib.db

import android.database.Cursor

class BleSdkCursorHelper {

    companion object {

        fun getString(cursor : Cursor , columnName : String) : String?{
            return cursor.getString(cursor.getColumnIndex(columnName))
        }

        fun getInt(cursor : Cursor , columnName : String) : Int{
            return cursor.getInt(cursor.getColumnIndex(columnName))
        }

        fun getLong(cursor : Cursor , columnName : String) : Long{
            return cursor.getLong(cursor.getColumnIndex(columnName))
        }

        fun getDouble(cursor : Cursor , columnName : String) : Double{
            return cursor.getDouble(cursor.getColumnIndex(columnName))
        }

        fun getFloat(cursor : Cursor , columnName : String) : Float{
            return cursor.getFloat(cursor.getColumnIndex(columnName))
        }

    }

}