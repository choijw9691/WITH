package lets.go.lets_go_together

import android.app.Activity
import android.content.Context

class loginsession(c: Context) {


    private val PREF_NAME = "com.rabiaband.pref"

    init {
        lets.go.lets_go_together.loginsession.Companion.mContext = c
    }

    fun put(key: String, value: String) {
        val pref = lets.go.lets_go_together.loginsession.Companion.mContext.getSharedPreferences(
            PREF_NAME,
            Activity.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun put(key: String, value: Boolean) {
        val pref = lets.go.lets_go_together.loginsession.Companion.mContext.getSharedPreferences(
            PREF_NAME,
            Activity.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun put(key: String, value: Int) {
        val pref = lets.go.lets_go_together.loginsession.Companion.mContext.getSharedPreferences(
            PREF_NAME,
            Activity.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getValue(key: String, dftValue: String): String? {
        val pref = lets.go.lets_go_together.loginsession.Companion.mContext.getSharedPreferences(
            PREF_NAME,
            Activity.MODE_PRIVATE
        )
        try {
            return pref.getString(key, dftValue)
        } catch (e: Exception) {
            return dftValue
        }

    }

    fun getValue(key: String, dftValue: Int): Int {
        val pref = lets.go.lets_go_together.loginsession.Companion.mContext.getSharedPreferences(
            PREF_NAME,
            Activity.MODE_PRIVATE
        )
        try {
            return pref.getInt(key, dftValue)
        } catch (e: Exception) {
            return dftValue
        }

    }

    fun getValue(key: String, dftValue: Boolean): Boolean {
        val pref = lets.go.lets_go_together.loginsession.Companion.mContext.getSharedPreferences(
            PREF_NAME,
            Activity.MODE_PRIVATE
        )
        try {
            return pref.getBoolean(key, dftValue)
        } catch (e: Exception) {
            return dftValue
        }

    }

    companion object {
        val PREF_INTRO_USER_AGREEMENT = "PREF_USER_AGREEMENT"
        val PREF_MAIN_VALUE = "PREF_MAIN_VALUE"
        internal lateinit var mContext: Context
    }
}
