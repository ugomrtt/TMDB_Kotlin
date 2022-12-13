package com.example.profiladaptatif.com.example.profiladaptatif.Repo

import com.example.profiladaptatif.Movie
import com.example.profiladaptatif.Result
import com.example.profiladaptatif.Serie
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.ActeurDao
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.FilmDao
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.SerieDao
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.ActeurEntity
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.FilmEntity
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.SerieEntity


class Repository(private val tmdbAPI: TmdbAPI, private val dbFilm: FilmDao, private val dbSerie: SerieDao, private val dbActeur: ActeurDao) {
    val apikey = "d2ee8f9a0abe429c115a40452040c23a"

    suspend fun lastMovies() = tmdbAPI.getFilmsRecents(apikey).results
    suspend fun searchFilm(search:String) = tmdbAPI.searchFilm(apikey, search).results
    suspend fun getFavFilms() = dbFilm.getFavFilms()
    suspend fun notFavFilm(id: Int) = dbFilm.deleteFilm(id)
    suspend fun isFavFilm(movie: Movie) = dbFilm.insertFilm(FilmEntity(fiche = movie, id = movie.id.toString()))

    suspend fun lastSeries() = tmdbAPI.getSeries(apikey).results
    suspend fun searchSeries(search:String) = tmdbAPI.searchSerie(apikey, search).results
    suspend fun getFavSeries() = dbSerie.getFavSeries()
    suspend fun notFavSerie(id: Int) = dbSerie.deleteSerie(id)
    suspend fun isFavSerie(serie: Serie) = dbSerie.insertSerie(SerieEntity(fiche = serie, id = serie.id.toString()))

    suspend fun lastActors() = tmdbAPI.getActeurs(apikey).results
    suspend fun gatFavActeurs() = dbActeur.getFavActeurs()
    suspend fun notFavActeur(id: Int) = dbActeur.deleteActeur(id)
    suspend fun isFavActeur(acteur: Result) = dbActeur.insertActeur(ActeurEntity(fiche = acteur, id = acteur.id.toString()))

    suspend fun detailFilm(id: String) = tmdbAPI.detailFilm(id, apikey)
    suspend fun detailSerie(id: String) = tmdbAPI.detailSerie(id, apikey)

    suspend fun creditFilm(id: String) = tmdbAPI.creditFilm(id, apikey)
    suspend fun creditSerie(id: String) = tmdbAPI.creditSerie(id, apikey)
}
