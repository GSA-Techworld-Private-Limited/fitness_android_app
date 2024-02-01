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
        const val USER_TOKEN = "token"
        const val REFRESH_TOKEN = "refresh_token"
        const val USER="name"
        const val DOB="dob"
        const val MOBILE_NUMBER="mobile_number"
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

    fun saveUserName(name: String){
        val editor =prefs.edit()
        editor.putString(USER,name)
        editor.apply()
    }

    fun fetchUSerName(): String? {
        return prefs.getString(USER,null)
    }


    fun saveDOB(dob:String){
        val editor=prefs.edit()
        editor.putString(DOB,dob)
        editor.apply()
    }


    fun fetchDOB(): String?{
        return prefs.getString(DOB,null)
    }
    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN,null)
    }


    fun saveNumber(mobile:String){
        val editor=prefs.edit()
        editor.putString(MOBILE_NUMBER,mobile)
        editor.apply()
    }


    fun fetchNumber() : String?{
        return prefs.getString(MOBILE_NUMBER,null)
    }

    fun fetchRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN,null)
    }


    fun logout() {
        prefs.edit().apply {
            remove(USER_TOKEN)
           // remove(REFRESH_TOKEN)
            apply()
        }
    }


    fun clearSession() {
        sharedPreferences.edit().remove("access_token").apply()
    }
}