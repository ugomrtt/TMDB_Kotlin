package com.example.profiladaptatif


class Repository(private val tmdbAPI: TmdbAPI) {
    val apikey = "d2ee8f9a0abe429c115a40452040c23a"

    suspend fun lastMovies() = tmdbAPI.getFilmsRecents(apikey).results
    suspend fun lastSeries() = tmdbAPI.getSeries(apikey).results
    suspend fun lastActors() = tmdbAPI.getActeurs(apikey).results



    suspend fun detailFilm(id: String) = tmdbAPI.detailFilm(id, apikey)
    suspend fun detailSerie(id: String) = tmdbAPI.detailSerie(id, apikey)

    suspend fun creditFilm(id: String) = tmdbAPI.creditFilm(id, apikey)
    suspend fun creditSerie(id: String) = tmdbAPI.creditSerie(id, apikey)
}
