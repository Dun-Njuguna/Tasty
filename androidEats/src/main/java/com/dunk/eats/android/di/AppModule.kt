package com.dunk.eats.android.di

import android.content.Context
import com.dunk.eats.android.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApplication(@ApplicationContext app: Context):BaseApplication{
        return app as BaseApplication
    }
}