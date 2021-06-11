package com.bluetooth.lib.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by E on 2017/11/22.
 */
public final class BleSdkDbTableHelper {

    private static BleSdkDbTableHelper dbTableHelper = null;
    private StringBuilder builder = null;

    private BleSdkDbTableHelper(){
    }

    private BleSdkDbTableHelper(String dbName){
        builder = new StringBuilder();
        builder.append("create table if not exists " + dbName);
        builder.append("(id INTEGER PRIMARY KEY AUTOINCREMENT,");
    }

    public static BleSdkDbTableHelper fromTableName(String dbName){
        dbTableHelper = new BleSdkDbTableHelper(dbName);
        return dbTableHelper;
    }

    public BleSdkDbTableHelper addColumn_Integer(String columnName){
        builder.append(columnName + " Integer,");
        return dbTableHelper;
    }

    public BleSdkDbTableHelper addColumn_Long(String columnName){
        builder.append(columnName + " Long,");
        return dbTableHelper;
    }

    public BleSdkDbTableHelper addColumn_Float(String columnName){
        builder.append(columnName + " Float,");
        return dbTableHelper;
    }

    public BleSdkDbTableHelper addColumn_Varchar(String columnName , int length){
        builder.append(columnName + " varchar(" + length + "),");
        return dbTableHelper;
    }

    public void buildTable(SQLiteDatabase db){
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
        db.execSQL(builder.toString());
    }

}
