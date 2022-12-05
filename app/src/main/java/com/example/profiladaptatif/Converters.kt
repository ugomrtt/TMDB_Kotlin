package com.example.profiladaptatif

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.profiladaptatif.Movie
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Converters() {
    val moshi = Moshi.Builder().build()
    val movieJsonadapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)

    @TypeConverter
    fun StringToMovie(value: String): Movie {
        return movieJsonadapter.fromJson(value)?:Movie()
    }

    @TypeConverter
    fun MovieToString(film: Movie): String {
        return movieJsonadapter.toJson(film)
    }

}