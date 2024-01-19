package com.fitness.app.modules.services

import android.content.Context
import android.content.SharedPreferences
import com.fitness.app.R

/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
        Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    private val sharedPreferences =
        context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    fun saveRefreshToken(token: String) {
        val editor = prefs.edit()
        editor.putString(REFRESH_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN,null)
    }
    fun fetchRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN,null)
    }


    fun logout() {
        prefs.edit().apply {
            remove(USER_TOKEN)
            remove(REFRESH_TOKEN)
            apply()
        }
    }


    fun clearSession() {
        sharedPreferences.edit().remove("access_token").apply()
    }
}