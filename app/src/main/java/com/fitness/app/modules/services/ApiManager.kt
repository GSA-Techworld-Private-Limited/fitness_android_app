package com.fitness.app.modules.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private val BASE_URL = "https://4d55-2409-40d5-103a-74f7-6c20-24bf-d2ae-27a7.ngrok-free.app"

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