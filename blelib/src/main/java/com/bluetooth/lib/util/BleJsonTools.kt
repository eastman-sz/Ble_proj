package com.bluetooth.lib.util

import org.json.JSONObject
import java.util.Map

class BleJsonTools {

    companion object {

        fun buildJson(map : HashMap<String , Any>) : String{
            val jsonObject = JSONObject()
            try {
                val iterator = map.entries.iterator()
                while (iterator.hasNext()){
                    val entry = iterator.next() as Map.Entry<*, *>
                    val key = entry.key.toString()
                    var value: Any? = entry.value

                    jsonObject.put(key, value)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
            return jsonObject.toString()
        }
    }

    class JSONBean{

        private val map = HashMap<String , Any>()

        constructor(
        )

        fun put(key: String , value : Any) : JSONBean{
            map[key] = value
            return this
        }

        fun buildJson() : String{
            return buildJson(map)
        }

    }

}