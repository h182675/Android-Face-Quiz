package com.android.oblig.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

    val PREFS_FILENAME = "com.android.oblig.user_prefs"
class Preferences(context:Context) {
    // Preference values
    val USER_NAME = "Username"
    val THEME = "Theme"
    val HIGH_SCORE = "High Score"
    val preferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,MODE_PRIVATE)

    var userName:String?
        get() = preferences.getString(USER_NAME,"default")
        set(value) = preferences.edit()
            .putString(USER_NAME,value)
            .apply()
    var theme:String?
        get() = preferences.getString(THEME,"default_theme")
        set(value) = preferences.edit()
            .putString(THEME,value)
            .apply()
    var highScore:Int
        get() = preferences.getInt(HIGH_SCORE,0);
        set(value) = preferences.edit()
            .putInt(HIGH_SCORE,value)
            .apply()
}

