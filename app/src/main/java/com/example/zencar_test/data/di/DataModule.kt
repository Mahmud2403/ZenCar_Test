package com.example.zencar_test.data.di

import android.app.Application
import androidx.room.Room
import com.example.zencar_test.data.local_source.ZenCarDao
import com.example.zencar_test.data.local_source.ZenCarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ZenCarDatabase {
        return Room.databaseBuilder(app, ZenCarDatabase::class.java, "zen_car_db").build()
    }

    @Provides
    @Singleton
    fun provideDao(database: ZenCarDatabase): ZenCarDao {
        return database.dao
    }
}