package com.example.profiladaptatif

import android.content.Context
import androidx.room.Room
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.ActeurDao
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.FilmDao
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.SerieDao
import com.example.profiladaptatif.com.example.profiladaptatif.Repo.Repository
import com.example.profiladaptatif.com.example.profiladaptatif.Repo.TmdbAPI
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerConverters() : Converters {
        val moshi = Moshi.Builder().build()
        // remplacer ici Converters, par le nom que vous avez donné à votre classe
        // convertisseur de types.
        return Converters(moshi)
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context)
            : FilmDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .addTypeConverter(Converters(Moshi.Builder().build()))
            .fallbackToDestructiveMigration()
            .build().filmDao()
    }

    @Singleton
    @Provides
    fun provideDbSerie(@ApplicationContext context: Context)
            : SerieDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .addTypeConverter(Converters(Moshi.Builder().build()))
            .fallbackToDestructiveMigration()
            .build().serieDao()
    }
    @Singleton
    @Provides
    fun provideDbActeur(@ApplicationContext context: Context)
            : ActeurDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .addTypeConverter(Converters(Moshi.Builder().build()))
            .fallbackToDestructiveMigration()
            .build().acteurDao()
    }

    @Singleton
    @Provides
    fun provideTmdbApi() : TmdbAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TmdbAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: TmdbAPI, dbFilm: FilmDao, dbSerie: SerieDao, dbActeur: ActeurDao): Repository {
        return Repository(api, dbFilm, dbSerie, dbActeur)
    }
}