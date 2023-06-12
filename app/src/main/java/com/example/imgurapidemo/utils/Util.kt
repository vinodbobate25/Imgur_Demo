package com.example.imgurapidemo.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


fun <T> setList(key: String?, list: List<T>?, context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(list)
    editor.putString(key, json)
    editor.commit()
}

fun <T> getList(key: String?, context: Context): List<T>? {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
     var arrayItems: List<T>?=null
    val serializedObject: String? = sharedPreferences.getString(key, null)
    if (serializedObject != null) {
        val gson = Gson()
        val type: Type = object : TypeToken<List<T?>?>() {}.type
        arrayItems = gson.fromJson<List<T>>(serializedObject, type)
    }
    return arrayItems;
}


fun <T> setData(key: String?, data: T?, context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val json = GsonBuilder().create().toJson(data)
    editor.putString(key, json)
    editor.commit()
}

inline fun <reified T> getData(key: String?, context: Context): T? {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    //We read JSON String which was saved.
    val value = sharedPreferences.getString(key, null)
    //JSON String was found which means object can be read.
    //We convert this JSON String to model object. Parameter "c" (of
    //type Class < T >" is used to cast.
    return GsonBuilder().create().fromJson(value, T::class.java)
}


