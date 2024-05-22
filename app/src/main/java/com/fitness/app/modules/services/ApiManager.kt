package com.fitness.app.modules.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private val BASE_URL = "https://f9ec-2405-201-d02f-a029-b02b-e7c-2380-2026.ngrok-free.app"

    // API response interceptor
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    // Client
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val  apiInterface : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client)
            .build()
            .create(ApiInterface::class.java)
    }

    fun getImageUrl(imagePath: String): String {
        return BASE_URL + imagePath
    }

    fun getVideoUrl(videoPath: String): String {
        // Check if the video path is already an absolute URL
        if (videoPath.startsWith("http://") || videoPath.startsWith("https://")) {
            return videoPath
        } else {
            // If not, append it to the base URL
            return BASE_URL + videoPath
        }
    }
}