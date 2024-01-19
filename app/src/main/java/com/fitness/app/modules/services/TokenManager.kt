package com.fitness.app.modules.services

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val ACCESS_TOKEN_KEY = "token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    fun setTokens(accessToken: String) {
        sharedPreferences.edit().apply {
            putString(ACCESS_TOKEN_KEY, accessToken)
           // putString(REFRESH_TOKEN_KEY, refreshToken)
            apply()
        }
    }

    fun logout() {
        sharedPreferences.edit().apply {
            remove(ACCESS_TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
            apply()
        }
    }


}