package com.example.seekhotest.core.room.converters

import androidx.room.TypeConverter
import com.example.seekhotest.network.RetrofitClient.moshi
import com.squareup.moshi.Types

//class Converters {
//    @TypeConverter
//    fun fromList(value: List<String>): String {
//        return Json.encodeToString(value)
//    }
//
//    @TypeConverter
//    fun toList(value: String): List<String> {
//        return Json.decodeFromString(value)
//    }
//}

class Converters {
    @TypeConverter
//    @JvmStatic
    fun fromStringList(value: List<String>?): String? {
        if (value == null) return null
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.toJson(value)
    }

    @TypeConverter
//    @JvmStatic
    fun toStringList(value: String?): List<String>? {
        if (value == null) return null
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.fromJson(value)
    }
}