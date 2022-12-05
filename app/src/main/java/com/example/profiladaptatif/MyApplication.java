package com.example.profiladaptatif.hilt

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.myapplication.AppDatabase
import com.example.myapplication.Converters
import com.example.myapplication.FilmDao
import com.squareup.moshi.Moshi
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@HiltAndroidApp
class MyApplication : Application()