package com.example.kotlinmusicplayer.retro

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetro {
    private val retrofit =  Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiService = retrofit.create(ApiService::class.java)
}