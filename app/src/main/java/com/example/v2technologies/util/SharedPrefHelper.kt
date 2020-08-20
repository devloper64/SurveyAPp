package com.example.v2technologies.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.v2technologies.model.DataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object SharedPrefHelper{




    fun saveNumber(number:Int,key: String,context: Context){
        val sharedPreference = context?.getSharedPreferences("NUM", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        editor?.putInt(key,number)
        editor?.commit()
    }

    fun  getNumber(key: String,context: Context): Int? {
        val sharedPreference =  context.getSharedPreferences("NUM",
            Context.MODE_PRIVATE)
        return sharedPreference.getInt(key,0)
    }


    fun saveRequired(required:String,key: String,context: Context){
        val sharedPreference = context?.getSharedPreferences("REQ", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        editor?.putString(key,required)
        editor?.commit()
    }

    fun  getRequired(key: String,context: Context): String? {
        val sharedPreference =  context.getSharedPreferences("REQ",
            Context.MODE_PRIVATE)
        return sharedPreference.getString(key,null)
    }

    fun clearSharedPreference(context: Context) {
        val sharedPreference =  context.getSharedPreferences("REQ", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.clear()
        editor.commit()
    }

    fun saveSurvey(list: List<DataModel>, key: String?, context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply() // This line is IMPORTANT !!!
    }
    fun getSurvey(key: String,context: Context): List<DataModel>?{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = prefs.getString(key, null)
        val type: Type = object : TypeToken<MutableList<DataModel>>() {}.type
        return gson.fromJson(json, type)
    }


}