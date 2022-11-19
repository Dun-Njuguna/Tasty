package com.dunk.eats.datasource.di

import com.dunk.eats.datasource.cache.*
import com.dunk.eats.domain.util.DatetimeUtil

class CachingModule {

    private val driverFactory: DriverFactory by lazy { DriverFactory() }
    val recipeDatabase: RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil()
        )
    }
}