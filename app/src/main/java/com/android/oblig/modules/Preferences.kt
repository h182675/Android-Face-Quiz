package com.android.oblig.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preferences(context:Context) {
    val PREFS_FILENAME = "com.android.oblig.user_prefs"
    // Preference values
    val USER_NAME = "user_name"
    val THEME = "theme"
    val preferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,MODE_PRIVATE)

    var userName:String?
        get() = preferences.getString(USER_NAME,"default")
        set(value) = preferences.edit().putString(USER_NAME,value).apply()
    var theme:String?
        get() = preferences.getString(THEME,"default_theme")
        set(value) = preferences.edit().putString(THEME,value).apply()
}

