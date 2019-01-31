package com.android.oblig.modules

import android.content.Context
import android.content.SharedPreferences

class Preferences(context:Context) {
    val PREFS_FILENAME = "com.android.oblig.user_prefs"
    val USER_NAME = "user_name"
    val preferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,0)

    var userName:String
        get() = preferences.getString(USER_NAME,"default")
        set(value) = preferences.edit().putString(USER_NAME,value).apply()
}

