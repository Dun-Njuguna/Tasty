package com.dunk.eats.android.di

import com.dunk.eats.android.MainActivity.Companion.BASE_URL
import com.dunk.eats.datasource.network.KtorClientFactory
import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.datasource.network.recipeService.RecipeServiceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient{
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(
        httpClient: HttpClient
    ): RecipeService{
         return RecipeServiceImp(httpClient = httpClient, baseUrl = BASE_URL)
    }

}