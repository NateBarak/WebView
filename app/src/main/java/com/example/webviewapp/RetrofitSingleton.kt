package com.example.webviewapp

import com.example.webviewapp.services.WebViewService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {

    private const val BASE_URL = "https://www.random.org/"

    val service: WebViewService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebViewService::class.java)
    }

}