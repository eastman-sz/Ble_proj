package com.bluetooth.lib.imp.bean

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.bluetooth.lib.db.BleSdkCursorHelper
import com.bluetooth.lib.db.BleSdkDbTableHelper
import com.bluetooth.lib.db.BleSdkSqLiteDateBase
import com.bluetooth.lib.imp.conn.BleState
import com.bluetooth.lib.util.BleLog
import com.bluetooth.lib.util.BleSdkContext
/**
 * About ble device info.
 * @author E
 */
class SDKBleDbHelper {

    companion object {

        fun save(address : String , type : Int){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("type" , type)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
            if (count < 1){
                db.insert(DBNAME , null , contentValues)
            }
        }

        fun save(address : String , name : String?){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("name" , name?:"")
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
            if (count < 1){
                db.insert(DBNAME , null , contentValues)
            }
        }

        fun saveFirmwareVersion(address : String , firmwareVersion : String){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("firmwareVersion" , firmwareVersion)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
//            if (count < 1){
//                db.insert(DBNAME , null , contentValues)
//            }
        }

        fun saveManufactureName(address : String , manufactureName : String){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("manufactureName" , manufactureName)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
//            if (count < 1){
//                db.insert(DBNAME , null , contentValues)
//            }
        }

        fun saveModelNumber(address : String , modelNumber : String){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("modelNumber" , modelNumber)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
//            if (count < 1){
//                db.insert(DBNAME , null , contentValues)
//            }
        }

        fun saveSerialNumber(address : String , serialNumber : String){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("serialNumber" , serialNumber)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
//            if (count < 1){
//                db.insert(DBNAME , null , contentValues)
//            }
        }

        fun saveHardwareRevision(address : String , hardwareRevision : String){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("hardwareRevision" , hardwareRevision)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
//            if (count < 1){
//                db.insert(DBNAME , null , contentValues)
//            }
        }

        fun saveSoftwareVersion(address : String , softwareVersion : String){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("softwareVersion" , softwareVersion)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
//            if (count < 1){
//                db.insert(DBNAME , null , contentValues)
//            }
        }

        fun saveSystemId(address : String , systemId : String){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("systemId" , systemId)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
//            if (count < 1){
//                db.insert(DBNAME , null , contentValues)
//            }
        }

        fun saveState(address : String , state : Int){
            val contentValues = ContentValues()
            contentValues.put("address" , address)
            contentValues.put("state" , state)
            contentValues.put("userId" , BleSdkContext.userId)
            contentValues.put("newGet" , 1)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                    arrayOf(BleSdkContext.userId.toString(), address))
            BleLog.d("更新状态：： $count")
        }

        fun saveSDKBleDevices(devices : List<SDKBleDevice>){
            if (devices.isEmpty()){
                return
            }
            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            db.beginTransaction()
            try {
                devices.forEach {
                    val contentValues = ContentValues()
                    contentValues.put("address" , it.address)
                    contentValues.put("name" , it.name)
                    contentValues.put("type" , it.type)
                    contentValues.put("state" , BleState.STATE_DISCONNECTED.state)
                    contentValues.put("firmwareVersion" , it.firmwareVersion)
                    contentValues.put("manufactureName" , it.manufactureName)
                    contentValues.put("modelNumber" , it.modelNumber)
                    contentValues.put("serialNumber" , it.serialNumber)
                    contentValues.put("hardwareRevision" , it.hardwareRevision)
                    contentValues.put("softwareVersion" , it.softwareVersion)
                    contentValues.put("systemId" , it.systemId)
                    contentValues.put("newGet" , 1)

                    contentValues.put("userId" , BleSdkContext.userId)

                    val count = db.update(DBNAME , contentValues , "userId = ? and address = ? " ,
                            arrayOf(BleSdkContext.userId.toString(), it.address))
                    if (count < 1){
                        db.insert(DBNAME , null , contentValues)
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                db.setTransactionSuccessful()
                db.endTransaction()
            }
        }

        fun getSDKBleDevice(address : String) : SDKBleDevice?{
            var cursor : Cursor ?= null
            try {
                val db = BleSdkSqLiteDateBase.sqLiteDataBase
                cursor = db.query(DBNAME , null , "userId = ? and address = ? " ,
                        arrayOf(BleSdkContext.userId.toString() ,address) , null , null, null)
                cursor?.let {
                    val moveToNext = it.moveToNext()
                    if (moveToNext){
                        it.moveToFirst()
                        val sDKBleDevice = fromCursor(cursor)
                        return sDKBleDevice
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return null
        }

        fun getSDKBleDevices() : List<SDKBleDevice> {
            val list = ArrayList<SDKBleDevice>()
            var cursor : Cursor ?= null
            try {
                val db = BleSdkSqLiteDateBase.sqLiteDataBase
                cursor = db.query(DBNAME , null , "userId = ? " ,
                        arrayOf(BleSdkContext.userId.toString()) , null , null, null)

                while (null != cursor && cursor.moveToNext()){
                    val sDKBleDevice = fromCursor(cursor)
                    list.add(sDKBleDevice)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return list
        }

        fun delete(address: String?){
            address?.let {
                val db = BleSdkSqLiteDateBase.sqLiteDataBase
                db.delete(DBNAME , "userId = ? and address = ? " ,
                        arrayOf(BleSdkContext.userId.toString() ,address))
            }
        }

        fun reset(){
            val contentValues = ContentValues()
            contentValues.put("newGet" , 0)

            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            db.update(DBNAME , contentValues , null , null)
        }

        fun clear(){
            val db = BleSdkSqLiteDateBase.sqLiteDataBase
            db.delete(DBNAME , null , null)
        }

        private fun fromCursor(cursor : Cursor) : SDKBleDevice{
            val sdkBleDevice = SDKBleDevice()
            sdkBleDevice.address = BleSdkCursorHelper.getString(cursor , "address")
            sdkBleDevice.name = BleSdkCursorHelper.getString(cursor , "name")
            sdkBleDevice.type = BleSdkCursorHelper.getInt(cursor , "type")

            sdkBleDevice.state = BleSdkCursorHelper.getInt(cursor , "state")

            sdkBleDevice.firmwareVersion = BleSdkCursorHelper.getString(cursor , "firmwareVersion")
            sdkBleDevice.manufactureName = BleSdkCursorHelper.getString(cursor , "manufactureName")
            sdkBleDevice.modelNumber = BleSdkCursorHelper.getString(cursor , "modelNumber")
            sdkBleDevice.serialNumber = BleSdkCursorHelper.getString(cursor , "serialNumber")
            sdkBleDevice.hardwareRevision = BleSdkCursorHelper.getString(cursor , "hardwareRevision")
            sdkBleDevice.softwareVersion = BleSdkCursorHelper.getString(cursor , "softwareVersion")
            sdkBleDevice.systemId = BleSdkCursorHelper.getString(cursor , "systemId")

            sdkBleDevice.newGet = BleSdkCursorHelper.getInt(cursor , "newGet")

            sdkBleDevice.rssi = 100
            return sdkBleDevice
        }

        private const val DBNAME = "sdkBleInfo"

        fun createTable(db : SQLiteDatabase){
            BleSdkDbTableHelper.fromTableName(DBNAME)
                    .addColumn_Integer("userId")

                    .addColumn_Varchar("address" , 20)
                    .addColumn_Varchar("name" , 20)
                    .addColumn_Integer("type")
                    .addColumn_Integer("state")

                    .addColumn_Varchar("firmwareVersion" , 20)
                    .addColumn_Varchar("manufactureName" , 20)
                    .addColumn_Varchar("modelNumber" , 20)
                    .addColumn_Varchar("serialNumber" , 20)
                    .addColumn_Varchar("hardwareRevision" , 20)
                    .addColumn_Varchar("softwareVersion" , 20)
                    .addColumn_Varchar("systemId" , 20)

                    .addColumn_Integer("newGet")
                    .buildTable(db)

        }

        fun dropTable(db : SQLiteDatabase){
            db.execSQL("drop table if exists $DBNAME")
        }

    }

}