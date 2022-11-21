package com.example.profiladaptatif

import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmsRecents(@Query("api_key") apikey: String) : TmdbResult

    @GET("movie/{movie_id}")
    suspend fun detailFilm(@Path("movie_id") movieid: String, @Query("api_key") apikey: String) : MovieDetail

    @GET("search/movie")
    suspend fun searchFilm(@Query("api_key") apikey: String, searchtext: String) : TmdbResult

    @GET("trending/tv/week")
    suspend fun getSeries(@Query("api_key") apikey: String) : Series
}