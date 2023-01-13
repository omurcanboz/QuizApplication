package com.edu.miu.quizapplication.utility

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager {
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var ctx: Context? = null
    var privateMode = 0

    private val prefName = "My Quiz Application"
    private val isFirstTime = "isFirstTime"

    fun isFirstTime(): Boolean {
        return pref!!.getBoolean(isFirstTime, true)
    }

    fun setFirstTime(isFirstTime: Boolean) {
        editor?.putBoolean(this.isFirstTime, isFirstTime)
        editor?.commit()
    }

    @SuppressLint("PersistPreferencesEdits")
    constructor(context: Context?)  {
        ctx = context
        pref = ctx?.getSharedPreferences(prefName, privateMode)
        editor = pref?.edit()
    }

}