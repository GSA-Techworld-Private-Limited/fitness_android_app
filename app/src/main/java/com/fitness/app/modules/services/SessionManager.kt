package com.fitness.app.modules.services

import android.content.Context
import android.content.SharedPreferences
import com.fitness.app.R
import com.fitness.app.responses.WorkShopSegmentResponses
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
        Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        const val USER_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val NAME ="name"
        const val PROFILE="profile"
        const val MOBILENUMBER="phone_number"
        const val DOB="dob"
        const val WORK_SHOP_ID="id"
        private const val TASK_MAP_KEY = "task_map_key"
        private const val AUTH_TOKEN_KEY = "auth_token_key"
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

    fun saveWorkShopId(id:Int){
        val editor=prefs.edit()
        editor.putInt(WORK_SHOP_ID,id)
        editor.apply()
    }
    fun saveName(name: String) {
        val editor = prefs.edit()
        editor.putString(NAME, name)
        editor.apply()
    }

    fun saveProfile(profile: String) {
        val editor = prefs.edit()
        editor.putString(PROFILE, profile)
        editor.apply()
    }


    fun saveDOB(dob:String){
        val editor=prefs.edit()
        editor.putString(DOB,dob)
        editor.apply()
    }
    fun saveMobileNumber(phone: Int) {
        val editor = prefs.edit()
        editor.putInt(PROFILE, phone)
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

    fun fetchName(): String? {
        return prefs.getString(NAME,null)
    }

    fun fetchProfile():String?{
        return prefs.getString(PROFILE,null)
    }


    fun featchDOB():String?{
        return prefs.getString(DOB,null)
    }
    fun fetchMobile():Int?{
        return prefs.getInt(MOBILENUMBER,1)
    }

    fun fetch():Int{
        return prefs.getInt(WORK_SHOP_ID,1)
    }
    fun logout() {
        prefs.edit().apply {
            remove(USER_TOKEN)
            remove(REFRESH_TOKEN)
            apply()
        }
        // Clear the access token from SharedPreferences
        sharedPreferences.edit().remove("token").apply()
    }




    fun saveTaskMap(taskMap: Map<String, List<WorkShopSegmentResponses>>) {
        val gson = Gson()
        val json = gson.toJson(taskMap)
        editor.putString(TASK_MAP_KEY, json).apply()
    }

    fun fetchTaskMap(): Map<String, List<WorkShopSegmentResponses>> {
        val gson = Gson()
        val json = prefs.getString(TASK_MAP_KEY, null)
        val type = object : TypeToken<Map<String, List<WorkShopSegmentResponses>>>() {}.type
        return gson.fromJson(json, type) ?: emptyMap()
    }


}