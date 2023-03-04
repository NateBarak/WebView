package com.example.webviewapp.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebViewService {

    @GET("integers/")
    fun getRandomNumber(
        @Query("num") num: Int = 1,
        @Query("min") min: Int = 1,
        @Query("max") max: Int = 100,
        @Query("col") col: Int = 1,
        @Query("base") base: Int = 10,
        @Query("format") format: String = "plain",
        @Query("rnd") rnd: String = "new"
    ): Call<Int>

}