package com.android.oblig.activities


import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import com.android.oblig.R
import com.android.oblig.modules.PREFS_FILENAME

class Settings : PreferenceActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = fragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(android.R.id.content, userPrefsFragment())
        transaction.commit()
    }

    class userPrefsFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val manager = preferenceManager
            manager.sharedPreferencesName = PREFS_FILENAME
            addPreferencesFromResource(R.xml.preferences)
        }
    }
}
