package com.uvg.laboratorio8.LocalAndOnlineData.data.di

import com.uvg.laboratorio8.LocalAndOnlineData.data.network.HttpClientFactory
import io.ktor.client.HttpClient

object KtorDependencies {
    private var httpClient: HttpClient? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}