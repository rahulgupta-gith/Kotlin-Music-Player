package com.example.kotlinmusicplayer.retro
import com.example.kotlinmusicplayer.models.SongResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("search")
    fun searchSongs(@Query("term") term:String, @Query("media")media:String = "music"):Call<SongResponse>
}