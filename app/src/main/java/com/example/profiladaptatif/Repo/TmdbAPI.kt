package com.example.profiladaptatif.com.example.profiladaptatif.Repo

import com.example.profiladaptatif.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmsRecents(@Query("api_key") apikey: String) : TmdbResult

    @GET("movie/{movie_id}/credits")
    suspend fun creditFilm(@Path("movie_id") movieid: String, @Query("api_key") apikey: String) : Credits

    @GET("movie/{movie_id}")
    suspend fun detailFilm(@Path("movie_id") movieid: String, @Query("api_key") apikey: String) : MovieDetail

    @GET("search/movie")
    suspend fun searchFilm(@Query("api_key") apikey: String, @Query("query") searchtext: String) : TmdbResult

    @GET("trending/tv/week")
    suspend fun getSeries(@Query("api_key") apikey: String) : Series

    @GET("tv/{tv_id}")
    suspend fun detailSerie(@Path("tv_id") movieid: String, @Query("api_key") apikey: String) : Serie

    @GET("search/tv")
    suspend fun searchSerie(@Query("api_key") apikey: String, @Query("query") searchtext: String) : Series

    @GET("tv/{tv_id}/credits")
    suspend fun creditSerie(@Path("tv_id") movieid: String, @Query("api_key") apikey: String) : CreditsSerie

    @GET("trending/person/week")
    suspend fun getActeurs(@Query("api_key") apikey: String) : Acteur
}