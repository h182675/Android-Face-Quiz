package com.android.oblig.activities

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import com.android.oblig.R

class Settings : PreferenceActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = fragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(android.R.id.content, userPrefsFragment())
    }

    class userPrefsFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val manager = preferenceManager
            manager.sharedPreferencesName = "Username"
            addPreferencesFromResource(R.xml.preferences)
        }
    }
}
