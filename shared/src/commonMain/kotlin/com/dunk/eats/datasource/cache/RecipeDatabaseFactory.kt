package com.dunk.eats.datasource.cache

import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase() : RecipeDatabase{
        return RecipeDatabase(driver = driverFactory.createDriver())
    }
}

expect class DriverFactory{
    fun createDriver():SqlDriver
} 