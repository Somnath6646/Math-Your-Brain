package com.wenull.mathyourbrain.di


import com.wenull.mathyourbrain.data.local.AppDAO
import com.wenull.mathyourbrain.data.remote.AppService
import com.wenull.mathyourbrain.data.remote.AuthService
import com.wenull.mathyourbrain.repo.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(authService: AuthService, Service: AppService, dao: AppDAO, ) = AppRepository(authService, Service, dao)


}