package com.example.sobok_android.data.sharedpref

import android.content.Context
import android.content.SharedPreferences

object SobokSharedPreference {
    private const val USER_ID = "USER_ID"
    private const val PET_ID = "PET_ID"
    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserId(): String {
        return preferences.getString(USER_ID, "") ?: ""
    }

    fun getPetId(): String {
        return preferences.getString(PET_ID, "") ?: ""
    }

    fun setPetId(value: String) {
        preferences.edit().putString(PET_ID, value).apply()
    }

    fun setUserId(value: String) {
        preferences.edit().putString(USER_ID, value).apply()
    }

}