package com.paras.flowerapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.paras.flowerapp.R

class PrefUtils (context: Context) {

    private lateinit var prefs: SharedPreferences

    init {
        getPrefs(context)
    }

    private fun getPrefs(context: Context): SharedPreferences {
        prefs = context.getSharedPreferences(context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        return context.getSharedPreferences(context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }


    fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }




    fun logout() {
        prefs.edit { clear() }
    }
}