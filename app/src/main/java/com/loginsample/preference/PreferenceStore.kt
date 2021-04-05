package com.loginsample.preference

import android.annotation.SuppressLint
import android.content.Context
import com.loginsample.constants.ApplicationConstants

class PreferenceStore(context: Context) {
   private var context : Context = context
    /**
     * save the values in shared preference
     * @param key
     * @param value
     */
    fun <T> saveValue(key: String?, value: T?) {
        val editor = context.getSharedPreferences(
            ApplicationConstants.PREFERENCE,
            Context.MODE_PRIVATE
        ).edit()
        if (value == null) {
            editor.remove(key).apply()
            return
        }
        if (value is String) {
            editor.putString(key, value as String?)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        }
        editor.apply()
    }


    /**
     * get the values from the shared preference
     * @param key
     * @param defValue
     * @return
     */
    @SuppressLint("UseValueOf")
    fun <T> getValue(key: String?, defValue: T): T {
        var defValue = defValue
        val pref = context.getSharedPreferences(
            ApplicationConstants.PREFERENCE,
            Context.MODE_PRIVATE
        )
        if (defValue is String) {
            defValue = pref.getString(key, defValue as String) as T
        } else if (defValue is Long) {
            defValue = pref.getLong(key, (defValue as Long)) as T
        } else if (defValue is Int) {
            defValue = pref.getInt(key, (defValue as Int)) as T
        } else if (defValue is Boolean) {
            defValue = pref.getBoolean(key, (defValue as Boolean)) as T
        } else if (defValue is Float) {
            defValue = pref.getFloat(key, (defValue as Float)) as T
        }
        return defValue
    }


    /**
     * It returns true if key is available otherwise false
     *
     * @param key
     * @return boolean
     */
    operator fun contains(key: String?): Boolean {
        val pref = context!!.getSharedPreferences(ApplicationConstants.PREFERENCE, Context.MODE_PRIVATE)
        return pref.contains(key)
    }
}