package com.example.profiladaptatif

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.profiladaptatif.Movie
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    val moshi = Moshi.Builder().build()
    val movieJsonadapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)
    val serieJsonadapter: JsonAdapter<Serie> = moshi.adapter(Serie::class.java)
    val acteurJsonadapter: JsonAdapter<Result> = moshi.adapter(Result::class.java)

    @TypeConverter
    fun StringToMovie(value: String): Movie {
        return movieJsonadapter.fromJson(value)?:Movie()
    }

    @TypeConverter
    fun MovieToString(film: Movie): String {
        return movieJsonadapter.toJson(film)
    }

    @TypeConverter
    fun StringToSerie(value: String): Serie {
        return serieJsonadapter.fromJson(value)?:Serie()
    }

    @TypeConverter
    fun SerieToString(serie: Serie): String {
        return serieJsonadapter.toJson(serie)
    }

    @TypeConverter
    fun StringToActeur(value: String):Result {
        return acteurJsonadapter.fromJson(value)?:Result()
    }

    @TypeConverter
    fun ActeurToString(acteur: Result): String {
        return acteurJsonadapter.toJson(acteur)
    }

}