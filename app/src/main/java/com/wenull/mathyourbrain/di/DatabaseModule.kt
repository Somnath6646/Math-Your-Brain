package com.wenull.mathyourbrain.di

import android.app.Application
import androidx.room.Room
import com.wenull.mathyourbrain.data.local.AppDB
import com.wenull.mathyourbrain.data.local.AppDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideResAppDatabase(application: Application): AppDB {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDB::class.java,
            AppDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideResourcesDAO(appDatabase: AppDB): AppDAO {
        return appDatabase.appDao()
    }

}
