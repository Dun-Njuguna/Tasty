package com.dunk.eats.android.di

import com.dunk.eats.android.BaseApplication
import com.dunk.eats.datasource.cache.*
import com.dunk.eats.domain.util.DatetimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun  provideRecipeDatabase (context:BaseApplication):RecipeDatabase{
        return  RecipeDatabaseFactory(
            driverFactory = DriverFactory(context = context)
        ).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase
    ): RecipeCache{
        return RecipeCacheImpl(recipeDatabase = recipeDatabase, datetimeUtil = DatetimeUtil())
    }

}