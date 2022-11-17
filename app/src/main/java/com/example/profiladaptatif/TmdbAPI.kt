package com.example.profiladaptatif

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmsRecents(@Query("api_key") apikey: String) : TmdbResult

    @GET("search/movie")
    suspend fun searchFilm(@Query("api_key") apikey: String, searchtext: String) : TmdbResult
}