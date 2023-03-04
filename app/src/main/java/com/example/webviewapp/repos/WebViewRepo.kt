package com.example.webviewapp.repos

import com.example.webviewapp.RetrofitSingleton
import retrofit2.Call

interface WebViewRepo {
    suspend fun getRandomNumber(): Call<Int>
}

internal object WebViewRepoImpl: WebViewRepo {
    override suspend fun getRandomNumber(): Call<Int> =
        RetrofitSingleton.service.getRandomNumber()
}