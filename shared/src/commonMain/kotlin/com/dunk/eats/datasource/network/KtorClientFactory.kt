package com.dunk.eats.datasource.network

import io.ktor.client.*

expect class KtorClientFactory(){
    fun build():HttpClient
}