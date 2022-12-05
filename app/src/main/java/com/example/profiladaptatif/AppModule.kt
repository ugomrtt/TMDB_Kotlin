package com.example.profiladaptatif.hilt

import android.content.Context
import androidx.room.Room
import com.example.myapplication.*
import com.example.profiladaptatif.Converters
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
    fun providerConverters(): Converters {
        return Converters()
    }

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): FilmDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .addTypeConverter(Converters())
            .fallbackToDestructiveMigration()
            .build().filmDao()
    }




    @Singleton
    @Provides
    fun provideTmdbApi() : TmdbAPI =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TmdbAPI::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: TmdbAPI, db: FilmDao, serieDB: SerieDao) =
        Repository(api, db, serieDB)
}